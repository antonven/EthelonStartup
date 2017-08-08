package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.EventDetailsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.GoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.LeaderBoardFragment;
import myapps.wycoco.com.ethelonstartup.R;

public class EventDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    String eventName, eventHost, eventLocation, eventDate, eventTimeStart;
    TextView eventName1, eventHost1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }

        eventName1 = (TextView)findViewById(R.id.eventName);
        eventHost1 = (TextView)findViewById(R.id.eventHost);

        Intent n = getIntent();
        eventName = n.getStringExtra("eventName");
        eventHost = n.getStringExtra("eventHost");
        eventDate = n.getStringExtra("eventDate");
        eventTimeStart = n.getStringExtra("eventTimeStart");
        eventLocation = n.getStringExtra("eventLocation");


        eventName1.setText(eventName);
        eventHost1.setText(eventHost);


        insTabs();

    }
    private void insTabs(){

        toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setBackground(null);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewPager = (ViewPager)findViewById(R.id.viePagerDetails);
        setupViewPager(viewPager);

//        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse);

        tabLayout = (TabLayout) findViewById(R.id.detailsTabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.event_details_custom_tab, null);
        tabOne.setText("Details");
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C62828"));
        tabLayout.getTabAt(0).setText("Details");
        tabLayout.setTabTextColors(Color.parseColor("#c62828"), Color.parseColor("#ffffff"));

//        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.event_details_custom_tab, null);
        tabTwo.setText("Volunteers");
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C62828"));
        tabLayout.getTabAt(1).setText("Volunteers");
        tabLayout.setTabTextColors(Color.parseColor("#c62828"), Color.parseColor("#ffffff"));
//        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.event_details_custom_tab, null);
        tabThree.setText("Reviews");
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C62828"));
        tabLayout.getTabAt(2).setText("Reviews");
        tabLayout.setTabTextColors(Color.parseColor("#c62828"), Color.parseColor("#ffffff"));
//        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());




        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();

        Bundle in = new Bundle();

        in.putString("eventDate", eventDate);
        in.putString("eventTimeStart", eventTimeStart);
        in.putString("eventLocation", eventLocation);

        eventDetailsFragment.setArguments(in);

        adapter.addFrag(eventDetailsFragment, "Details");
        adapter.addFrag(new GoingVolunteersFragment(), "Volunteers");
        adapter.addFrag(new LeaderBoardFragment(), "Reviews");
        viewPager.setAdapter(adapter);
    }



}
