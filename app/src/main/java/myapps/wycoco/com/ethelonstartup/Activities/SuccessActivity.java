package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myapps.wycoco.com.ethelonstartup.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        String api_token = getIntent().getStringExtra("api_token");
        String volunteer_id = getIntent().getStringExtra("volunteer_id");

        Intent n = new Intent(SuccessActivity.this, PortfolioActivity.class);
        n.putExtra("api_token", api_token);
        n.putExtra("volunteer_id", volunteer_id);
        finish();
        startActivity(n);
    }
}
