package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Utils.SingletonClass;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 7/15/2017.
 */

public class HomeActivitiesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String URL = "http://"+new Localhost().getLocalhost()+"getallactivities";
    RecyclerView recView;
    ArrayList<ActivityModel> activities;
    HomeActivitiesListAdapter homeActivitiesListAdapter;
    String id, api_token, activity_id, profileId;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> final_skills;
    boolean happy = true;

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
        int offsetCount = 5;

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", id);
        params.put("api_token", api_token);
        params.put("count", String.valueOf(offsetCount));

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
                                    String foundationImage = activityObject.getString("foundation_img");
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
                                    int volunteerCount = Integer.parseInt(activityObject.getString("volunteer_count"));
                                    String volunteerStatus = activityObject.getString("volunteerstatus");
                                    JSONArray act_skills = activityObject.getJSONArray("activity_skills");
                                    final_skills = new ArrayList<String>();


                                    for(int x = 0; x<act_skills.length(); x++){
                                        JSONObject obj = act_skills.getJSONObject(x);
                                        String skill = obj.getString("name");
                                        final_skills.add(skill);
                                    }
                                    Log.i("final_skills", final_skills + "" + activityName);

                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = dateFormat.parse(activityDate);
                                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                                    Date time = timeFormat.parse(activityStart);

                                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM dd, EEE");
                                    String finalDate = dateFormat1.format(date);
                                    SimpleDateFormat timeFormat1 = new SimpleDateFormat("hh:mm a");
                                    String finalTime = timeFormat1.format(time);

                                    ActivityModel activityModel1 = new ActivityModel(activity_id, foundationId,
                                            activityName,
                                            activityImage,
                                            activityQr,
                                            activityDes,
                                            activityLocation,
                                            finalTime,
                                            activityEnd,
                                            finalDate,
                                            activityGroup,
                                            activityLong,
                                            activityLat,
                                            activityPoints,
                                            activityStatus,
                                            activityCreated,
                                            activityUpdated,
                                            contactPerson,
                                            activityContact,
                                            foundationName,
                                            volunteerCount,
                                            volunteerStatus,
                                            foundationImage,
                                            final_skills);

                                    Log.e("ACTIVITIES and skillz", "" + final_skills + act_skills);
                                    activities.add(activityModel1);

                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recView.setLayoutManager(layoutManager);
                            Log.e("ACTIVITY ID ", "HOME FRAGMENT" + activity_id );
                            FragmentManager suppFragment = getChildFragmentManager();
                            homeActivitiesListAdapter = new HomeActivitiesListAdapter(getApplicationContext(),activity_id, activities, id, api_token, profileId, suppFragment);
                            recView.setItemAnimator(new DefaultItemAnimator());
                            recView.setAdapter(homeActivitiesListAdapter);

                            recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                    super.onScrolled(recyclerView, dx, dy);

                                    if(dy > 0){

                                        int visibleItemCount = layoutManager.getChildCount();
                                        int totalItemCount = layoutManager.getItemCount();
                                        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                                        if(happy){
                                            if((visibleItemCount + pastVisibleItems)>=totalItemCount){
                                                happy = false;



                                            }
                                        }

                                    }
                                }
                            });

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

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonArrayRequest);
//        SingletonClass.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);



    }
    @Subscribe
    public void getMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

