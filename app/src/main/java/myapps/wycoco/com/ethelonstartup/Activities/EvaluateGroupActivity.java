package myapps.wycoco.com.ethelonstartup.Activities;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import myapps.wycoco.com.ethelonstartup.Fragments.VolunteerRatingFragment;
import myapps.wycoco.com.ethelonstartup.R;

public class EvaluateGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_group);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#8b0000"));
        }

        String api_token = getIntent().getStringExtra("api_token");
        String activity_id = getIntent().getStringExtra("activity_id");
        String volunteer_id = getIntent().getStringExtra("volunteer_id");

        VolunteerRatingFragment ratingFragment = new VolunteerRatingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("api_token", api_token);
        bundle.putString("activity_id", activity_id);
        bundle.putString("volunteer_id", volunteer_id);
        ratingFragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.evaluateGroupFrame, ratingFragment)
                .commit();

    }
}
