package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeActivitiesFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.NotificationsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.LeaderBoardFragment;
import myapps.wycoco.com.ethelonstartup.Models.Config;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeActivity extends AppCompatActivity
                implements NavigationView.OnNavigationItemSelectedListener {

    TextView profileName, profileEmail, toolbarTitle;
    ImageView profilePicture;
    String profName, image, newSignUpUsername, profileId, cov_photo,  ethelonUserName, ethelonUserImage;
    String fbProfilePicture, fbProfileName, api_token, email, volunteer_id, birthday;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    String fcm_token;
    SessionManager session;
    AppBarLayout appBarLayout;
    Set<String> skill_set;
    ArrayList<String> userSkills = new ArrayList<>();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    NotificationBadge badge;
    int notificationCount = 0;
    private String url = "http://"+new Localhost().getLocalhost()+"fcm_token";

//    KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        checkSession();
        displayFirebaseRegId();



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

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("Fire base", "inside onrecieve");
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    //txtMessage.setText(message);

                    Log.e("Fire homeactivity", message + " mgreg broadcastreciever");
                    notificationCount++;
                    Log.e("count sa notif",notificationCount+"");
                    badge.setNumber(notificationCount);
                    Toast.makeText(context, "Nay notification", Toast.LENGTH_SHORT).show();
                }
            }
        };



        initInstancesDrawer();
        getExtras();
        getNumberOfUnreadNotifications();

    }

    public void getNumberOfUnreadNotifications(){

        String notifUrl = "http://"+new Localhost().getLocalhost()+"getnumofnotifs";

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id",volunteer_id);
        params.put("api_token",api_token);
//        Log.e("volunteer_id",volunteer_id);
        JsonRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, notifUrl, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    int number = response.getInt("number");
                    notificationCount = number;
                    Log.e("ZZZZZZZZZZZZZZ",response.toString());

                    badge.setNumber(notificationCount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ZZZZZZZZZZZZZCCCCERROR",error.toString());
            }
        }
        );

        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonrequest);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onResume() {
        super.onResume();


        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));




    }

    public void notificationTabSelected(){

        String notifUrl = "http://"+new Localhost().getLocalhost()+"notiftabclicked";
        notificationCount = 0;
        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id",volunteer_id);
        Log.e("homeactivity219",volunteer_id);
        params.put("api_token",api_token);

        JsonRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, notifUrl, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ZZZZZZZZ",error.toString());
            }
        }
        );

        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonrequest);

    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        fcm_token = regId;

//        Log.e("fuck",FirebaseInstanceId.getInstance().getToken() + " ");
//        Log.e("fucker",fcm_token);

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id",volunteer_id);
        params.put("api_token",api_token);

        if(fcm_token!=null){
            params.put("fcm_token",fcm_token);
        }else{
            params.put("fcm_token", FirebaseInstanceId.getInstance().getToken());
        }

        JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                        Log.e("fuczczk",error.toString());
            }
        }
        );


        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonrequest);

        //Log.e(TAG, "Fire base reg_id: " + regId);


        if (!TextUtils.isEmpty(regId)) {
            Log.d("No logs", regId);
            System.out.printf(regId);
//            text.setText(regId + "");
        }
