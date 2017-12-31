package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Utils.SingletonClass;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 8/26/2017.
 */

public class PortfolioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    String id, activity_id;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recView;
    ArrayList<PortfolioModel> activities;
    ArrayList<String> final_skills;
    int offset = 5;
    int ilhanan = 0;
    PortfolioAdapter portfolioAdapter;
    Parcelable recyclerViewState;
    boolean happy = true;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "portfolio";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_portfolio, container, false);
        recView = (RecyclerView) v.findViewById(R.id.recView);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bgContentTop));
        happy = true;
        activities = new ArrayList<>();
        ilhanan = 0;

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

        Log.e("kobee","offset = "+offset);
        final String volunteer_id = getArguments().getString("volunteer_id");
        final String api_token = getArguments().getString("api_token");
        final String profile_id = getArguments().getString("profileId");

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);
        params.put("offset",offset+"");

        Log.e("PORTFOLIO ID",volunteer_id + profile_id + api_token);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    Log.e("PortfolioActivities", response.toString());
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
                                    String foundationName = activityObject.getString("foundtion_name");
                                    int points = activityObject.getInt("points");
                                    int volunteer_count = activityObject.getInt("volunteer_count");
                                    String foundationImage = activityObject.getString("foundation_img");
                                    JSONArray act_skills = activityObject.getJSONArray("activity_skills");
                                    final_skills = new ArrayList<String>();

                                    for(int x = 0; x<act_skills.length(); x++){
                                        JSONObject obj = act_skills.getJSONObject(x);
                                        String skill = obj.getString("name");
                                        final_skills.add(skill);
                                    }
                                   // Log.i("final_skills", final_skills + "" + activityName);

                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = dateFormat.parse(activityDate);
                                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                                    Date time = timeFormat.parse(activityStart);

                                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM dd, EEE");
                                    String finalDate = dateFormat1.format(date);
                                    SimpleDateFormat timeFormat1 = new SimpleDateFormat("hh:mm a");
                                    String finalTime = timeFormat1.format(time);

                                    PortfolioModel portfolioModel = new PortfolioModel(activity_id, foundationId, activityName, activityImage,
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
                                            volunteerStatus,
                                            foundationName,
                                            points,
                                            volunteer_count,
                                            foundationImage,
                                            final_skills);

                                    //Log.e("PortfolioActivities", response.toString());
                                    activities.add(portfolioModel);

                                    if(portfolioAdapter != null)
                                    portfolioAdapter.notifyDataSetChanged();

                                    setView(activities);

                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            if(portfolioAdapter != null){

                                portfolioAdapter.notifyDataSetChanged();
                            }

                            happy = true;

                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                swipeRefreshLayout.setRefreshing(false);
                //Log.e("AntonWycoco", "" + error.toString());
            }
        });
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonObjectRequest);

//        SingletonClass.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
//        int x = 2;
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,x,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        ));

    }

    public void setView(ArrayList<PortfolioModel>activityModels){

        final String volunteer_id = getArguments().getString("volunteer_id");
        final String api_token = getArguments().getString("api_token");
        final String profile_id = getArguments().getString("profileId");

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

      if(ilhanan == 0){

          recView.setLayoutManager(layoutManager);
          portfolioAdapter = new PortfolioAdapter(getApplicationContext(), activityModels, activity_id, api_token, volunteer_id, profile_id);
          recView.setAdapter(portfolioAdapter);

      }

      ilhanan++;

        recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(happy){

                        Log.e("kobee","SUD SA HAPPY");
                        if((visibleItemCount + pastVisibleItems)>=totalItemCount){


                            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();

                            happy = false;
                            offset = offset + 5;
                            RequestPortfolio();
                            Toast.makeText(getContext(), "oh fuck", Toast.LENGTH_SHORT).show();

                        }

                    }else{
                        Log.e("kobee","gawas SA HAPPPY");
                    }

                }
            }
        });


    }
}
