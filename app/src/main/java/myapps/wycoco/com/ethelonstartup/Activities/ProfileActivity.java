package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/22/2017.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    String imageUrl, profName, id;
    ImageView profilePicture;
    TextView profileName, volunteerPoints, activitiesJoined;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setBackground(null);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparent));

        profilePicture = (ImageView)findViewById(R.id.profilePictureProfile);
        profileName = (TextView)findViewById(R.id.profileNameProfile);
        volunteerPoints = (TextView)findViewById(R.id.volunteerPoints);
        activitiesJoined = (TextView)findViewById(R.id.activitiesJoined);

        Intent n = getIntent();
        n.getExtras();
        profName = n.getStringExtra("profName");
        id = n.getStringExtra("profileId");
        imageUrl = n.getStringExtra("image");

        Glide.with(this).load("https://graph.facebook.com/"+ id +"/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D1877377522288783").centerCrop().crossFade().into(profilePicture);
        profileName.setText(profName);


        volunteerPoints.setOnClickListener(this);
        activitiesJoined.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.volunteerPoints:

                break;

            case R.id.activitiesJoined:
                    startActivity(new Intent(this, PortfolioActivity.class));
                break;
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