//        else
//           text.setText("wala pa daw ");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();

        if (id == R.id.nav_first_layout) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("fbProfilePicture", fbProfilePicture);
            intent.putExtra("fbProfileName", fbProfileName);
            intent.putExtra("profileId", profileId);
            intent.putExtra("volunteer_id",volunteer_id);
            intent.putExtra("api_token", api_token);
            intent.putExtra("message", "true");


            startActivity(intent);

        }else if (id == R.id.nav_second_layout) {
            Intent intent = new Intent(this, PortfolioActivity.class);
            intent.putExtra("volunteer_id", volunteer_id);
            intent.putExtra("api_token", api_token);
            intent.putExtra("profileId", profileId);
            startActivity(intent);

        }else if (id == R.id.nav_third_layout) {
//            Bundle bundle = new Bundle();

//            Log.e("SKILL_SET SA THIRD", userSkills.toString() + bundle.toString());
            Intent intent = new Intent(this, SkillPreferenceActivity.class);
//            intent.putStringArrayListExtra("skill_set", userSkills);
            intent.putExtra("volunteer_id",volunteer_id);
            intent.putExtra("api_token",api_token);
            startActivity(intent);
        }else if (id == R.id.logOutButton){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            LoginManager.getInstance().logOut();
                            session.logoutUser();
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            finish();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            dialog.dismiss();
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    private void initInstancesDrawer() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        //badge.setNumber(4);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("kobe","tab selected : "+tab.getPosition());
                if(tab.getPosition() == 1){
                    badge.setNumber(0);
                    toolbarTitle.setText("Notifications");
                    notificationTabSelected();
                }else{
                    tab.getIcon().setColorFilter(Color.parseColor("#c62828"), PorterDuff.Mode.SRC_IN);
                    if(tab.getPosition() == 0){
                        toolbarTitle.setText("Home");
                    }else{
                        toolbarTitle.setText("Leaderboard");

                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition() != 1)
                tab.getIcon().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupTabIcons() {


    tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c62828"));
    tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#c62828"), PorterDuff.Mode.SRC_IN);

    View v = (View) LayoutInflater.from(this).inflate(R.layout.hahay,null);
        badge = (NotificationBadge)v.findViewById(R.id.notifBadge);

        tabLayout.getTabAt(1).setCustomView(v);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c62828"));

    tabLayout.getTabAt(2).setIcon(R.drawable.ic_format_list_numbered_black_24dp);
    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c62828"));



    }

    private void setupViewPager(ViewPager viewPager){

        Intent n = getIntent();

        HashMap<String, String> user = session.getUserCredentials();
        HashMap<String, String> userEthelon = session.getUserCredentials();

        volunteer_id = n.getStringExtra("volunteer_id");
        api_token = n.getStringExtra("api_token");
        profileId = n.getStringExtra("profileId");
        if(user != null) {
            api_token = user.get(SessionManager.KEY_API_TOKEN);
            profileId = user.get(SessionManager.KEY_PROFILE_ID);
            volunteer_id = user.get(SessionManager.KEY_VOLUNTEER_ID);
        }else{
            api_token = userEthelon.get(SessionManager.KEY_API_TOKEN);
            profileId = user.get(SessionManager.KEY_PROFILE_ID);
            volunteer_id = user.get(SessionManager.KEY_VOLUNTEER_ID);

        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putString("id",volunteer_id);
        bundle.putString("api_token", api_token);
        bundle.putString("profileId", profileId);
        Log.e("fzz",api_token + volunteer_id + profileId);

        HomeActivitiesFragment homeActivitiesFragment = new HomeActivitiesFragment();
        homeActivitiesFragment.setArguments(bundle);

        NotificationsFragment notificationsFragment = new NotificationsFragment();
        notificationsFragment.setArguments(bundle);

        LeaderBoardFragment leaderBoardFragment = new LeaderBoardFragment();
        leaderBoardFragment.setArguments(bundle);

        adapter.addFrag(homeActivitiesFragment,"Home");
        adapter.addFrag(notificationsFragment, "Notifications");
        adapter.addFrag(leaderBoardFragment, "Leaderboard");
        viewPager.setAdapter(adapter);
    }

    public void getExtras(){

        appBarLayout = (AppBarLayout)findViewById(R.id.appBarHome);
        toolbarTitle = (TextView)findViewById(R.id.toolbarTitle);
        //badge = (NotificationBadge)findViewById(R.id.notificationBadge);
//        konfettiView = (KonfettiView)findViewById(R.id.konfettiView);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "Rancho-Regular.ttf");
        toolbarTitle.setTypeface(typeface);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent n = getIntent();

        email = n.getStringExtra("email");


        HashMap<String, String> userFb = session.getUserCredentials();
        fbProfilePicture = userFb.get(SessionManager.KEY_PICTURE);
        email = userFb.get(SessionManager.KEY_EMAIL);
//        Log.e("EMAIL_SESSION", email);
        fbProfileName = userFb.get(SessionManager.KEY_NAME);
        profName = userFb.get(SessionManager.KEY_NAME);
        birthday = userFb.get(SessionManager.KEY_BIRTHDAY);
        HashMap<String, String> userEthelon = session.getUserCredentialsEthelon();
        newSignUpUsername = userEthelon.get(SessionManager.KEY_USER_NAME);
        String ethelonEmail = userEthelon.get(SessionManager.KEY_EMAIL);

//        skill_set = session.getUserSkillSet();
//        userSkills.addAll(skill_set);

//        Log.e("TONY WYKSS", userFb.toString() +"Skill set size" +  skill_set.toString());



        Log.e("HOME ACTIVITY", "facebook_id " + profileId + image + ethelonUserImage + profileName + email + "birt :" + birthday);

        View view = navigationView.getHeaderView(0);
        profileName = (TextView) view.findViewById(R.id.profileName);
        profilePicture = (ImageView) view.findViewById(R.id.profilePicture);
        profileEmail = (TextView)view.findViewById(R.id.profileEmail);

        if(fbProfileName != null && fbProfilePicture != null && profileId != null) {
            profileName.setText(fbProfileName);
            Glide.with(getApplicationContext()).load(fbProfilePicture)
                    .centerCrop().crossFade().into(profilePicture);
            getDetails();

        }else if(newSignUpUsername != null){
            profileName.setText(newSignUpUsername);
        }
        else{
            //If user signups with Ethelon register
            profileName.setText(ethelonUserName);
            Glide.with(getApplicationContext()).load(ethelonUserImage).centerCrop().crossFade().into(profilePicture);
            profileEmail.setText(ethelonEmail);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            setResult(RESULT_OK, null);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void checkSession(){
        session = new SessionManager(getApplicationContext());
        session.checkLogin();


    }

    public void getDetails(){
        String Url = "http://"+new Localhost().getLocalhost()+"profileDetails";

        Intent intent = getIntent();
        String volunteer_id = intent.getStringExtra("volunteer_id");
        String api_token = intent.getStringExtra("api_token");

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id",volunteer_id);
        params.put("api_token",api_token);

        JsonRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, Url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray arrayOfDetails = response.getJSONArray("details");
                    JSONObject jsonObject = arrayOfDetails.getJSONObject(0);

                    final String email = jsonObject.getString("email");

                    profileEmail.setText(email);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("animal",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ZZZZZZZZZZZZZCCCCERROR",error.toString());
            }
        }
        );

        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonrequest);

    }

}
