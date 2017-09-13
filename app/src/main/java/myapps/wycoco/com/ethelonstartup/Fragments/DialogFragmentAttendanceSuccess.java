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
import myapps.wycoco.com.ethelonstartup.Adapters.EvaluateGroupPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.EvaluationCriteriaAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentAttendanceSuccess extends DialogFragment {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitycriteria";
    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    ArrayList<EvaluationCriteria> criteria;
    EvaluationCriteriaAdapter evaluationCriteriaAdapter;
    LinearLayoutManager linearLayout;
    LayoutInflater layoutInflater;
    RecyclerView recyclerCriteria;
    String api_token, volunteer_id, activity_id;
    Context mContext;
    ViewPager viewPager;

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


        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;

        window.setLayout(300, 700);
        window.setGravity(Gravity.CENTER);
        recyclerCriteria = (RecyclerView)view.findViewById(R.id.criteriaRec);

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");
        String volunteer_id = getArguments().getString("volunteer_id");
        CriteriaFragment criteriaFragment = new CriteriaFragment();
        Log.e("DIALOGFRAGMENT", "act_id" + activity_id);
        Bundle bundle = new Bundle();
        bundle.putString("activity_id", activity_id);
        bundle.putString("api_token", api_token);
        bundle.putString("volunteer_id", volunteer_id);
        criteriaFragment.setArguments(bundle);
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction()
                .replace(R.id.recFrame, criteriaFragment)
                .commit();

        if(getArguments() != null){
            getCriteria();
        }

        return view;
    }

    public void getCriteria(){

        int rating = getArguments().getInt("rating");
        String criteria_name = getArguments().getString("criteria_name");

        Log.e("rating: ", rating + "" + criteria_name);

    }





}
