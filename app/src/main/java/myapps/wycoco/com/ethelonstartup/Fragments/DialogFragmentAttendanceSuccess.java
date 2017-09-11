package myapps.wycoco.com.ethelonstartup.Fragments;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentAttendanceSuccess extends DialogFragment {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "groupmatestorate";

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

        window.setLayout((int) (width * 2), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        viewPager = (ViewPager) view.findViewById(R.id.evaluateGroupViewPager);

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");
        String volunteer_id = getArguments().getString("volunteer_id");
        Log.e("DIALOGFRAGMENT", "act_id" + activity_id);


        EvaluateFragment evaluateFragment = new EvaluateFragment();

        Bundle bundle = new Bundle();
        bundle.putString("api_token", api_token);
        bundle.putString("activity_id", activity_id);
        bundle.putString("volunteer_id", volunteer_id);
        FragmentManager fm = getChildFragmentManager();
        evaluateFragment.setArguments(bundle);

        fm.beginTransaction()
                .replace(R.id.dialogFragment, evaluateFragment)

                .addToBackStack("Rate")
                .commit();

        return view;
    }





}
