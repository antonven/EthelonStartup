package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import myapps.wycoco.com.ethelonstartup.Adapters.BasicAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;

public class SkillPreferenceActivity extends AppCompatActivity {

    private static final String skillsUrl = "http://" + new Localhost().getLocalhost() + "getSkills";

    Button doneButton;
    ImageView environmentCheck, livelihoodCheck, culinaryCheck, charityCheck, sportsCheck, educationalCheck, medicineCheck, artsCheck;
    GridView gridView;
    ImageView environmental, livelihood, educational, culinary, charity, sports, medicine, arts;
    String volunteer_id, fbProfilePicture, fbProfileName;
    String api_token, profileId, profilePicture;
    String name;
    ArrayList<String> skill_set;
    ProgressBar progbar;
    SessionManager sessionManager;
    ArrayList<String> skillz = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_preference);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }

        init();

        Intent n = getIntent();
        skill_set = new ArrayList<>();
        skill_set = n.getStringArrayListExtra("skill_set");
//        Log.e("skillpref", skill_set.toString());
        fetchSkillPreference();

    }

    public void init(){
        environmentCheck = (ImageView)findViewById(R.id.environmentCheck);
        livelihoodCheck = (ImageView)findViewById(R.id.livelihoodCheck);
        culinaryCheck = (ImageView)findViewById(R.id.culinaryCheck);
        charityCheck = (ImageView)findViewById(R.id.charityCheck);
        sportsCheck = (ImageView)findViewById(R.id.sportsCheck);
        educationalCheck = (ImageView)findViewById(R.id.educationalCheck);
        medicineCheck = (ImageView)findViewById(R.id.medicineCheck);
        artsCheck = (ImageView)findViewById(R.id.artsCheck);
        doneButton = (Button)findViewById(R.id.doneButton);
        gridView = (GridView)findViewById(R.id.gridView);
        environmental = (ImageView)findViewById(R.id.environmental);
        livelihood = (ImageView)findViewById(R.id.livelihood);
        educational = (ImageView)findViewById(R.id.educational);
        culinary = (ImageView)findViewById(R.id.culinary);
        charity = (ImageView)findViewById(R.id.charity);
        sports = (ImageView)findViewById(R.id.sports);
        medicine = (ImageView)findViewById(R.id.medicine);
        arts = (ImageView)findViewById(R.id.arts);
        progbar = (ProgressBar)findViewById(R.id.progbar);

//        environmental.setOnClickListener(this);
//        livelihood.setOnClickListener(this);
//        culinary.setOnClickListener(this);
//        educational.setOnClickListener(this);
//        charity.setOnClickListener(this);
//        sports.setOnClickListener(this);
//        medicine.setOnClickListener(this);
//        arts.setOnClickListener(this);
//        doneButton.setOnClickListener(this);
    }


    public void fetchSkillPreference(){
        Intent intent = getIntent();
        String volunteer_id = intent.getStringExtra("volunteer_id");
        String api_token = intent.getStringExtra("api_token");

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id",volunteer_id);
        params.put("api_token",api_token);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, skillsUrl, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try {

                                Log.e("NISUD_SKILLZ", response.toString());
                                JSONObject jsonObject = response.getJSONObject(i);
                                String skill = jsonObject.getString("name");

                                if(skill.equals("environment")){
                                    environmentCheck.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("medical")){
                                    medicineCheck.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("arts")){
                                    artsCheck.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("sports")){
                                    sportsCheck.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("livelihood")){
                                    livelihood.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("culinary")){
                                    culinaryCheck.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("charity")){
                                    charityCheck.setVisibility(View.VISIBLE);
                                }
                                if(skill.equals("education")){
                                    educationalCheck.setVisibility(View.VISIBLE);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("animal", e.toString());
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("animalsss", error.toString());

            }
        });

        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonArrayRequest);


    }


}
