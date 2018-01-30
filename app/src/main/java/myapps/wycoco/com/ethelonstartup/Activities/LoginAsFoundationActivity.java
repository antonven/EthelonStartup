package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import myapps.wycoco.com.ethelonstartup.R;

public class LoginAsFoundationActivity extends AppCompatActivity implements View.OnClickListener {

    Button proceedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_foundation);


        proceedBtn = (Button)findViewById(R.id.proceedBtn);


        proceedBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.proceedBtn:
                Intent intent = new Intent(LoginAsFoundationActivity.this, FoundationHomeActivity.class);
        }
    }
}
