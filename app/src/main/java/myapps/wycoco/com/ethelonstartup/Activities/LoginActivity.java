package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import myapps.wycoco.com.ethelonstartup.Activities.Register.RegisterActivity;
import myapps.wycoco.com.ethelonstartup.Adapters.LoginViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeActivitiesFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.SecondFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.ThirdFragment;
import myapps.wycoco.com.ethelonstartup.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    CallbackManager callbackManager;
    EditText inputEmail, inputPassword;
    Button buttonLogin, buttonFacebook, buttonSignup ;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    VideoView vidView;
    Profile profile;
    Uri uri;
    Spinner inputRole;
    ViewPager vp;
    MediaController mediaController;
    LoginViewPagerAdapter viewPager;
    String name, facebook_id, email;
    String volunteer_id;
    String id;

    RequestQueue requestQueue;
    private String URL = "http://192.168.1.5/EthelonStartupWeb/public/api/loginwithfb";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        setTitle("");





        int rawId = getResources().getIdentifier("vid",  "raw", getPackageName());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
       // window.setStatusBarColor(this.getResources().getColor(R.color.transparent));

        callbackManager = CallbackManager.Factory.create();

        vidView = (VideoView)findViewById(R.id.videoView);
        buttonSignup = (Button)findViewById(R.id.buttonEthelonSignUp);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonFacebook = (Button)findViewById(R.id.buttonFacebook);

        uri = Uri.parse("android.resources://" + getPackageName()+ "/" + R.raw.bg_vid);


        vidView.setVideoURI(uri);
        vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vidView.start();
                mediaPlayer.setLooping(true);
            }
        });


        vp = (ViewPager)findViewById(R.id.viewPagerText);
        viewPager = new LoginViewPagerAdapter(this);
        vp.setAdapter(viewPager);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutomaticSlide(), 2000, 4000);

        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);




        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(callbackManager.onActivityResult(requestCode, resultCode, data)){
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        profile = Profile.getCurrentProfile();
        if(profile!=null) {

        StringRequest string = new StringRequest(Request.Method.POST, "http://192.168.1.5/EthelonStartupWeb/public/api/session",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       id = response;
                        Log.e("kobe",id);
                        Intent intent = new Intent(LoginActivity.this,SkillsActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("kobe", error.getMessage().toString());
                        error.printStackTrace();
                        Log.e("kobe","naas error");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("facebook_id", profile.getId());
                Log.e("kobe","id "+profile.getId());
                return params;
            }
        };
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(string);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new HomeActivitiesFragment(), "Home");
        adapter.addFrag(new SecondFragment(), "Notifications");
        adapter.addFrag(new ThirdFragment(), "Leaderboard");
        viewPager.setAdapter(adapter);
    }

    public class AutomaticSlide extends TimerTask{

        @Override
        public void run() {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(vp.getCurrentItem() == 0){
                        vp.setCurrentItem(1);
                    }else if(vp.getCurrentItem() == 1){
                        vp.setCurrentItem(2);
                    }else
                        vp.setCurrentItem(0);
                }
            });
        }
    }


    private void nextActivity(Profile profile){
        if(profile!= null){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            i.putExtra("profileName", profile.getName());
            i.putExtra("profilePicture", profile.getProfilePictureUri(500,500).toString());
            i.putExtra("profileId", profile.getId());
            i.putExtra("volunteer_id",volunteer_id);
            startActivity(i);
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.buttonEthelonSignUp:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

            case R.id.buttonFacebook:
                LoginManager.getInstance().logInWithReadPermissions(this,
                        Arrays.asList("user_photos", "email", "user_birthday", "user_friends", "public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        if(Profile.getCurrentProfile() == null){
                            profileTracker = new ProfileTracker() {
                                @Override
                                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                    Log.e("kobe",currentProfile.getFirstName());

                                    name = currentProfile.getFirstName() +" "+ currentProfile.getMiddleName() + " " + currentProfile.getLastName();
                                    profileTracker.stopTracking();
                                    pushFacebookCred(loginResult.getAccessToken(),currentProfile);
                                }
                            };
                        } else{
                            profile = Profile.getCurrentProfile();
                            name = profile.getName();
                            Log.e("kobe","else"+name);

                            pushFacebookCred(loginResult.getAccessToken(),profile);
                        }
                       // pushFacebookCred(loginResult.getAccessToken());
                        }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

                Log.e("SHET", "yawa ning IT PROJECT!!!!!!");
                break;

            case R.id.buttonLogin:
                startActivity(new Intent(LoginActivity.this, SkillsActivity.class));
                break;
        }
    }


    public void pushFacebookCred(AccessToken accessToken, final Profile profile){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.i("LoginActivity", response.toString());
                // Get facebook data from login
                Log.e("kobe","json ob = "+object);
                try {
                    facebook_id = object.getString("id");
                     email = " ";
                    if(object.getString("email")!= null){
                        email = object.getString("email");
                    }


                    Log.e("SASDASDASD", "" + email + facebook_id + name);

                    StringRequest string = new StringRequest(Request.Method.POST, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    volunteer_id = response;
                                    Log.e("kobe","SHIT" +response);
                                    if(response.equals("First Time")){
                                        Log.e("kobe","sud sa if");
                                        startActivity(new Intent(LoginActivity.this, SkillsActivity.class));
                                        Log.e("kobe","kayata");
                                    }else{
                                        nextActivity(profile);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//                                    Log.e("kobe", error.getMessage());
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", email);
                            params.put("facebook_id", facebook_id);
                            params.put("name", name);
                            params.put("role", "volunteer");

                            return params;
                        }
                    };
                    RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                    request.add(string);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("kobe","jsonexception sa catch"+e.toString());

                    if(e.toString().equals("org.json.JSONException: No value for email")){

                        Log.e("SASDASDASD", "" +facebook_id +name);

                        StringRequest string = new StringRequest(Request.Method.POST, URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("First Time")){
                                            startActivity(new Intent(LoginActivity.this, SkillsActivity.class));
                                        }else{
                                            nextActivity(profile);
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    Log.e("kobe Error", error.toString());
                                        error.printStackTrace();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", "email not available "+facebook_id );
                                params.put("facebook_id", facebook_id);
                                params.put("name", name);
                                params.put("role", "volunteer");

                                Log.e("kobe","fb name = "+facebook_id + name);
                                return params;
                            }
                        };
                        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                        request.add(string);


                    }
                }

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();

    }
}
