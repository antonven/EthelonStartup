package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.R;

public class CompleteActivitySuccess extends AppCompatActivity {

    ImageView checkmark;
    TextView pointsEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_success);

        pointsEarned = (TextView)findViewById(R.id.successTxt);
        checkmark = (ImageView)findViewById(R.id.successImage);

        Intent i = getIntent();
        int points = i.getIntExtra("points", 0);
        pointsEarned.setText("You have successfully completed the event and earned " + points + " points for ");
        String prefix = "";
        ArrayList<String> badges = i.getStringArrayListExtra("badges");
        for (String badge : badges){
            pointsEarned.append(prefix);
            prefix = ", ";
            pointsEarned.append(badge);
        }

        Toast.makeText(this, "Please press the check mark to proceed!", Toast.LENGTH_SHORT).show();
        checkmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String api_token = getIntent().getStringExtra("api_token");
                String volunteer_id = getIntent().getStringExtra("volunteer_id");
                String activity_id = getIntent().getStringExtra("activity_id");

                Intent n = new Intent(CompleteActivitySuccess.this, HomeActivity.class);
                n.putExtra("api_token", api_token);
                n.putExtra("volunteer_id", volunteer_id);
                n.putExtra("activity_id", activity_id);
                startActivity(n);
                finish();
            }
        });

    }
}
