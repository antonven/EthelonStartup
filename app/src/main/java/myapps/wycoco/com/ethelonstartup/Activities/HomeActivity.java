package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

import myapps.wycoco.com.ethelonstartup.Fragments.ProfileFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.SecondFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.ThirdFragment;
import myapps.wycoco.com.ethelonstartup.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView profileName;
    ImageView profilePicture;
    String profName, image, profileId;
    LoginManager loginManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.signature2Color));

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.signature2Color));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent n = getIntent();
        profName = n.getStringExtra("profileName");
        image = n.getStringExtra("profilePicture");
        profileId = n.getStringExtra("profileId");

        View view = navigationView.getHeaderView(0);
        profileName = (TextView)view.findViewById(R.id.profileName);
        profilePicture = (ImageView)view.findViewById(R.id.profilePicture);
        profileName.setText(profName);
        Glide.with(getApplicationContext()).load("https://graph.facebook.com/"+ profileId +"/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D").centerCrop().crossFade().into(profilePicture);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();

        if (id == R.id.nav_first_layout) {
//            fm.beginTransaction()
//                    .replace(R.id.frame1, ProfileFragment.newInstance(image, profName, profileId))
//                    .commit();
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("image", image);
            intent.putExtra("profName", profName);
            intent.putExtra("profileId", profileId);

            startActivity(intent);

        }else if (id == R.id.nav_second_layout) {
            fm.beginTransaction()
                    .replace(R.id.frame1, new SecondFragment())
                    .commit();

        }else if (id == R.id.nav_third_layout) {
            fm.beginTransaction()
                    .replace(R.id.frame1, new ThirdFragment())
                    .commit();
        }else if (id == R.id.logOutButton){
            LoginManager.getInstance().logOut();
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
