package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.ActivityListGoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 7/15/2017.
 */

public class HomeActivitiesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    Localhost local = new Localhost();
    private static final String URL = "http://"+new Localhost().getLocalhost()+"getallactivities";
    RecyclerView recView;
    ArrayList<ActivityModel> activities;
    HomeActivitiesListAdapter homeActivitiesListAdapter;
    ArrayList<UserModel> users;
    ActivityListGoingVolunteersAdapter activityListGoingVolunteersAdapter;
    LinearLayoutManager linearLayoutManager;
    String id, api_token, activity_id, profileId;
    SwipeRefreshLayout swipeRefreshLayout;
    private int offset = 0;

    public HomeActivitiesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_activities_fragment, container, false);
        recView = (RecyclerView) v.findViewById(R.id.recView);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bgContentTop));

        swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    fetchActivities();
                }
            }
        );

        return v;
        }


    @Override
    public void onRefresh() {
        fetchActivities();
    }

    public void fetchActivities(){

        swipeRefreshLayout.setRefreshing(true);

        activities = new ArrayList<>();
        id = getArguments().getString("id");
        api_token = getArguments().getString("api_token");
        profileId = getArguments().getString("profileId");

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", id);
        params.put("api_token", api_token);

        Log.e("kobe", "sa home" + id + api_token);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if(response.length() > 0) {
                            Log.e("kyle", response + "");
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    Log.e("RESPONsE", response.toString());

                                    JSONObject activityObject = response.getJSONObject(i);
                                    String activityName = activityObject.getString("name");
                                    String foundationId = activityObject.getString("foundation_id");
                                    activity_id = activityObject.getString("activity_id");
                                    String activityImage = activityObject.getString("image_url");
                                    String activityQr = activityObject.getString("imageQr_url");
                                    String activityDes = activityObject.getString("description");
                                    String activityLocation = activityObject.getString("location");
                                    String activityStart = activityObject.getString("start_time");
                                    String activityEnd = activityObject.getString("end_time");
                                    String activityDate = activityObject.getString("startDate");
                                    String activityGroup = activityObject.getString("group");
                                    String activityLong = activityObject.getString("long");
                                    String activityLat = activityObject.getString("lat");
                                    String activityPoints = activityObject.getString("points_equivalent");
                                    String activityStatus = activityObject.getString("status");
                                    String activityCreated = activityObject.getString("created_at");
                                    String activityUpdated = activityObject.getString("updated_at");
                                    String contactPerson = activityObject.getString("contactperson");
                                    String activityContact = activityObject.getString("contact");
                                    String foundationName = activityObject.getString("foundtion_name");


                                    ActivityModel activityModel1 = new ActivityModel(activity_id, foundationId, activityName, activityImage,
                                            activityQr,
                                            activityDes,
                                            activityLocation,
                                            activityStart,
                                            activityEnd,
                                            activityDate,
                                            activityGroup,
                                            activityLong,
                                            activityLat,
                                            activityPoints,
                                            activityStatus,
                                            activityCreated,
                                            activityUpdated,
                                            contactPerson,
                                            activityContact,
                                            foundationName);

                                    Log.e("ACTIVITIES", response.toString());
                                    activities.add(activityModel1);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recView.setLayoutManager(layoutManager);
                            Log.e("ACTIVITY ID ", "HOME FRAGMENT" + activity_id );
                            homeActivitiesListAdapter = new HomeActivitiesListAdapter(getApplicationContext(), activities, id, api_token, profileId);
                            recView.setItemAnimator(new DefaultItemAnimator());
                            recView.setAdapter(homeActivitiesListAdapter);

                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }){

        };

        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonArrayRequest);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));


    }
    @Subscribe
    public void getMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }




}

