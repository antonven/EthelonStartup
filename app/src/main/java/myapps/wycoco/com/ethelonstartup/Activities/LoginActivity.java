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
import com.android.volley.toolbox.JsonObjectRequest;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import myapps.wycoco.com.ethelonstartup.Activities.Register.RegisterActivity;
import myapps.wycoco.com.ethelonstartup.Adapters.LoginViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeActivitiesFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.LeaderBoardFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.NotificationsFragment;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
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
    String message, volunteerId, api_token;
    RequestQueue requestQueue;

    private String URL = "http://"+new Localhost().getLocalhost()+"/EthelonStartupWeb/public/api/loginwithfb";


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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }

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




                Map<String, String> params = new HashMap<String, String>();
                params.put("facebook_id", profile.getId());

                Log.e("kobe","id "+profile.getId());



        JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, "http://"+new Localhost().getLocalhost()+"/EthelonStartupWeb/public/api/loginwithfb",
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("kyle",response + " FUCKKKKKDSd");
                            message = response.getString("message");
                            volunteerId = response.getString("volunteer_id");
                            api_token = response.getString("api_token");
                            //Log.e("message",message + volunteerIdid + api_token);
                            nextActivity(profile);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("sud sa catch","sud sa catch");
                        }

                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e("kobe","error sa onreusme"+error.toString());
                }
            }) ;
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
        adapter.addFrag(new NotificationsFragment(), "Notifications");
        adapter.addFrag(new LeaderBoardFragment(), "Leaderboard");
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
            i.putExtra("volunteer_id",volunteerId);
            i.putExtra("api_token",api_token);

            Log.e("kobe","next act" + api_token + volunteerId);

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
               // startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                break;
        }
    }


    public void pushFacebookCred(AccessToken accessToken, final Profile profile){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.i("LoginActivity", response + " ");
                // Get facebook data from login
                Log.e("kobe","json ob = "+object);
                try {
                    facebook_id = object.getString("id");
                     email = " ";
                    if(object.getString("email")!= null){
                        email = object.getString("email");
                    }


                    Log.e("SASDASDASD", "" + email + facebook_id + name);



                    Map<String, String> params = new HashMap<String, String>();
                    Log.e("nelson"," FUCk!");
                    params.put("email", email);
                    params.put("facebook_id", facebook_id);
                    params.put("name", name);
                    params.put("role", "volunteer");
                    params.put("image_url","https://graph.facebook.com/"+ profile.getId() +"/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D");


                    Log.e("kyle","id "+profile.getId());
                    JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, "http://"+new Localhost().getLocalhost()+"/EthelonStartupWeb/public/api/loginwithfb",
                            new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("kyle",response + "piste ");
                                    try {
                                        message = response.getString("message");
                                        volunteer_id = response.getString("volunteer_id");
                                        api_token = response.getString("api_token");

                                        if(message.equals("First Time")){
                                            Intent intent = new Intent(LoginActivity.this,SkillsActivity.class);
                                            intent.putExtra("id",volunteer_id);
                                            intent.putExtra("api_token",api_token);
                                            startActivity(intent);

                                        }else{
                                            nextActivity(profile);
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.e("kyle",e.toString() + " naay email piste ");
                                        }

                                    }


                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("kyle",error.toString()+ " Ari gyud siya piste");
                        }
                    }) ;
                    RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                    request.add(string);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("kobe","jsonexception sa catch"+e.toString());

                    if(e.toString().equals("org.json.JSONException: No value for email")){

                            JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, "http://"+new Localhost().getLocalhost()+"/EthelonStartupWeb/public/api/loginwithfb",
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                            try {
                                                message = response.getString("message");
                                                volunteer_id = response.getString("volunteer_id");
                                                api_token = response.getString("api_token");

                                                if(message.equals("First Time")){
                                                    Intent intent = new Intent(LoginActivity.this,SkillsActivity.class);
                                                    intent.putExtra("id",volunteer_id);
                                                    intent.putExtra("api_token",api_token);
                                                    startActivity(intent);
                                                }else{
                                                    nextActivity(profile);
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("email", "Email Not Available"+facebook_id);
                                    params.put("facebook_id", facebook_id);
                                    params.put("name", name);
                                    params.put("role", "volunteer");
                                    params.put("image_url","https://graph.facebook.com/"+ profile.getId() +"/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D");

                                    Log.e("kobe","id "+profile.getId());
                                    return params;
                                }
                            };
                            RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                            request.add(string);

                    }
                }

            }//on completed
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();

    }
}
