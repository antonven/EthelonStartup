package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import myapps.wycoco.com.ethelonstartup.Activities.Register.RegisterActivity;
import myapps.wycoco.com.ethelonstartup.Adapters.LoginViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Models.BusStation;
import myapps.wycoco.com.ethelonstartup.Models.Config;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserCredentials;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;
import myapps.wycoco.com.ethelonstartup.Utils.NotificationUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    CallbackManager callbackManager;
    EditText inputEmail, inputPassword;
    Button buttonLogin, buttonFacebook, buttonSignup ;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    Profile profile;
    UserModel userModel;
    ViewPager vp;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    LoginViewPagerAdapter viewPager;
    String name, facebook_id, email, profile_id, profilePicture;
    String volunteer_id;
    String message, api_token;
    TextView text, loginText;
    Spinner loginAsSpinner;
    String fcm_token;
    ProgressBar progressBar;
    ArrayList<UserModel> emails = new ArrayList<>();
    SessionManager session;
    ArrayList<String> roles = new ArrayList<>();

    private static final String NOTIFURL = "http://" + new Localhost().getLocalhost() + "checkNotif";
    private static  final String URL = "http://"+new Localhost().getLocalhost()+"loginwithfb";
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        setTitle("");

        callbackManager = CallbackManager.Factory.create();
        buttonSignup = (Button)findViewById(R.id.buttonEthelonSignUp);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
//        loginAsSpinner = (Spinner)findViewById(R.id.buttonLogin);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        text = (TextView)findViewById(R.id.alreadAUserLabel);
        buttonFacebook = (Button)findViewById(R.id.buttonFacebook);
        vp = (ViewPager)findViewById(R.id.viewPagerText);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loginText = (TextView)findViewById(R.id.loginText);


        roles.add("as Volunteer");
        roles.add("as Foundation");
        
        displayFirebaseRegId();

        viewPager = new LoginViewPagerAdapter(this);
        vp.setAdapter(viewPager);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutomaticSlide(), 2000, 4000);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "Roboto-Black.ttf");
        Typeface typeface2 = Typeface.createFromAsset(this.getAssets(), "Rancho-Regular.ttf");

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        buttonSignup.setTypeface(typeface);
        buttonLogin.setTypeface(typeface);
        buttonFacebook.setTypeface(typeface);
        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);

        session = new SessionManager(getApplicationContext());

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("Fire base", "inside onrecieve");
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    //txtMessage.setText(message);

                    Log.e("Fire baseloginactivity", message + " mgreg broadcastreciever");
                }
            }
        };

        accestToken();
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        fcm_token = regId;
        Log.e(TAG, "Fire base reg_id: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            Log.d("No logs", regId);
            System.out.printf(regId);
//            text.setText(regId + "");
        }
