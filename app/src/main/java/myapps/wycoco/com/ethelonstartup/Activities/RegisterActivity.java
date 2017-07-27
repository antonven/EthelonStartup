package myapps.wycoco.com.ethelonstartup.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import myapps.wycoco.com.ethelonstartup.R;



/**
 * Created by dell on 7/20/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    private String URL = "http://172.17.0.138/EthelonStartupWeb/public/api/register";

    EditText userName, passWord, conPass, eMail;
    FrameLayout frame;
    Button register;
    RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparent));

        frame = (FrameLayout)findViewById(R.id.frame1);
        userName = (EditText)findViewById(R.id.inputUsername);
        passWord = (EditText)findViewById(R.id.inputPassword);
        conPass = (EditText)findViewById(R.id.inputConfirmPassword);
        eMail = (EditText)findViewById(R.id.inputEmail);
        register = (Button)findViewById(R.id.registerButton);
        requestQueue = Volley.newRequestQueue(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            requestRegisterPost();
            }
        });

        }
    public void requestRegisterPost() {
        StringRequest string = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

            String email = eMail.getText().toString();
            String password = passWord.getText().toString();
            String name = userName.getText().toString();
            String role = conPass.getText().toString();

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("name", name);
                params.put("role", role);

                return params;
            }
        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(string);

    }








}

