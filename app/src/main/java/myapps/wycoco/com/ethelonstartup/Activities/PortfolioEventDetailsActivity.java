package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.EventDetailsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.GoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.GroupmatesFragment;

import myapps.wycoco.com.ethelonstartup.R;

public class PortfolioEventDetailsActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    String eventName, eventHost, eventImage, activity_id;
    TextView eventName1, eventHost1;
    ImageView eventDetailsImage;
    Intent n;
    String message;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ViewPagerAdapter adapter;
    TextView pointsEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_event_details);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#8b0000"));
        }
        tabLayout = (TabLayout) findViewById(R.id.detailsTabs);


        Typeface typefaceRoboto = Typeface.createFromAsset(this.getAssets(), "Roboto-Black.ttf");
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.linearHeader);
        collapsingToolbarLayout.setTitle(eventName);
        eventName1 = (TextView)findViewById(R.id.eventName);
        eventName1.setTypeface(typefaceRoboto);
        eventHost1 = (TextView)findViewById(R.id.eventHost);
        eventHost1.setTypeface(typefaceRoboto);
        eventDetailsImage = (ImageView)findViewById(R.id.eventDetailsImage);
        pointsEarned = (TextView)findViewById(R.id.pointsEarned);
        insTabs();

        n = getIntent();
        eventName = n.getStringExtra("eventName");
        eventHost = n.getStringExtra("eventHost");
        eventImage = n.getStringExtra("eventImage");
        activity_id = n.getStringExtra("activity_id");
        String api_token = n.getStringExtra("api_token");
        String activity_id = n.getStringExtra("activity_id");
        String volunteer_id = n.getStringExtra("volunteer_id");
        int points = n.getIntExtra("points", 0);
        String fromWhere = n.getStringExtra("from");

        if(fromWhere.equals("notification")){

            //Ibutang diri nga ang group nga groupmates fragment diretso ang mo gawas

            GroupmatesFragment groupmatesFragment = new GroupmatesFragment();
            Bundle group = new Bundle();
            group.putString("activity_id", activity_id);
            group.putString("api_token", api_token);
            group.putString("volunteer_id", volunteer_id);
            groupmatesFragment.setArguments(group);

        }else if(fromWhere.equals("normal")){

        }

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Glide.with(this).load(eventImage).centerCrop().crossFade().into(eventDetailsImage);
        eventName1.setText(eventName);
        eventHost1.setText(eventHost);
        pointsEarned.setText(points+"");


        try{
            Log.e("YAWAYAWAYAWA",n.getStringExtra("indicator"));
            GroupmatesFragment groupmatesFragment = new GroupmatesFragment();
            Bundle group = new Bundle();
            group.putString("activity_id", activity_id);
            group.putString("api_token", api_token);
            group.putString("volunteer_id", volunteer_id);
            groupmatesFragment.setArguments(group);

            viewPager.setCurrentItem(2);

        }catch (Exception e){

        }
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

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(12);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C62828"));
        tabLayout.setTabTextColors(Color.parseColor("#808080"), Color.parseColor("#c62828"));
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setText("Details");
        tabLayout.getTabAt(1).setText("Volunteers");
        tabLayout.getTabAt(2).setText("Group");
    }

    private void setupViewPager(ViewPager viewPager){

        String api_token = n.getStringExtra("api_token");
        String activity_id = n.getStringExtra("activity_id");
        String profile_id = n.getStringExtra("profileId");
        String eventDate = n.getStringExtra("eventDate");
        String eventTimeStart = n.getStringExtra("eventTimeStart");
        String eventLocation = n.getStringExtra("eventLocation");
        String contactNo = n.getStringExtra("contactNo");
        String contactPerson = n.getStringExtra("contactPerson");
        String volunteer_id = n.getStringExtra("volunteer_id");

        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        Bundle in = new Bundle();
        in.putString("eventDate", eventDate);
        in.putString("eventTimeStart", eventTimeStart);
        in.putString("eventLocation", eventLocation);
        in.putString("contactNo", contactNo);
        in.putString("contactPerson", contactPerson);
        eventDetailsFragment.setArguments(in);

        GoingVolunteersFragment goingVolunteersFragment = new GoingVolunteersFragment();
        Bundle going = new Bundle();
        going.putString("profileId", profile_id);
        going.putString("activity_id", activity_id);
        going.putString("api_token", api_token);
        goingVolunteersFragment.setArguments(going);

        GroupmatesFragment groupmatesFragment = new GroupmatesFragment();
        Bundle group = new Bundle();
        group.putString("activity_id", activity_id);
        group.putString("api_token", api_token);
        group.putString("volunteer_id", volunteer_id);
        groupmatesFragment.setArguments(group);


        adapter.addFrag(eventDetailsFragment, "Details");
        adapter.addFrag(goingVolunteersFragment, "Volunteers");
        adapter.addFrag(groupmatesFragment, "Group");
        viewPager.setAdapter(adapter);
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
