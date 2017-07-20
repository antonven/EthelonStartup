package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
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
import android.widget.Toast;

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
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import myapps.wycoco.com.ethelonstartup.R;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    TextView signUp;
    EditText inputEmail, inputPassword;
    Button buttonLogin, buttonGoogle ;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    FragmentManager fm;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.signature2Color));

        callbackManager = CallbackManager.Factory.create();
        LoginButton buttonFacebook = (LoginButton) findViewById(R.id.buttonFacebook);

        signUp = (TextView)findViewById(R.id.buttonSignup);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
//        buttonGoogle = (Button)findViewById(R.id.buttonGoogle);

        buttonFacebook.setReadPermissions(Arrays.asList("user_status"));

//        buttonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                loginResult.getAccessToken().getUserId();
//                loginResult.getAccessToken().getToken();
//                Profile profile = Profile.getCurrentProfile();
//                String name = profile.getName();
//                String imageUrl = profile.getProfilePictureUri(200, 200).toString();
//                String id = profile.getId();
//                Toast.makeText(LoginActivity.this, "" + name
//                        , Toast.LENGTH_SHORT).show();
//                Log.e("HI!", imageUrl + id);
//
//                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
//                i.putExtra("profileName", name);
//                i.putExtra("profilePicture", imageUrl);
//                i.putExtra("profileId", id);
//                startActivity(i);
//
//
////                startActivity(new Intent(LoginActivity.this, HomeActivity.class), args);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });

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

       FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
           @Override
           public void onSuccess(LoginResult loginResult) {
               Profile profile = Profile.getCurrentProfile();
               nextActivity(profile);


               new GraphRequest(
                       AccessToken.getCurrentAccessToken(), "/{user-id}/friends", null, HttpMethod.GET, new GraphRequest.Callback() {
                   @Override
                   public void onCompleted(GraphResponse response) {


//                           try {
//
//                               String shit = response.getJSONArray("data");
//                               Log.e("SHET", shit);
//                           } catch (JSONException e) {
//                               e.printStackTrace();
//                           }

                   }
               }
               ).executeAsync();


           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onError(FacebookException error) {

           }
       };
        buttonFacebook.setReadPermissions("user_friends");
        buttonFacebook.registerCallback(callbackManager, callback);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });



//        buttonGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
//        startActivity(new Intent(this, HomeActivity.class));
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
            i.putExtra("profilePicture", profile.getProfilePictureUri(200,200).toString());
            i.putExtra("profileId", profile.getId());
            startActivity(i);
        }
    }
}
