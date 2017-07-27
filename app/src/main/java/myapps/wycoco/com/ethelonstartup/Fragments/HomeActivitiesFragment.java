package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.PortfolioAdapter;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 7/15/2017.
 */

public class HomeActivitiesFragment extends Fragment {

    FoldingCell fc;
    RecyclerView recView;
    ArrayList<ActivityModel> activities = new ArrayList<>();
    HomeActivitiesListAdapter homeActivitiesListAdapter;
    ActivityModel activityModel;
    Toolbar toolbar;

    public HomeActivitiesFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_activities_fragment, container, false);


        recView = (RecyclerView)v.findViewById(R.id.recView);

        activityModel = new ActivityModel(1,1,"ICTO Feeding Program","","123","This is to help the victims of the barangay labangon's devastating fire" ,
                "12/25/17", "Barangay Labangon, Cebu City","1","10:00 a.m.","1:00 p.m.","Free for all ICTO members","I.C.T.O.","100");
        activities.add(activityModel);
        HomeActivitiesListAdapter homeActivitiesListAdapter = new HomeActivitiesListAdapter(getApplicationContext(), activities);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recView.setLayoutManager(layoutManager);
        recView.setItemAnimator(new DefaultItemAnimator());
        recView.setAdapter(homeActivitiesListAdapter);

        return v;

    }



}
