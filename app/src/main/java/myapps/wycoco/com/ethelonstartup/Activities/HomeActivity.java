package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.squareup.otto.Subscribe;

import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeActivitiesFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.NotificationsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.SecondFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.LeaderBoardFragment;
import myapps.wycoco.com.ethelonstartup.Models.BusStation;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserCredentials;
import myapps.wycoco.com.ethelonstartup.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView profileName;
    ImageView profilePicture;
    String profName, image, newSignUpUsername, profileId, cov_photo,  ethelonUserName, ethelonUserImage;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    String api_token;
    String volunteer_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }

        initInstancesDrawer();

        appBarLayout = (AppBarLayout)findViewById(R.id.appBarHome);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent n = getIntent();
        volunteer_id = n.getStringExtra("volunteer_id");
        profName = n.getStringExtra("profileName");
        image = n.getStringExtra("profilePicture");
        profileId = n.getStringExtra("profileId");
        cov_photo = n.getStringExtra("cover_photo");
        ethelonUserName = n.getStringExtra("userName");
        ethelonUserImage = n.getStringExtra("image_url");
        newSignUpUsername = n.getStringExtra("newSignUpUsername");

        Log.e("HOME ACTIVITY", "facebook_id " + profileId + image + ethelonUserImage + profileName);

        View view = navigationView.getHeaderView(0);
        profileName = (TextView) view.findViewById(R.id.profileName);
        profilePicture = (ImageView) view.findViewById(R.id.profilePicture);

        if(profName != null && image != null && profileId != null) {
            profileName.setText(profName);
            Glide.with(getApplicationContext()).load(image)
                    .centerCrop().crossFade().into(profilePicture);

        }else if(newSignUpUsername != null){
            profileName.setText(newSignUpUsername);
        }
        else{
            //If user signups with Ethelon register
            profileName.setText(ethelonUserName);
            Glide.with(getApplicationContext()).load(ethelonUserImage).centerCrop().crossFade().into(profilePicture);
        }


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
            intent.putExtra("image", image);
            intent.putExtra("profName", profName);
            intent.putExtra("profileId", profileId);
            intent.putExtra("volunteer_id",volunteer_id);

            startActivity(intent);

        }else if (id == R.id.nav_second_layout) {
            Intent intent = new Intent(this, PortfolioActivity.class);
            intent.putExtra("volunteer_id", volunteer_id);
            intent.putExtra("api_token", api_token);
            startActivity(intent);

        }else if (id == R.id.nav_third_layout) {


            Bundle bundle = new Bundle();
            bundle.putString("id",volunteer_id);
            bundle.putString("api_token",api_token);

            HomeActivitiesFragment homeActivitiesFragment = new HomeActivitiesFragment();
            homeActivitiesFragment.setArguments(bundle);

            fm.beginTransaction()
                    .replace(R.id.frame1, homeActivitiesFragment)
                    .commit();
        }else if (id == R.id.logOutButton){
            LoginManager.getInstance().logOut();
            startActivity(new Intent(this, LoginActivity.class));
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
    }

    private void setupTabIcons() {

    TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("Home");
    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_black_24dp, 0, 0);
    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c62828"));
    tabLayout.getTabAt(0).setCustomView(tabOne);

    TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//           tabTwo.setText("Notifications");
    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_notifications_black_24dp, 0, 0);
    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c62828"));
    tabLayout.getTabAt(1).setCustomView(tabTwo);

    TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//           tabThree.setText("Leaderboard");
    tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_format_list_numbered_black_24dp, 0, 0);
    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c62828"));
    tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager){

        Intent n = getIntent();
        volunteer_id = n.getStringExtra("volunteer_id");
        api_token = n.getStringExtra("api_token");
        profileId = n.getStringExtra("profileId");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putString("id",volunteer_id);
        bundle.putString("api_token", api_token);
        bundle.putString("profileId", profileId);
        Log.e("fzz",api_token + volunteer_id);

        HomeActivitiesFragment homeActivitiesFragment = new HomeActivitiesFragment();
        homeActivitiesFragment.setArguments(bundle);

        adapter.addFrag(homeActivitiesFragment,"Home");
        adapter.addFrag(new NotificationsFragment(), "Notifications");
        adapter.addFrag(new LeaderBoardFragment(), "Leaderboard");
        viewPager.setAdapter(adapter);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        BusStation.getBus().register(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        BusStation.getBus().unregister(this);
//    }


}
