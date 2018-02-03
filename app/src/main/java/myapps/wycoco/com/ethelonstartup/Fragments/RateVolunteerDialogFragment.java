package myapps.wycoco.com.ethelonstartup.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class RateVolunteerDialogFragment extends AppCompatDialogFragment implements View.OnClickListener, EvaluationCriteriaAdapter.OnClickListener {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitycriteria";
    private static final String URL2 = "http://" + new Localhost().getLocalhost() + "rategroupmate";
    private OnCompleteListener onCompleteListener;

    Button doneBtn;
    EvaluationCriteriaAdapter adapter;
    ArrayList<Integer> ratings;
    ArrayList<EvaluationCriteria> criteria;
    EvaluationCriteriaAdapter evaluationCriteriaAdapter;
    LinearLayoutManager linearLayout;
    RecyclerView recyclerCriteria;
    Context mContext;
    ImageView groupmateImage;
    TextView volunteerNameTxt;
    int index;
    int i = 0;

    public RateVolunteerDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rate_volunteer ,container,false);
        doneBtn = (Button)view.findViewById(R.id.doneRateBtn);
        groupmateImage = (ImageView)view.findViewById(R.id.groupmateImage);
        ratings = new ArrayList<>();

        onCompleteListener = (OnCompleteListener)getTargetFragment();

        String volunteer_name = getArguments().getString("volunteer_name");
        String groupmateImage1 = getArguments().getString("groupmateImage");
//        Glide.with(getApplicationContext()).load(groupmateImage1)
//                .centerCrop().crossFade().into(groupmateImage);

        volunteerNameTxt = (TextView)view.findViewById(R.id.rateVolunteerName);
        recyclerCriteria = (RecyclerView)view.findViewById(R.id.criteriaRec);

        volunteerNameTxt.setText(volunteer_name);
        doneBtn.setOnClickListener(this);
        fetchCriteria();

        index = getArguments().getInt("index");
        Log.e("DFASline122", " "+index);

        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
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

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject usersObject = response.getJSONObject(i);
                                    EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
                                    evaluationCriteria.setCriteriaName(usersObject.getString("criteria"));
                                    criteria.add(evaluationCriteria);
                                    Log.e("CRITERIA: " , criteria.size() + "" + criteria.get(i).getCriteriaName());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            declarations();
                            i++;
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

        Log.e("sud declarations size",i+ "  = criterion size"+criteria.size());
        i++;
        ratings = new ArrayList<>();
        Log.e("PISTING YAWAzzzzzz",ratings.size() + " = size niya");
        for(int i = 0; i<criteria.size(); i++){
            ratings.add(0);
        }
        Log.e("PISTING YAWAzzzzzz",ratings.size() + " = size niya after sa loop");

        linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerCriteria.setLayoutManager(linearLayout);
        evaluationCriteriaAdapter = new EvaluationCriteriaAdapter(mContext, criteria, this);
        recyclerCriteria.setItemAnimator(new DefaultItemAnimator());
        recyclerCriteria.setAdapter(evaluationCriteriaAdapter);

    }


    @Override
    public void onClick(View view) {
        adapter = new EvaluationCriteriaAdapter();

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");
        String volunteer_id = getArguments().getString("volunteer_id");

        String activitygroups_id = getArguments().getString("activity_group_id");
        String volunteer_id_to_rate = getArguments().getString("volunteer_id_to_rate");

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
        Log.e("PISTING YAWA","INIG CLICK DONE = "+ratings.toString());
        int totalRating;
        int RatingsAdded = 0;

        for(int i = 0; i < ratings.size(); i++){
            RatingsAdded = RatingsAdded + ratings.get(i);
        }

        totalRating = RatingsAdded / ratings.size();

        onCompleteListener.onComplete(index, totalRating);
        Log.e("OnCompleteRatings", ratings.toString());
        ratings.clear();
        dismiss();
    }

    @Override
    public void onClick(int rating, int index) {
        Log.e("RateVolunteerDialont234",rating + " = "+index);
        ratings.set(index,rating);
        Log.e("RateVolunteerDialont235",ratings.size()+"");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            this.onCompleteListener = (OnCompleteListener)context;
        }catch (final ClassCastException e ){
            e.printStackTrace();
        }
    }

    public interface OnCompleteListener{
        public void onComplete(int index, int ratings);
    }

}
