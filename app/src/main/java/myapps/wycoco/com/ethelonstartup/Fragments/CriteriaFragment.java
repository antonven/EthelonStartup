package myapps.wycoco.com.ethelonstartup.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import myapps.wycoco.com.ethelonstartup.Adapters.PortfolioAdapter;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriteriaFragment extends Fragment {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitycriteria";

    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    ArrayList<EvaluationCriteria> criteria;
    EvaluationCriteriaAdapter evaluationCriteriaAdapter;
    LinearLayoutManager linearLayout;
    LayoutInflater layoutInflater;
    RecyclerView recyclerCriteria;

    Context mContext;


    public CriteriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.evaluate_group_item, container, false);
        recyclerCriteria = (RecyclerView)view.findViewById(R.id.criteriaRec);

        fetchCriteria();
        return view;
    }


    public void fetchCriteria(){

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");
        String volunteer_id = getArguments().getString("volunteer_id");

        criteria = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);
        Log.e("Wycoco", "EVALUATEVOLUNTEERSFRAG " + api_token + activity_id + volunteers.size());
/*
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    Log.e("GOINGVFRAGMENT", "RESPONSE" + response.toString());
                                    JSONObject usersObject = response.getJSONObject(i);
                                    EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
                                    evaluationCriteria.setCriteriaName(usersObject.getString("criteria"));
                                    criteria.add(evaluationCriteria);
                                    Log.e("CRITERIA: " , criteria.size() + "");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            linearLayout = new LinearLayoutManager(getApplicationContext());
                            recyclerCriteria.setLayoutManager(linearLayout);
                            evaluationCriteriaAdapter = new EvaluationCriteriaAdapter(mContext, criteria);
                            recyclerCriteria.setItemAnimator(new DefaultItemAnimator());
                            recyclerCriteria.setAdapter(evaluationCriteriaAdapter);
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
        requestQueue.add(jsonArrayRequest);*/
    }

}
