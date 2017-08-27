package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import myapps.wycoco.com.ethelonstartup.R;

public class SuccessActivity extends AppCompatActivity {

    ImageView checkmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        checkmark = (ImageView)findViewById(R.id.successImage);

        Snackbar.make(findViewById(R.id.successRelative) , "Press the check mark to proceed.", Snackbar.LENGTH_LONG).show();

        checkmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String api_token = getIntent().getStringExtra("api_token");
                String volunteer_id = getIntent().getStringExtra("volunteer_id");

                Intent n = new Intent(SuccessActivity.this, PortfolioActivity.class);
                n.putExtra("api_token", api_token);
                n.putExtra("volunteer_id", volunteer_id);
                startActivity(n);
                finish();
            }
        });

    }
}
