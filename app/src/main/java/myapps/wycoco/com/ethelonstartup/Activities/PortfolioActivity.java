package myapps.wycoco.com.ethelonstartup.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Adapters.PortfolioAdapter;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.R;

public class PortfolioActivity extends AppCompatActivity {

    FoldingCell fc;
    RecyclerView recView;
    ArrayList<ActivityModel> activities = new ArrayList<>();
    PortfolioAdapter portfolioAdapter;
    ActivityModel activityModel;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparent));


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Portfolio");

        fc = (FoldingCell)findViewById(R.id.foldingCell);
        recView = (RecyclerView)findViewById(R.id.recyclerView1);


//        activityModel = new ActivityModel(1,1,"ICTO Feeding Program","","123","This is to help the victims of the barangay labangon's devastating fire" ,
//                "12/25/17", "Barangay Labangon, Cebu City","1","10:00 a.m.","1:00 p.m.","Free for all ICTO members","I.C.T.O.","100");
        activities.add(activityModel);
        portfolioAdapter = new PortfolioAdapter(getApplicationContext(), activities);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);
        recView.setItemAnimator(new DefaultItemAnimator());
        recView.setAdapter(portfolioAdapter);



    }

}
