package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
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

public class ProfileActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout = null;
    String imageUrl, profName, id;
    ImageView profilePicture;
    TextView profileName, volunteerPoints, activitiesJoined;
    Toolbar toolbar;
    AppBarLayout appBarLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);



        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }

        profilePicture = (ImageView)findViewById(R.id.profilePictureProfile);
        profileName = (TextView)findViewById(R.id.profileNameProfile);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);

        Intent n = getIntent();
        n.getExtras();
        profName = n.getStringExtra("profName");
        id = n.getStringExtra("profileId");
        imageUrl = n.getStringExtra("image");

        Glide.with(this).load("https://graph.facebook.com/"+id+"/picture?height=500&width=500&migration_overrides=%7Boctober_2012%3Atrue%7D")
                .centerCrop().crossFade().into(profilePicture);
        profileName.setText(profName);


        Toolbar toolbar = (Toolbar)findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setBackground(null);
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
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ethback);
            collapsingToolbarLayout.setContentScrimColor((getResources().getColor(R.color.colorPrimary)));
            collapsingToolbarLayout.setStatusBarScrimColor((getResources().getColor(R.color.colorPrimary)));

    }

}
