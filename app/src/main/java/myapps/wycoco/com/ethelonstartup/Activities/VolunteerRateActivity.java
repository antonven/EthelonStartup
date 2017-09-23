package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.EvaluationCriteriaAdapter;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class VolunteerRateActivity extends AppCompatActivity implements View.OnClickListener, EvaluationCriteriaAdapter.OnClickListener {
    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitycriteria";
    private static final String URL2 = "http://" + new Localhost().getLocalhost() + "rategroupmate";

    Button doneBtn;
    EvaluationCriteriaAdapter adapter;
    ArrayList<Integer> ratings;
    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    ArrayList<EvaluationCriteria> criteria;
    EvaluationCriteriaAdapter evaluationCriteriaAdapter;
    LinearLayoutManager linearLayout;
    RecyclerView recyclerCriteria;
    Context mContext;
    TextView volunteerNameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_volunteer);

        doneBtn = (Button)findViewById(R.id.doneRateBtn);
        ratings = new ArrayList<>();

        volunteerNameTxt = (TextView)findViewById(R.id.rateVolunteerName);
        recyclerCriteria = (RecyclerView)findViewById(R.id.criteriaRec);

        doneBtn.setOnClickListener(this);
        fetchCriteria();
    }

    public void fetchCriteria(){

        Intent n = getIntent();

        String activity_id = n.getStringExtra("activity_id");
        String api_token = n.getStringExtra("api_token");
        String volunteer_id = n.getStringExtra("volunteer_id");

        criteria = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);
        Log.e("Wycoco", "EVALUATEVOLUNTEERSFRAG " + api_token + activity_id + volunteers.size());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("GOINGVFRAGMENT", "RESPONSE" + response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject usersObject = response.getJSONObject(i);
                                    EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
                                    evaluationCriteria.setCriteriaName(usersObject.getString("criteria"));
                                    criteria.add(evaluationCriteria);
                                    Log.e("CRITERIA: " , criteria.size() + "" + criteria.get(i).getCriteriaName());
                                    declarations();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
//                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }


    public void declarations(){

        for(int i = 0; i<criteria.size(); i++){
            ratings.add(0);
        }

        linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerCriteria.setLayoutManager(linearLayout);
        evaluationCriteriaAdapter = new EvaluationCriteriaAdapter(mContext, criteria,this);
        recyclerCriteria.setItemAnimator(new DefaultItemAnimator());
        recyclerCriteria.setAdapter(evaluationCriteriaAdapter);

    }


    @Override
    public void onClick(View view) {
        adapter = new EvaluationCriteriaAdapter();

        Intent n = getIntent();

        String activity_id = n.getStringExtra("activity_id");
        String api_token = n.getStringExtra("api_token");
        String volunteer_id = n.getStringExtra("volunteer_id");

        String activitygroups_id = n.getStringExtra("activitygroups_id");
        String volunteer_id_to_rate = n.getStringExtra("volunteer_id_to_rate");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);

        for(int i = 0; i<criteria.size(); i++) {
            Log.e("dialogfragmentate222", "  " + ratings.get(i) +" cri" + criteria.get(i).getCriteriaName());
            params.put("criteriaParams" + i, criteria.get(i).getCriteriaName());
            params.put("ratingParams" + i, String.valueOf(ratings.get(i)));
        }
        params.put("activitygroups_id", activitygroups_id);
        params.put("volunteer_id_to_rate", volunteer_id_to_rate);
        params.put("count", String.valueOf(criteria.size()));


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL2, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);


        //ipasa dayon ang ratings nga og katong criteria nga arraylist. dapat parehag index ha. pareha atong inputskills. katong g for loop ang params
        //isend sa server ang activitygroups_id, volunteer_id, volunteer_id_to_rate, activity_id, count, criteriaParams0, ratingParams0 (index nang 0), volunteer_id, activity_id, api_token
//        for(int i = 0; i<criteria.size(); i++){
//            ratings.add(0);
//        }

        //pinakaubos ni ton para ma clear ang arraylist
        ratings.clear();
        finish();
    }

    @Override
    public void onClick(int rating, int index) {
        Log.e("line144dialogfragm","rating = "+rating + index);
        ratings.add(index,rating);
    }
}
