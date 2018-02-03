package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import myapps.wycoco.com.ethelonstartup.Adapters.BadgeCollectionAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.BasicAdapter;
//import myapps.wycoco.com.ethelonstartup.Adapters.ProfileSkillsController;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.BadgeFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.EventDetailsFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.GoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.PortfolioFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.ReviewsFragment;
import myapps.wycoco.com.ethelonstartup.Models.SkillBadgesModel;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 7/22/2017.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    CollapsingToolbarLayout collapsingToolbarLayout = null;
    String id;
    String fbProfilePicture, fbProfileName;
    ImageView profilePicture;
    Button editProfileBtn;
    TextView profileName, profileEmail;
    AppBarLayout appBarLayout;
    SessionManager sessionManager;
    RecyclerView skillBadgesCollection;
    GridView gridView, gridViewBadges;
    ArrayList<Integer> images;
    BasicAdapter basicAdapter;
    ArrayList<Integer> badges;
    RecyclerView recView;
    ArrayList<SkillBadgesModel> badgesModels;
//    ProfileSkillsController controller;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;
    ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_layout_profile);

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

        insTabs();

        sessionManager = new SessionManager(getApplicationContext());

        profilePicture = (ImageView)findViewById(R.id.profilePictureProfile);
        profileEmail = (TextView)findViewById(R.id.profileEmail);
        profileName = (TextView)findViewById(R.id.profileNameProfile);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        editProfileBtn = (Button)findViewById(R.id.editProfileBtn);



        editProfileBtn.setOnClickListener(this);

        HashMap<String, String> user = sessionManager.getUserCredentials();
        String userEmail = user.get(SessionManager.KEY_EMAIL);

        Intent n = getIntent();
        n.getExtras();
        fbProfileName = n.getStringExtra("fbProfileName");
        id = n.getStringExtra("profileId");
        fbProfilePicture = n.getStringExtra("fbProfilePicture");
        profileEmail.setText(userEmail);
        Glide.with(this).load("https://graph.facebook.com/"+id+"/picture?height=500&width=500&migration_overrides=%7Boctober_2012%3Atrue%7D")
                .centerCrop().crossFade().into(profilePicture);
        profileName.setText(fbProfileName);

        loadBadges();
        loadInterests();


        Toolbar toolbar = (Toolbar)findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setBackground(null);
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse1);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    dynamicToolbarColor();
                    collapsingToolbarLayout.setTitle(profileName.getText());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void dynamicToolbarColor(){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ethback);
            collapsingToolbarLayout.setContentScrimColor((getResources().getColor(R.color.colorPrimary)));
            collapsingToolbarLayout.setStatusBarScrimColor((getResources().getColor(R.color.colorPrimary)));

    }

    private void insTabs(){


        viewPager = (ViewPager)findViewById(R.id.viePagerDetails);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.achievementsTab);
        tabLayout.setSelectedTabIndicatorHeight(12);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C62828"));

        tabLayout.setTabTextColors(Color.parseColor("#808080"), Color.parseColor("#c62828"));
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setText("Achievements");
        tabLayout.getTabAt(1).setText("Portfolio");

    }
    private void setupViewPager(ViewPager viewPager){

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Intent intent = getIntent();
        String profileId = intent.getStringExtra("profileId");
        String volunteer_id = intent.getStringExtra("volunteer_id");
        String api_token = intent.getStringExtra("api_token");
        PortfolioFragment portfolioFragment = new PortfolioFragment();
        BadgeFragment badgeFragment = new BadgeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("profileId", profileId);
        bundle.putString("volunteer_id", volunteer_id);
        bundle.putString("api_token", api_token);
        portfolioFragment.setArguments(bundle);
        badgeFragment.setArguments(bundle);

        adapter.addFrag(badgeFragment, "Achievements");
        adapter.addFrag(portfolioFragment, "Portfolio");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent s = getIntent();

        switch(view.getId()){

            case R.id.editProfileBtn:
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("fbProfilePicture", fbProfilePicture);
                startActivity(intent);
                break;
        }
    }

    public void loadBadges(){
        gridViewBadges = (GridView)findViewById(R.id.gridViewBadges);

        badges = new ArrayList<>();
        badges.add(R.drawable.medicinebadge);
        gridViewBadges.setAdapter(new BasicAdapter(badges, getApplicationContext()));
    }

    public void loadInterests(){

        images = new ArrayList<>();
        gridView = (GridView)findViewById(R.id.gridView);

//        ArrayList<String> skills = activities.get(position).getAct_skills();
//        SharedPreferences shared = getSharedPreferences("SKILLS_PREF", MODE_PRIVATE);
//        Set<String> skills = shared.getStringSet("skills", null);
//        Log.e("ProfileActivity skills", skills.toString());
//        for(String skill : skills){
//
//            if(skill.equals("environment"))
//                images.add(R.drawable.environment_volunteer);
//            else if(skill.equals("medical"))
//                images.add(R.drawable.medical_volunteer);
//            else if(skill.equals("livelihood"))
//                images.add(R.drawable.livelihood_volunteer);
//            else if(skill.equals("sports"))
//                images.add(R.drawable.sports_volunteer);
//            else if(skill.equals("culinary"))
//                images.add(R.drawable.culinary_volunteer);
//            else if(skill.equals("charity"))
//                images.add(R.drawable.charity_volunteer);
//            else if(skill.equals("arts"))
//                images.add(R.drawable.arts_volunteer);
//            else if(skill.equals("education"))
//                images.add(R.drawable.education_volunteer);
//        }
//        Log.e("IMAGESPROFILE skills", images.toString());
//
//        basicAdapter = new BasicAdapter(images, getApplicationContext());
//        gridView.setAdapter(basicAdapter);
    }


}
