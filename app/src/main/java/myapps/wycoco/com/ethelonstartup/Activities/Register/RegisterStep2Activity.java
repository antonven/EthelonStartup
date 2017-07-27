package myapps.wycoco.com.ethelonstartup.Activities.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.R;

public class RegisterStep2Activity extends AppCompatActivity implements View.OnClickListener {

    Button backStep, nextStep;
    GridView gridView;
    ImageView environmental, livelihood, educational, culinary, charity, sports, medical;
    ArrayList<String> skillSet = new ArrayList<>();
    private String URL = "http://172.17.0.138/EthelonStartupWeb/public/api/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step2);

        backStep = (Button)findViewById(R.id.backStep);
        nextStep = (Button)findViewById(R.id.nextStep);
        gridView = (GridView)findViewById(R.id.gridView);

        environmental = (ImageView)findViewById(R.id.environmental);
        livelihood = (ImageView)findViewById(R.id.livelihood);
        educational = (ImageView)findViewById(R.id.educational);
        culinary = (ImageView)findViewById(R.id.culinary);
        charity = (ImageView)findViewById(R.id.charity);
        sports = (ImageView)findViewById(R.id.sports);

        backStep.setOnClickListener(this);
        environmental.setOnClickListener(this);
        livelihood.setOnClickListener(this);
        culinary.setOnClickListener(this);
        educational.setOnClickListener(this);
        charity.setOnClickListener(this);
        sports.setOnClickListener(this);

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRegisterPost();
                Toast.makeText(RegisterStep2Activity.this, "Requesting to Post", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.backStep:
                finish();
                break;

            case R.id.environmental:
                skillSet.add("environmental");
                Log.e("shet", "skill added");
                break;
            case R.id.livelihood:
                skillSet.add("livelihood");
                Log.e("shet", "skill added");
                break;
            case R.id.educational:
                skillSet.add("educational");
                Log.e("shet", "skill added");
                break;
            case R.id.charity:
                skillSet.add("charity");
                Log.e("shet", "skill added");
                break;
            case R.id.sports:
                skillSet.add("sports");
                Log.e("shet", "skill added");
                break;
            case R.id.culinary:
                skillSet.add("culinary");
                Log.e("shet", "skill added");
                break;
        }
    }

    public void requestRegisterPost() {
        StringRequest string = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE", "äbcasdasdsadasd");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSE", "NA BOGOO!!!!");
                    }
                }) {

            Intent i = getIntent();
            String email = i.getExtras().getString("email");
            String name = i.getExtras().getString("name");
            String password = i.getExtras().getString("password");
            String role = i.getExtras().getString("role");

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("name", name);
                params.put("role", role);
                for(int i = 0; i < skillSet.size(); i++) {
                    params.put("skills", skillSet.get(i));
                }

                return params;
            }
        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(string);

    }
}
