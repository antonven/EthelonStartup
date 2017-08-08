package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.PortfolioAdapter;
import myapps.wycoco.com.ethelonstartup.Libraries.VolleySingleton;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 7/15/2017.
 */

public class HomeActivitiesFragment extends Fragment {

    //    private String URL = "http://172.17.3.2/EthelonStartupWeb/public/api/getallactivities";
    Localhost local = new Localhost();
    String URL = local.getLocalhost();
    FoldingCell fc;
    RecyclerView recView;
    ArrayList<ActivityModel> activities = new ArrayList<>();
    ActivityModel activityModel;
    HomeActivitiesListAdapter homeActivitiesListAdapter;
    Toolbar toolbar;
    String id, api_token;

    public HomeActivitiesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_activities_fragment, container, false);


        recView = (RecyclerView) v.findViewById(R.id.recView);

        id = getArguments().getString("id");
        api_token = getArguments().getString("api_token");

        homeActivitiesListAdapter = new HomeActivitiesListAdapter(getApplicationContext(), activities);





            Map<String, String> params = new HashMap<String, String>();
            params.put("volunteer_id", id);
            params.put("api_token", api_token);

            Log.e("kobe", "sa home" + id + api_token);

            //params.put("count",String.valueOf(skillSet.size()));


            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, "http://192.168.1.6/EthelonStartupWeb/public/api/getallactivities",
                    new JSONObject(params),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("kyle", response + "");
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject activityObject = response.getJSONObject(i);
                                    String activityName = activityObject.getString("name");
                                    String foundationId = activityObject.getString("foundation_id");
                                    String activityId = activityObject.getString("activity_id");
                                    String activityImage = activityObject.getString("image_url");
                                    String activityQr = activityObject.getString("imageQr_url");
                                    String activityDes = activityObject.getString("description");
                                    String activityLocation = activityObject.getString("location");
                                    String activityStart = activityObject.getString("start_time");
                                    String activityEnd = activityObject.getString("end_time");
                                    String activityDate = activityObject.getString("date");
                                    String activityGroup = activityObject.getString("group");
                                    String activityLong = activityObject.getString("long");
                                    String activityLat = activityObject.getString("lat");
                                    String activityPoints = activityObject.getString("points_equivalent");
                                    String activityStatus = activityObject.getString("status");
                                    String activityCreated = activityObject.getString("created_at");
                                    String activityUpdated = activityObject.getString("updated_at");
                                    String contactPerson = activityObject.getString("contactperson");
                                    String activityContact = activityObject.getString("contact");


                                    ActivityModel activityModel1 = new ActivityModel(activityId, foundationId, activityName, activityImage,
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
                                            activityContact);

                                    Log.e("asdsadasdads", response.toString());

//                                ActivityModel activityModel2 = new ActivityModel("2", "Philippine Red Cross", "Relief Operations Marawi" ,"",
//                                        "",
//                                        "This event is to help the victims of marawi regain the the city that they have always lived.",
//                                        "Marawi City, Zamboanga del Norte",
//                                        "7:00 am",
//                                        "5:00 pm",
//                                        "Dec 25, 2017",
//                                        "5",
//                                        "38",
//                                        "40",
//                                        "100",
//                                        "",
//                                        "",
//                                        "");


                                    activities.add(activityModel1);

                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recView.setLayoutManager(layoutManager);
                                    recView.setItemAnimator(new DefaultItemAnimator());
                                    recView.setAdapter(homeActivitiesListAdapter);
                                    Log.e("PISTE KOBE ", activities.size() + "");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("volunteer_id",id);
                    params.put("api_token",api_token);

                    Log.e("kobe","piste null daw" + id + api_token);

                    //params.put("count",String.valueOf(skillSet.size()));
                    return params;
                }
            };

            RequestQueue request = Volley.newRequestQueue(getApplicationContext());
            request.add(jsonArrayRequest);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(jsonArrayRequest);
//        VolleySingleton.getInstance().addToRequestQueue(jsonArrayRequest);
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));

            return v;

        }


    }

