package myapps.wycoco.com.ethelonstartup.Activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettoGenerator;
import com.github.jinatonic.confetti.confetto.Confetto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import myapps.wycoco.com.ethelonstartup.Libraries.MaterialEditText;
import myapps.wycoco.com.ethelonstartup.Libraries.MaterialEditText;
import myapps.wycoco.com.ethelonstartup.R;


/**
 * Created by dell on 7/20/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    EditText userName, passWord, conPass, eMail;
    FrameLayout frame;
    Button register;
    String databaseUrl = "http://172.17.2.35/EthelonStartupWeb/public/register";
    RequestQueue requestQueue;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);



        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.signature2Color));


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
                StringRequest stringRequest = new StringRequest(Request.Method.POST, databaseUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(RegisterActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("spitsad", error.getMessage()+ "");
                            }
                        }



                ){
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", eMail.getText().toString());
                    params.put("password", passWord.getText().toString());
                    params.put("name", userName.getText().toString());
                    Log.e("piste", "NA PASLAK ANG DATA");
                    return params;
                }
                };
                requestQueue.add(stringRequest);
                Toast.makeText(RegisterActivity.this, "NA REGISTER", Toast.LENGTH_SHORT).show();

            }
        });





        }

//        CommonConfetti.rainingConfetti(frame, new int[]{Color.CYAN})
//                .infinite();
//
//        ConfettoGenerator confettoGenerator = new ConfettoGenerator() {
//            @Override
//            public Confetto generateConfetto(Random random) {
//                final Bitmap bitmap = allPos
//            }
//        }
    }

