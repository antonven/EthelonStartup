package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Activities.Register.RegisterActivity;
import myapps.wycoco.com.ethelonstartup.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    CallbackManager callbackManager;
    TextView signUp;
    EditText inputEmail, inputPassword;
    Button buttonLogin, buttonFacebook, buttonSignup ;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    FragmentManager fm;
    VideoView vidView;
    Profile profile;
    Uri uri;
    RequestQueue requestQueue;
    private String URL = "http://172.17.0.138/EthelonStartupWeb/public/api/register";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        setTitle("");


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparent));

        callbackManager = CallbackManager.Factory.create();

        vidView = (VideoView)findViewById(R.id.videoView);
        buttonSignup = (Button)findViewById(R.id.buttonEthelonSignUp);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonFacebook = (Button)findViewById(R.id.buttonFacebook);

        uri = Uri.parse("android.resources://" + getPackageName()+ "/" + R.raw.vid);
        vidView.setVideoURI(uri);
        vidView.start();

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
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        profile = Profile.getCurrentProfile();
        nextActivity(profile);
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

    private void nextActivity(Profile profile){
        if(profile!= null){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            i.putExtra("profileName", profile.getName());
            i.putExtra("profilePicture", profile.getProfilePictureUri(500,500).toString());
            i.putExtra("profileId", profile.getId());
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
                LoginFacebook();


                break;

            case R.id.buttonLogin:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                break;
        }
    }

    public void LoginFacebook(){


        LoginManager.getInstance().logOut();
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                profile = Profile.getCurrentProfile();
                String accesToken = loginResult.getAccessToken().getToken();
                nextActivity(profile);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        try {
                            String email = object.getString("email");
                            String facebook_id = object.getString("id");
                            String name = profile.getName();

                            requestRegisterPost(email, facebook_id, name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                request.setParameters(parameters);
                request.executeAsync();


            }
            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
    }

    public void requestRegisterPost(final String email, final String facebook_id, final String name) {
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


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("facebook_id", facebook_id);
                params.put("name", name);


                return params;
            }
        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(string);

    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = null;
            try {
                id = object.getString("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        }
        catch(JSONException e) {
            Log.d("shet","Error parsing JSON");
        }
        return null;
    }
}