//        else
//           text.setText("wala pa daw ");
    }


    public void accestToken(){
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

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());

        if(profile!=null) {

            Map<String, String> params = new HashMap<String, String>();
            params.put("facebook_id", profile.getId());
            if(fcm_token != null){
                params.put("fcm_token",fcm_token);
            }else{
                params.put("fcm_token",FirebaseInstanceId.getInstance().getToken());
            }

            Log.e("LOGIN ACTIVITY","facebook_id "+profile.getId());

            JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            message = response.getString("message");
                            Log.e("onResume LOGIN", "facebook_id & message " + profile.getId() + response.getString("message"));
                            Log.e("line 245"," iste = " + FirebaseInstanceId.getInstance().getToken());
                            if(message.equals("First Time")) {

                                volunteer_id = response.getString("volunteer_id");
                                api_token = response.getString("api_token");
                                profilePicture = profile.getProfilePictureUri(500,500).toString();
                                Intent intent = new Intent(LoginActivity.this, SkillsActivity.class);
                                intent.putExtra("volunteer_id", volunteer_id);
                                intent.putExtra("api_token", api_token);
                                intent.putExtra("profileId", profile_id);
                                intent.putExtra("image_url", profilePicture);
                                intent.putExtra("email", email);
                                Log.e("LOGIN ACTIVITY","facebook_id "+profile_id + email + "proPIC"+ profilePicture);
//                                session.createLoginSession(email, profile.getName(), profilePicture, profile_id, profile.getFirstName(), profile.getLastName(), volunteer_id, api_token);
                                startActivity(intent);

                            }else if(message.equals("Email already exigsts")){
                                Toast.makeText(LoginActivity.this, "Email already exists! Try another email", Toast.LENGTH_SHORT).show();

                            }else{
                                volunteer_id = response.getString("volunteer_id");
                                api_token = response.getString("api_token");
                                //Log.e("NOTIFICATIONGIATAY",getIntent().getExtras().getString("activity_id"));


                                try{
                                    if (getIntent().getExtras().getString("activity_id") != null ) {
                                        // Log.e("NOTIFICATION PISTE",getIntent().getExtras().getString("activity") + " = = "+getIntent().getExtras().getString("volunteersToRate"));
                                        Log.e("NOTIFICATIONGIATAY",getIntent().getExtras().getString("activity_id"));
                                        Intent in = new Intent(LoginActivity.this, PortfolioEventDetailsActivity.class);
                                        in.putExtra("eventImage", getIntent().getExtras().getString("eventImage"));
                                        in.putExtra("eventName", getIntent().getExtras().getString("eventName"));
                                        in.putExtra("eventHost", getIntent().getExtras().getString("eventHost"));
                                        in.putExtra("eventDate", getIntent().getExtras().getString("eventDate"));
                                        in.putExtra("eventTimeStart",getIntent().getExtras().getString("eventTimeStart") );
                                        in.putExtra("eventLocation", getIntent().getExtras().getString("eventLocation") );
                                        in.putExtra("contactNo", getIntent().getExtras().getString("eventContactNo") );
                                        in.putExtra("contactPerson",  getIntent().getExtras().getString("eventContactPerson"));
                                        in.putExtra("eventPoints",getIntent().getExtras().getString("eventSkills") );
                                        in.putExtra("activity_id", getIntent().getExtras().getString("activity_id"));
                                        in.putExtra("api_token", api_token);
                                        in.putExtra("profileId", profile_id);
                                        in.putExtra("volunteer_id", volunteer_id);
                                        in.putExtra("indicator","yes");

                                        startActivity(in);

                                    }else{
                                        BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));
                                        Log.e("BUS STATION", "CREDENTIALS" + BusStation.getBus());
                                        nextActivity(profile);
                                    }


                                }catch (Exception e){
                                    BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));
                                    Log.e("BUS STATION", "CREDENTIALS" + BusStation.getBus());
                                    nextActivity(profile);
                                }

                            }

                        }catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("sud sa catch","sud sa catch");
                        }

                    }},

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("OnResumeError",""+error.toString());
                        }
                    });

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
            SharedPreferences shared = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);
            String email = shared.getString("email", "");
            String fbProfileName = profile.getName();
            String fbProfilePicture = profile.getProfilePictureUri(500,500).toString();
            String profileId = profile.getId();
            String first_name = profile.getFirstName();
            String last_name = profile.getLastName();

            session.createLoginSession(email, fbProfileName, fbProfilePicture, profileId, first_name, last_name, volunteer_id, api_token);


