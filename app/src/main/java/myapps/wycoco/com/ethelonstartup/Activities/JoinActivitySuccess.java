package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import myapps.wycoco.com.ethelonstartup.R;

public class JoinActivitySuccess extends AppCompatActivity {

    ImageView checkmark;
    String message;
    TextView successTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        checkmark = (ImageView)findViewById(R.id.successImage);
        successTxt = (TextView)findViewById(R.id.successTxt);

        Snackbar.make(findViewById(R.id.successRelative) , "Press the check mark to proceed.", Snackbar.LENGTH_LONG).show();

        Intent i = getIntent();
        Date currentTime = Calendar.getInstance().getTime();
        if(i.getStringExtra("message")!= null) {
            if (i.getStringExtra("message").equals("time_in")) {
                SimpleDateFormat timeFormat1 = new SimpleDateFormat("hh:mm a");
                String finalTime = timeFormat1.format(currentTime);
                successTxt.setTextSize(22);
                successTxt.setText("Time in: " + finalTime + "You can time out on the event once the foundation provides the second qr. Thanks!");
            }
        }



            checkmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String api_token = getIntent().getStringExtra("api_token");
                    String volunteer_id = getIntent().getStringExtra("volunteer_id");
                    String activity_id = getIntent().getStringExtra("activity_id");

                    Intent n = new Intent(JoinActivitySuccess.this, PortfolioActivity.class);
                    n.putExtra("api_token", api_token);
                    n.putExtra("volunteer_id", volunteer_id);
                    n.putExtra("activity_id", activity_id);
                    startActivity(n);
                    finish();
                }
            });

    }
}
