package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;

import myapps.wycoco.com.ethelonstartup.Activities.Register.RegisterActivity;
import myapps.wycoco.com.ethelonstartup.Adapters.LoginViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.ViewPagerAdapter;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeActivitiesFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.LeaderBoardFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.NotificationsFragment;
import myapps.wycoco.com.ethelonstartup.Models.BusStation;
import myapps.wycoco.com.ethelonstartup.Models.Config;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserCredentials;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;
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
    String name, facebook_id, email, profile_id, profileName, first_name, last_name, profilePicture;
    String volunteer_id;
    String message, api_token;
    TextView text;
    ArrayList<UserModel> emails = new ArrayList<>();

    private static  final String URL = "http://"+new Localhost().getLocalhost()+"loginwithfb";
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        setTitle("");
//        Window window = this.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
//        }

        callbackManager = CallbackManager.Factory.create();
        buttonSignup = (Button)findViewById(R.id.buttonEthelonSignUp);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        text = (TextView)findViewById(R.id.alreadAUserLabel);
        buttonFacebook = (Button)findViewById(R.id.buttonFacebook);
        vp = (ViewPager)findViewById(R.id.viewPagerText);
        Log.e("FirebaseKobe","outside onrecieve");
        System.out.print("outside on recieve");
        Toast.makeText(this, "Fuck this hist", Toast.LENGTH_SHORT).show();


        viewPager = new LoginViewPagerAdapter(this);
        vp.setAdapter(viewPager);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutomaticSlide(), 2000, 4000);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "Roboto-Black.ttf");

        buttonSignup.setTypeface(typeface);
        buttonLogin.setTypeface(typeface);
        buttonFacebook.setTypeface(typeface);
        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);




        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "inside on recieve", Toast.LENGTH_SHORT).show();
                Log.e("FirebaseKobe","inside onrecieve");
                System.out.print("inside on recieve inatay");
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    //txtMessage.setText(message);

                    Log.e("FirebaseKobe",message + " mgreg broadcastreciever");
                }
            }
        };

        accestToken();
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);
        Toast.makeText(this, "reg id = "+regId, Toast.LENGTH_SHORT).show();

        if (!TextUtils.isEmpty(regId)) {
            Log.d("No logs", regId);
            System.out.printf(regId);
            text.setText(regId + "");
        }
        else
           text.setText("wala pa daw ");
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
            params.put("fcm_token",FirebaseInstanceId.getInstance().getToken());
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
                                startActivity(intent);

                            }else if(message.equals("Email already exists")){
                                Toast.makeText(LoginActivity.this, "Email already exists! Try another email", Toast.LENGTH_SHORT).show();

                            }else{
                                volunteer_id = response.getString("volunteer_id");
                                api_token = response.getString("api_token");
                                BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));
                                Log.e("BUS STATION", "CREDENTIALS" + BusStation.getBus());
                                nextActivity(profile);
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
                        }else{
                            profile = Profile.getCurrentProfile();
                            name = profile.getName();
                            Log.e("kobe","else"+name);
                            pushFacebookCred(loginResult.getAccessToken(),profile);
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

                try {
                    facebook_id = object.getString("id");
                    email = " ";
                    profilePicture = profile.getProfilePictureUri(500,500).toString();

                    if(object.getString("email")!= null){
                        email = object.getString("email");
                        userModel = new UserModel();
                        userModel.setUser_id(email);
                        emails.add(userModel);
                    }else{
                        email = "email not avaiable " + facebook_id;
                    }

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("facebook_id", facebook_id);
                    params.put("name", name);
                    params.put("role", "volunteer");
                    params.put("image_url",profilePicture);
                    params.put("fcm_token", FirebaseInstanceId.getInstance().getToken());

                    JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {
                                        message = response.getString("message");

                                        if(message.equals("First Time")) {

                                            String profilePicture = profile.getProfilePictureUri(500,500).toString();
                                            String profile_id = profile.getId();
                                            String profileName = profile.getName();
                                            Intent intent = new Intent(LoginActivity.this, SkillsActivity.class);

                                            volunteer_id = response.getString("volunteer_id");
                                            api_token = response.getString("api_token");



                                            intent.putExtra("volunteer_id", volunteer_id);
                                            intent.putExtra("api_token", api_token);
                                            intent.putExtra("profileId", profile_id);
                                            intent.putExtra("fbProfilePicture", profilePicture);
                                            intent.putExtra("fbProfileName", profileName);
                                            intent.putExtra("email", email);
                                            startActivity(intent);

                                        }else if(message.equals("Email already exists")){
                                            Toast.makeText(LoginActivity.this, "Email already exists! Try another email", Toast.LENGTH_SHORT).show();

                                        }else{
                                            volunteer_id = response.getString("volunteer_id");
                                            api_token = response.getString("api_token");

                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("volunteer_id",volunteer_id);
                                            params.put("api_token",api_token);

                                            JsonObjectRequest notif = new JsonObjectRequest(Request.Method.POST, "http://" + new Localhost().getLocalhost() + "checkNotif", new JSONObject(params), new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                     String msg;
                                                    try {
                                                        msg = response.getString("message");
                                                        if(msg.equals("good")){
                                                            nextActivity(profile);
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                    Log.e("line 469 login act","error " + error.toString());
                                                }
                                            }
                                            );
                                            RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                                            request.add(notif);
                                            BusStation.getBus().post(new UserCredentials(api_token, volunteer_id));

                                        }
                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.e("line 444","catch");
                                    }

                                }

                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("kyle",error.toString()+ " Ari gyud siya piste");
                                }
                            });
                    RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                    request.add(string);

                } catch (JSONException e) {
                    e.printStackTrace();

                    if(e.toString().equals("org.json.JSONException: No value for email")){
                        profilePicture = profile.getProfilePictureUri(500,500).toString();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", "Email Not Available"+facebook_id);
                        params.put("facebook_id", facebook_id);
                        params.put("name", name);
                        params.put("role", "volunteer");
                        params.put("image_url",profilePicture);
                        params.put("fcm_token",FirebaseInstanceId.getInstance().getToken());

                        JsonObjectRequest string = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                            try {
                                                message = response.getString("message");

                                                if(message.equals("First Time")) {
                                                    String profilePicture = profile.getProfilePictureUri(500,500).toString();
                                                    String profileName = profile.getName();
                                                    Intent intent = new Intent(LoginActivity.this, SkillsActivity.class);
                                                    volunteer_id = response.getString("volunteer_id");
                                                    api_token = response.getString("api_token");
                                                    intent.putExtra("volunteer_id", volunteer_id);
                                                    intent.putExtra("api_token", api_token);
                                                    intent.putExtra("profileId", profile_id);
                                                    intent.putExtra("fbProfilePicture", profilePicture);
                                                    intent.putExtra("fbProfileName", profileName);
                                                    intent.putExtra("email", email);
                                                    startActivity(intent);

                                                }else if(message.equals("Email already exists")){
                                                    Toast.makeText(LoginActivity.this, "Email already exists! Try another email", Toast.LENGTH_SHORT).show();

                                                }else{
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
