package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import myapps.wycoco.com.ethelonstartup.Fragments.PortfolioFragment;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.R;


public class PortfolioActivity extends AppCompatActivity {

    ArrayList<PortfolioModel> activities = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

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

        String volunteer_id = getIntent().getStringExtra("volunteer_id");
        String api_token = getIntent().getStringExtra("api_token");
        String activity_id = getIntent().getStringExtra("activity_id");
        String profile_id = getIntent().getStringExtra("profileId");

        Bundle b = new Bundle();
        b.putString("volunteer_id", volunteer_id);
        b.putString("api_token", api_token);
        b.putString("activity_id", activity_id);
        b.putString("profileId", profile_id);

        Fragment portFrag = new PortfolioFragment();
        portFrag.setArguments(b);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.framePortfolio, portFrag)
                .addToBackStack("Portfolio")
                .commit();


        toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitle("Portfolio");

        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            String volunteer_id = getIntent().getStringExtra("volunteer_id");
            String api_token = getIntent().getStringExtra("api_token");
            String activity_id = getIntent().getStringExtra("activity_id");
            String profile_id = getIntent().getStringExtra("profileId");
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("volunteer_id", volunteer_id);
                intent.putExtra("api_token", api_token);
                intent.putExtra("activity_id", activity_id);
                intent.putExtra("profileId", profile_id);
            }
        });

    }
}
