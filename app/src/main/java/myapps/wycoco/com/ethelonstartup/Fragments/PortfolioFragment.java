package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.PortfolioAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 8/26/2017.
 */

public class PortfolioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    String id, activity_id;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recView;
    ArrayList<PortfolioModel> activities;
    PortfolioAdapter portfolioAdapter;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "portfolio";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_portfolio, container, false);
        recView = (RecyclerView) v.findViewById(R.id.recView);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bgContentTop));

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                RequestPortfolio();
            }
        }
        );

        return v;
    }

    @Override
    public void onRefresh() {
        RequestPortfolio();
    }

    public void RequestPortfolio(){

        swipeRefreshLayout.setRefreshing(true);
        activities = new ArrayList<>();

        final String volunteer_id = getArguments().getString("volunteer_id");
        final String api_token = getArguments().getString("api_token");
        final String profile_id = getArguments().getString("profileId");

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
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
                                    String volunteerStatus = activityObject.getString("joined");
                                    String foundationName = activityObject.getString("foundation_name");
                                    int points = activityObject.getInt("points");
                                    int volunteer_count = activityObject.getInt("volunteer_count");

                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = dateFormat.parse(activityDate);

                                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM dd, EEE");
                                    String finalDate = dateFormat1.format(date);

                                    PortfolioModel portfolioModel = new PortfolioModel(activity_id, foundationId, activityName, activityImage,
                                            activityQr,
                                            activityDes,
                                            activityLocation,
                                            activityStart,
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
                                            volunteerStatus,
                                            foundationName,
                                            points,
                                            volunteer_count);

                                    Log.e("PortfolioActivities", response.toString());
                                    activities.add(portfolioModel);

                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recView.setLayoutManager(layoutManager);
                            portfolioAdapter = new PortfolioAdapter(getApplicationContext(), activities, activity_id, api_token, volunteer_id, profile_id);
                            recView.setItemAnimator(new DefaultItemAnimator());
                            recView.setAdapter(portfolioAdapter);
                            Log.e("PISTE KOBE ", activities.size() + "id :" + activity_id);
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                swipeRefreshLayout.setRefreshing(false);
                Log.e("AntonWycoco", "" + error.toString());
            }
        });
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonObjectRequest);
    }
}
