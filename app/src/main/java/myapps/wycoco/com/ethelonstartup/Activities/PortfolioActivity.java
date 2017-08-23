package myapps.wycoco.com.ethelonstartup.Activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.PortfolioAdapter;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PortfolioActivity extends AppCompatActivity {

    FoldingCell fc;
    RecyclerView recView;
    ArrayList<PortfolioModel> activities = new ArrayList<>();
    PortfolioAdapter portfolioAdapter;
    Toolbar toolbar;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "portfolio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Portfolio");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        fc = (FoldingCell)findViewById(R.id.foldingCell);
        recView = (RecyclerView)findViewById(R.id.recyclerView1);

        RequestPortfolio();



    }

    public void RequestPortfolio(){

        String volunteer_id = getIntent().getStringExtra("volunteer_id");
        String api_token = getIntent().getStringExtra("api_token");

        Log.e("VOLUNTEERID", "" + volunteer_id + api_token);
        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i < response.length(); i++) {
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

                                PortfolioModel portfolioModel = new PortfolioModel(activityId, foundationId, activityName, activityImage,
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
                                        volunteerStatus,
                                        foundationName);

                                Log.e("KirstenMay", response.toString());

                                activities.add(portfolioModel);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recView.setLayoutManager(layoutManager);
                        portfolioAdapter = new PortfolioAdapter(getApplicationContext(), activities);
                        recView.setItemAnimator(new DefaultItemAnimator());
                        recView.setAdapter(portfolioAdapter);
                        Log.e("PISTE KOBE ", activities.size() + "");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("AntonWycoco", "" + error.toString());
            }
        });
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonObjectRequest);
    }



}