//            SharedPreferences pref = getApplicationContext().getSharedPreferences("HOME_INFO", MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//
//            editor.commit();

            BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            i.putExtra("fbProfileName", profile.getName());
            i.putExtra("fbProfilePicture", profile.getProfilePictureUri(500,500).toString());
            i.putExtra("profileId", profile.getId());
            i.putExtra("first_name", profile.getFirstName());
            i.putExtra("last_name", profile.getLastName());
            i.putExtra("volunteer_id",volunteer_id);
            i.putExtra("api_token",api_token);
            i.putExtra("email", email);
            Log.e("NEXT ACT","EMAIL" + email + volunteer_id);
            startActivity(i);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonEthelonSignUp:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

            case R.id.buttonFacebook:
                progressBar.setVisibility(View.VISIBLE);
                Log.e("sud sa facebook button","facebook");
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
                                    finish();
                                    progressBar.setVisibility(View.GONE);
                                }
                            };
                        }else{
                            profile = Profile.getCurrentProfile();
                            name = profile.getName();
                            Log.e("kobe","else"+name);
//                            pushFacebookCred(loginResult.getAccessToken(),profile);

                        }
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
                break;

            case R.id.buttonLogin:
                startActivity(new Intent(LoginActivity.this, LoginWithEthelonActivity.class));
                break;
        }
    }




    public void pushFacebookCred(AccessToken accessToken, final Profile profile){


        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if(response!=null) {
                    try {
                        facebook_id = object.getString("id");
                        email = " ";
                        profilePicture = profile.getProfilePictureUri(500, 500).toString();

                        if (object.getString("email") != null) {
                            email = object.getString("email");

                            SharedPreferences pref = getApplicationContext().getSharedPreferences("SHARED_PREF", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("email", email);
                            editor.commit();
                            userModel = new UserModel();
                            userModel.setUser_id(email);
                            emails.add(userModel);
                        } else {
                            email = "email not avaiable " + facebook_id;
                        }

//                        progressBar.setVisibility(View.VISIBLE);

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("facebook_id", facebook_id);
                        params.put("name", name);
                        params.put("role", "volunteer");
                        params.put("image_url", profilePicture);
                        if(fcm_token != null){
                            params.put("fcm_token",fcm_token);
                        }else{
                            params.put("fcm_token",FirebaseInstanceId.getInstance().getToken());
                        }

                        JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                    if(response!=null){
                                        try {
                                            message = response.getString("message");

                                            if (message.equals("First Time")) {

                                                String profilePicture = profile.getProfilePictureUri(500, 500).toString();
                                                String profile_id = profile.getId();
                                                String profileName = profile.getName();
                                                session.createLoginSession(email, profileName, profilePicture, profile_id, profile.getFirstName(), profile.getLastName(), volunteer_id, api_token);
                                                Intent intent = new Intent(LoginActivity.this, SkillsActivity.class);

                                                volunteer_id = response.getString("volunteer_id");
                                                api_token = response.getString("api_token");

                                                intent.putExtra("id", volunteer_id);
                                                intent.putExtra("api_token", api_token);
                                                intent.putExtra("profileId", profile_id);
                                                intent.putExtra("fbProfilePicture", profilePicture);
                                                intent.putExtra("fbProfileName", profileName);
                                                intent.putExtra("email", email);
                                                startActivity(intent);


                                            } else if (message.equals("Email already exists")) {
                                                Toast.makeText(LoginActivity.this, "Email already exists! Try another email", Toast.LENGTH_SHORT).show();

                                            } else {
                                                volunteer_id = response.getString("volunteer_id");
                                                api_token = response.getString("api_token");

                                                Map<String, String> params = new HashMap<String, String>();
                                                params.put("volunteer_id", volunteer_id);
                                                params.put("api_token", api_token);

                                                JsonObjectRequest notification = new JsonObjectRequest(Request.Method.POST, NOTIFURL, new JSONObject(params),
                                                        new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                String msg;
                                                                try {
                                                                    msg = response.getString("message");
                                                                    if (msg.equals("good")) {
                                                                        nextActivity(profile);
                                                                    }
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {

                                                        Log.e("line 469 login act", "error " + error.toString());
                                                    }
                                                }
                                                );
                                                notification.setRetryPolicy(new DefaultRetryPolicy(
                                                        5000,
                                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                                                ));
                                                RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                                                request.add(notification);
                                                BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.e("line 444", "catch");
                                        }
                                        }
//                                        progressBar.setVisibility(View.GONE);
                                    }

                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("kyle", error.toString() + " Ari gyud siya piste");
                            }
                        });
                        string.setRetryPolicy(new DefaultRetryPolicy(
                                5000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                        ));
                        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                        request.add(string);

                    } catch (JSONException e) {
                        e.printStackTrace();

                        if (e.toString().equals("org.json.JSONException: No value for email")) {
                            profilePicture = profile.getProfilePictureUri(500, 500).toString();
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", "Email Not Available" + facebook_id);
                            params.put("facebook_id", facebook_id);
                            params.put("name", name);
                            params.put("role", "volunteer");
                            params.put("image_url", profilePicture);
                            if(fcm_token != null){
                                params.put("fcm_token",fcm_token);
                            }else{
                                params.put("fcm_token",FirebaseInstanceId.getInstance().getToken());
                            }

                            JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                            try {
                                                message = response.getString("message");

                                                if (message.equals("First Time")) {
                                                    String profilePicture = profile.getProfilePictureUri(500, 500).toString();
                                                    String profileName = profile.getName();
                                                    Intent intent = new Intent(LoginActivity.this, SkillsActivity.class);
                                                    volunteer_id = response.getString("volunteer_id");
                                                    api_token = response.getString("api_token");
                                                    intent.putExtra("id", volunteer_id);
                                                    intent.putExtra("api_token", api_token);
                                                    intent.putExtra("profileId", profile_id);
                                                    intent.putExtra("fbProfilePicture", profilePicture);
                                                    intent.putExtra("fbProfileName", profileName);
                                                    intent.putExtra("email", email);
                                                    startActivity(intent);

                                                } else if (message.equals("Email already exists")) {
                                                    Toast.makeText(LoginActivity.this, "Email already exists! Try another email", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    volunteer_id = response.getString("volunteer_id");
                                                    api_token = response.getString("api_token");
                                                    BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));
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
                            });
                            string.setRetryPolicy(new DefaultRetryPolicy(
                                    5000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                            ));
                            RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                            request.add(string);

                        }
                    }
                }
//                progressBar.setVisibility(View.GONE);

            }//on completed
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
