package myapps.wycoco.com.ethelonstartup.Fragments;


import android.content.Context;
import android.graphics.Point;
import android.location.Criteria;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.Toast;

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

import myapps.wycoco.com.ethelonstartup.Activities.AttendanceScanner;
import myapps.wycoco.com.ethelonstartup.Adapters.EvaluateGroupAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.EvaluateGroupPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.EvaluationCriteriaAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Models.AdapterInterface;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentAttendanceSuccess extends DialogFragment implements View.OnClickListener, EvaluationCriteriaAdapter.OnClickListener{




    Button doneBtn;
    EvaluationCriteriaAdapter adapter;

    ArrayList<Integer> ratings;

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitycriteria";

    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    ArrayList<EvaluationCriteria> criteria;
    EvaluationCriteriaAdapter evaluationCriteriaAdapter;
    LinearLayoutManager linearLayout;
    LayoutInflater layoutInflater;
    RecyclerView recyclerCriteria;

    Context mContext;


    public DialogFragmentAttendanceSuccess() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_fragment_attendance_success,container,false);
        Window window = getDialog().getWindow();
        Point size = new Point();
        doneBtn = (Button)view.findViewById(R.id.doneRateBtn);

        ratings = new ArrayList<>();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;

        window.setLayout(300, 700);
        window.setGravity(Gravity.CENTER);
        recyclerCriteria = (RecyclerView)view.findViewById(R.id.recycler);
        doneBtn.setOnClickListener(this);

        fetchCriteria();

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");
        String volunteer_id = getArguments().getString("volunteer_id");

        Context mContext;
       /* CriteriaFragment criteriaFragment = new CriteriaFragment();
        Log.e("DIALOGFRAGMENT", "act_id" + activity_id);
        Bundle bundle = new Bundle();
        bundle.putString("activity_id", activity_id);
        bundle.putString("api_token", api_token);
        bundle.putString("volunteer_id", volunteer_id);

        criteriaFragment.setArguments(bundle);*/

       /* FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction()
                .replace(R.id.recFrame, criteriaFragment)
                .commit();

        if(getArguments() != null){
            getCriteria();
        }
*/
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




    public void getCriteria(){

        int rating = getArguments().getInt("rating");
        String criteria_name = getArguments().getString("criteria_name");

        Log.e("rating: ", rating + "" + criteria_name);

    }

    public void declarations(){

        linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerCriteria.setLayoutManager(linearLayout);
        evaluationCriteriaAdapter = new EvaluationCriteriaAdapter(mContext, criteria,this);
        recyclerCriteria.setItemAnimator(new DefaultItemAnimator());
        recyclerCriteria.setAdapter(evaluationCriteriaAdapter);

    }


    @Override
    public void onClick(View view) {
        adapter = new EvaluationCriteriaAdapter();
        Log.e("214","yawa");
/*
        adapter.setOnChangedRating(new AdapterInterface() {
            @Override
            public void onChanged(String criterion) {
                Toast.makeText(getContext(), "Shit" + criterion, Toast.LENGTH_SHORT).show();
            }
        });*/
        for(int i = 0; i<ratings.size(); i++)
        Log.e("dialogfragmentate222","  "+ratings.get(i));


        //ipasa dayon ang ratings nga og katong criteria nga arraylist. dapat parehag index ha. pareha atong inputskills. katong g for loop ang params
        //isend sa server ang activitygroups_id, volunteer_id, volunteer_id_to_rate, activity_id, count, criteriaParams0, ratingParams0 (index nang 0), volunteer_id, activity_id, api_token


        //pinakaubos ni ton para ma clear ang arraylist
        ratings.clear();
    }

    @Override
    public void onClick(int rating, int index) {
        Log.e("line144dialogfragm","rating = "+rating + index);
        ratings.add(index,rating);
    }
}
