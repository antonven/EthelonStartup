package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Fragments.BadgeUpdateDialogFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.ConfirmDialogFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.VolunteerRatingFragment;
import myapps.wycoco.com.ethelonstartup.Models.BadgeUpdateModel;
import myapps.wycoco.com.ethelonstartup.Models.Config;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;

public class EvaluateGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL2 = "http://" + new Localhost().getLocalhost() + "attendanceactivity";
    Button submit;
    String email, fbProfileName,fbProfilePicture,profileId;
    ArrayList<BadgeUpdateModel>results;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_group);
        results = new ArrayList<>();

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

        submit = (Button)findViewById(R.id.submitBtn);
        submit.setOnClickListener(this);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("Notification Fragment", "inside onrecieve");
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    //displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    //txtMessage.setText(message);

                    Log.e("evaluategroupActivity", message + " mgreg broadcastreciever");
                }
            }
        };

        String api_token = getIntent().getStringExtra("api_token");
        String activity_id = getIntent().getStringExtra("activity_id");
        String volunteer_id = getIntent().getStringExtra("volunteer_id");

        VolunteerRatingFragment ratingFragment = new VolunteerRatingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("api_token", api_token);
        bundle.putString("activity_id", activity_id);
        bundle.putString("volunteer_id", volunteer_id);
        ratingFragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.evaluateGroupFrame, ratingFragment)
                .commit();

    }

    @Override
    public void onClick(View view) {
        String api_token = getIntent().getStringExtra("api_token");
        String activity_id = getIntent().getStringExtra("activity_id");
        String volunteer_id = getIntent().getStringExtra("volunteer_id");

        switch (view.getId()){
            case R.id.submitBtn:
                submitAttendance();
                break;

        }

    }

    private void starNextActivity() {

        final String api_token = getIntent().getStringExtra("api_token");
        final String volunteer_id =  getIntent().getStringExtra("volunteer_id");
        final String activity_id=  getIntent().getStringExtra("activity_id");


        SharedPreferences shared = this.getSharedPreferences("HOME_INFO", MODE_PRIVATE);
        email = shared.getString("email", "");
        fbProfileName = shared.getString("fbProfileName", "");
        fbProfilePicture = shared.getString("fbProfilePicture", "");
        profileId = shared.getString("profileId", "");

        Intent n = new Intent(this, HomeActivity.class);
        n.putExtra("api_token", api_token);
        n.putExtra("volunteer_id", volunteer_id);
        n.putExtra("activity_id", activity_id);
        n.putExtra("fbProfileName", fbProfileName);
        n.putExtra("fbProfilePicture", fbProfilePicture);
        n.putExtra("profileId", profileId);
        n.putExtra("profileId", email);
        Log.e("SHITPREFERENCES", api_token+ volunteer_id + activity_id + fbProfileName +fbProfilePicture + "");

        startActivity(n);
    }

    public void submitAttendance(){

        String api_token = getIntent().getStringExtra("api_token");
        String activity_id = getIntent().getStringExtra("activity_id");
        String volunteer_id = getIntent().getStringExtra("volunteer_id");
        Log.e("RESPONSE_UPDATE_SUD", "asdasd nisud diri");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL2, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        Log.e("RESPONSE_UPDATE", response.toString());
                        for(int i = 0; i<response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String update = jsonObject.getString("update");
                                String body = jsonObject.getString("body");


                                if(update.equals("nothing")){

                                    String api_token = getIntent().getStringExtra("api_token");
                                    String activity_id = getIntent().getStringExtra("activity_id");
                                    String volunteer_id = getIntent().getStringExtra("volunteer_id");

                                    BadgeUpdateModel badgeUpdateModel = new BadgeUpdateModel(update,body,null,null,null);
                                    //results.add(badgeUpdateModel);

                                }else if(update.equals("new badge")){

                                    JSONObject obj1 = jsonObject.getJSONObject("BadgeInfo");
                                    String badge_rank = obj1.getString("badge");
                                    String image_url = obj1.getString("url");
                                    String badge_name = obj1.getString("badge_name");

                                    BadgeUpdateModel badgeUpdateModel = new BadgeUpdateModel(update,body,badge_rank,image_url,badge_name);
                                    results.add(badgeUpdateModel);

                                    Toast.makeText(EvaluateGroupActivity.this, "NAAY BAG.O BADGE", Toast.LENGTH_SHORT).show();

                                }else if(update.equals("new star")){

                                    JSONObject obj1 = jsonObject.getJSONObject("BadgeInfo");
                                    String badge_rank = obj1.getString("badge");
                                    String image_url = obj1.getString("url");
                                    String badge_name = obj1.getString("badge_name");

                                    BadgeUpdateModel badgeUpdateModel = new BadgeUpdateModel(update,body,badge_rank,image_url,badge_name);
                                    results.add(badgeUpdateModel);

                                    Log.e("PISTING YAWA","nisud sa new star");

                                }else{
                                    Log.e("pisting yawa","kayata naa sa else");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if(i == response.length()-1){
                                Log.e("response length",response.length()+"");
                                badgeStart(response.length());
                            }

                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE_UPDATE_ERR", error.toString());

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void badgeStart(int count) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(results.size()>0){

            BadgeUpdateDialogFragment badgeUpdateDialogFragment = new BadgeUpdateDialogFragment();
            Bundle bundle = new Bundle();
            String badge_rank = results.get(0).getBadge();
            bundle.putString("badge_rank", results.get(0).getBadge());
            bundle.putString("image_url", results.get(0).getUrl());
            bundle.putString("body", results.get(0).getBody());
            bundle.putString("update",results.get(0).getUpdate());
            bundle.putString("badge_name",results.get(0).getBadge_name());
            bundle.putInt("count",0);
            count = count - 1;
            bundle.putInt("size",results.size());

            // results.remove(0);

            bundle.putSerializable("array",results);
            badgeUpdateDialogFragment.setArguments(bundle);
            badgeUpdateDialogFragment.show(fragmentManager,"Badge");

        }else{
            //Ton butangig pina success diri

            starNextActivity();

        }



    }


//        @Override
//        public void onChoose() { finish(); }


}
