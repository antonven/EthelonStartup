package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phelat.fun.Control.FunControl;
import com.phelat.fun.Layouts.Funny;
import com.phelat.fun.Widget.FunnyButton;

import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.EventDetailsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.GoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeActivitiesFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.SecondFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.ThirdFragment;
import myapps.wycoco.com.ethelonstartup.R;

public class EventDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String eventName, eventHost, eventLocation, eventDate, eventTimeStart;
    TextView eventName1, eventHost1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparent));

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
        adapter.addFrag(new ThirdFragment(), "Reviews");
        viewPager.setAdapter(adapter);
    }



}
