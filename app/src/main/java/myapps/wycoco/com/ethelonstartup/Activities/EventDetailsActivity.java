package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.EventDetailsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.GoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.ReviewsFragment;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    String eventName, eventHost, eventImage, activity_id, volunteerStatus, foundationImage;
    TextView eventName1, eventHost1;
    Button joinActivityBtn, unjoinActivityBtn;
    ImageView eventDetailsImage;
    Intent n;

    CollapsingToolbarLayout collapsingToolbarLayout;
    ViewPagerAdapter adapter;
    private static final String URL = "http://"+new Localhost().getLocalhost()+"joinactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#8b0000"));
        }

        Typeface typefaceRoboto = Typeface.createFromAsset(this.getAssets(), "Roboto-Black.ttf");
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.linearHeader);
        collapsingToolbarLayout.setTitle(eventName);
        eventName1 = (TextView)findViewById(R.id.eventName);
        eventName1.setTypeface(typefaceRoboto);
        eventHost1 = (TextView)findViewById(R.id.eventHost);
        eventHost1.setTypeface(typefaceRoboto);
        joinActivityBtn = (Button)findViewById(R.id.joinActivityBtn);
        unjoinActivityBtn = (Button)findViewById(R.id.unjoinActivityBtn);
        eventDetailsImage = (ImageView)findViewById(R.id.eventDetailsImage);

        n = getIntent();
        eventName = n.getStringExtra("eventName");
        eventHost = n.getStringExtra("eventHost");
        eventImage = n.getStringExtra("eventImage");
        activity_id = n.getStringExtra("activity_id");
        volunteerStatus = n.getStringExtra("volunteerStatus");
        foundationImage = n.getStringExtra("foundationImage");


        if (volunteerStatus.equals("yes")) {
                joinActivityBtn.setVisibility(View.GONE);
                unjoinActivityBtn.setVisibility(View.VISIBLE);
            }



        Glide.with(this).load(eventImage).centerCrop().crossFade().into(eventDetailsImage);
        eventName1.setText(eventName);


//        joinActivityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
        joinActivityBtn.setOnClickListener(this);
        eventHost1.setOnClickListener(this);
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

        tabLayout = (TabLayout) findViewById(R.id.detailsTabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(12);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C62828"));
        tabLayout.setTabTextColors(Color.parseColor("#808080"), Color.parseColor("#c62828"));
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setText("Details");
        tabLayout.getTabAt(1).setText("Volunteers");
        tabLayout.getTabAt(2).setText("Reviews");
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
        ArrayList<String> skills = n.getStringArrayListExtra("skills");

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        Bundle in = new Bundle();
        in.putString("eventDate", eventDate);
        in.putString("eventTimeStart", eventTimeStart);
        in.putString("eventLocation", eventLocation);
        in.putString("contactNo", contactNo);
        in.putString("contactPerson", contactPerson);
        in.putStringArrayList("skills", skills);
        eventDetailsFragment.setArguments(in);

        GoingVolunteersFragment goingVolunteersFragment = new GoingVolunteersFragment();
        Bundle going = new Bundle();
        going.putString("profileId", profile_id);
        going.putString("activity_id", activity_id);
        going.putString("api_token", api_token);
        goingVolunteersFragment.setArguments(going);

        adapter.addFrag(eventDetailsFragment, "Details");
        adapter.addFrag(goingVolunteersFragment, "Volunteers");
        adapter.addFrag(new ReviewsFragment(), "Reviews");
        viewPager.setAdapter(adapter);
    }



    public void fetchDetails(){

        final String volunteer_id = n.getStringExtra("id");
        final String api_token = n.getStringExtra("api_token");
        final String activity_id = n.getStringExtra("activity_id");

        final Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", volunteer_id);
        params.put("activity_id", activity_id);
        params.put("api_token", api_token);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("message").equals("Already Joined")) {

                            } else {
                                //Fragment na naka success siya!
                                Intent n = new Intent(EventDetailsActivity.this, JoinActivitySuccess.class);
                                n.putExtra("api_token", api_token);
                                n.putExtra("volunteer_id", volunteer_id);
                                n.putExtra("activity_id", activity_id);
                                startActivity(n);
                                //ma add sha sa portfolio
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EventDetailsActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View view) {
        String foundation_id = getIntent().getStringExtra("foundation_id");
        Intent n;
        switch (view.getId()){

            case R.id.joinActivityBtn:
                fetchDetails();
                joinActivityBtn.setVisibility(View.GONE);
                unjoinActivityBtn.setVisibility(View.VISIBLE);
                break;

            case R.id.eventHost:
                n = new Intent(getApplicationContext(), FoundationProfileActivity.class);
                n.putExtra("foundation_id", foundation_id);
                n.putExtra("foundation_name", eventHost);
                getApplicationContext().startActivity(n);
                break;

        }
    }
}
