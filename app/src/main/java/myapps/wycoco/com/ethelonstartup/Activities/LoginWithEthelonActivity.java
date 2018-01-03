package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;

public class LoginWithEthelonActivity extends AppCompatActivity {

    final String URL = "http://" + new Localhost().getLocalhost() + "login";
    EditText inputEmail, inputPassword;
    Button proceedBtn;
    String email, password, api_token, volunteer_id, userName, userImage, message;
    ProgressBar progressBar;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_ethelon);

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

        sessionManager = new SessionManager(getApplicationContext());
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        proceedBtn = (Button)findViewById(R.id.proceedBtn);
        progressBar = (ProgressBar)findViewById(R.id.progressBar3);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("fcm_token", FirebaseInstanceId.getInstance().getToken());
                Log.e("bla", email + password + "");

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    Log.e("Response", response.toString());
                                    message = response.getString("message");
                                    Log.e("naa", "naabot diri" + message);
                                    if(message.equals("Success")){
                                        api_token = response.getString("api_token");
                                        volunteer_id = response.getString("volunteer_id");
                                        userName = response.getString("name");
                                        userImage = response.getString("image_url");
                                        Log.e("ASD", "naabot diries" + userName + userImage);
                                        sessionManager.createLoginSessionForEthelon(email, userName, volunteer_id, api_token);
                                        Intent n = new Intent(LoginWithEthelonActivity.this, HomeActivity.class);
                                        n.putExtra("api_token", api_token);
                                        n.putExtra("volunteer_id", volunteer_id);
                                        n.putExtra("userName", userName);
                                        n.putExtra("userImage", userImage);
                                        startActivity(n);
                                        progressBar.setVisibility(View.GONE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginWithEthelonActivity.this, "Invalid credentials! Please try again.", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

    }


}
