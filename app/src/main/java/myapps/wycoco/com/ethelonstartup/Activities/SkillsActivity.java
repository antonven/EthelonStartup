package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.Snackbar;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;

public class SkillsActivity extends AppCompatActivity implements View.OnClickListener {

    Button doneButton;
    ImageView environmentCheck, livelihoodCheck, culinaryCheck, charityCheck, sportsCheck, educationalCheck, medicineCheck, artsCheck;
    GridView gridView;
    ImageView environmental, livelihood, educational, culinary, charity, sports, medicine, arts;
    String volunteer_id, fbProfilePicture, fbProfileName;
    String api_token, profileId, profilePicture;
    String name;
    ArrayList<String> skillSet = new ArrayList<>();
    ProgressBar progbar;
    int count1=0, count2=0, count3=0, count4=0, count5=0, count6=0, count7=0, count8=0;
    SessionManager sessionManager;
    Localhost localhost = new Localhost();
    private String URL = "http://"+localhost.getLocalhost()+"volunteerskills";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skills_interests_choice);
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

        Snackbar.make(findViewById(R.id.skillsRelative) , "PLEASE CHOOSE ONE OR MORE SKILLS AND INTERESTS", Snackbar.LENGTH_LONG).show();

        Intent n = getIntent();
        volunteer_id = n.getStringExtra("id");

        api_token = n.getStringExtra("api_token");
        name = n.getStringExtra("profileName");
        profileId = n.getStringExtra("profileId");
        fbProfilePicture = n.getStringExtra("fbProfilePicture");
        profilePicture = n.getStringExtra("image_url");
        fbProfileName = n.getStringExtra("fbProfileName");

        Log.e("skillsActivityline65",volunteer_id + api_token + name + profileId + fbProfilePicture + profilePicture + fbProfileName);

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

        environmental.setOnClickListener(this);
        livelihood.setOnClickListener(this);
        culinary.setOnClickListener(this);
        educational.setOnClickListener(this);
        charity.setOnClickListener(this);
        sports.setOnClickListener(this);
        medicine.setOnClickListener(this);
        arts.setOnClickListener(this);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {



        switch (view.getId()){

            case R.id.environmental:
                if(count1%2==0) {
                    environmentCheck.setVisibility(View.VISIBLE);
                    skillSet.add("environment");
                    count1++;
                }
                else if (count1%2!=0) {
                    environmentCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("environment")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count1++;
                }
                Log.e("shet", "" + skillSet + skillSet.size());
                break;

            case R.id.livelihood:
                if(count2%2==0) {
                    livelihoodCheck.setVisibility(View.VISIBLE);
                    skillSet.add("livelihood");
                    count2++;
                }
                else if (count2%2!=0) {
                    livelihoodCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("livelihood")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count2++;
                }
                Log.e("shet", "" + skillSet);
                break;

            case R.id.educational:
                if(count3%2==0) {
                    educationalCheck.setVisibility(View.VISIBLE);
                    skillSet.add("education");
                    count3++;
                }
                else if (count3%2!=0) {
                    educationalCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("education")) {
                            skillSet.remove(i);
                            break;
                        }
                    }

                    count3++;
                }
                break;

            case R.id.charity:
                if(count4%2==0) {
                    charityCheck.setVisibility(View.VISIBLE);
                    skillSet.add("charity");
                    count4++;
                }
                else if (count4%2!=0) {
                    charityCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("charity")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count4++;
                }
                break;

            case R.id.sports:
                if(count5%2==0) {
                    sportsCheck.setVisibility(View.VISIBLE);
                    skillSet.add("sports");
                    count5++;
                }
                else if (count5%2!=0) {
                    sportsCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("sports")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count5++;
                }
                break;

            case R.id.culinary:
                if(count6%2==0) {
                    culinaryCheck.setVisibility(View.VISIBLE);
                    skillSet.add("culinary");
                    count6++;
                }
                else if (count6%2!=0) {
                    culinaryCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("culinary")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count6++;
                }
                break;

            case R.id.medicine:
                if(count7%2==0) {
                    medicineCheck.setVisibility(View.VISIBLE);
                    skillSet.add("medical");
                    count7++;
                }
                else if (count7%2!=0) {
                    medicineCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("medical")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count7++;
                }
                Log.e("shet", "" + skillSet + skillSet.size());
                break;

            case R.id.arts:
                if(count8%2==0) {
                    artsCheck.setVisibility(View.VISIBLE);
                    skillSet.add("arts");
                    count8++;
                }
                else if (count8%2!=0) {
                    artsCheck.setVisibility(View.GONE);
                    for(int i = 0; i < skillSet.size(); i++){
                        if(skillSet.get(i).equals("arts")) {
                            skillSet.remove(i);
                            break;
                        }
                    }
                    count8++;
                }
                break;

            case R.id.doneButton:
                progbar.setVisibility(View.VISIBLE);
                final Set<String> skillset = new HashSet<String>();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("Success")){
                                    Intent intent = new Intent(SkillsActivity.this,HomeActivity.class);
                                    intent.putExtra("volunteer_id",volunteer_id);
                                    intent.putExtra("api_token",api_token);
                                    intent.putExtra("profileId", profileId);
                                    intent.putExtra("image_url", profilePicture);
                                    intent.putExtra("fbProfilePicture", fbProfilePicture);
                                    intent.putExtra("newSignUpUsername",name);
                                    intent.putExtra("fbProfileName", fbProfileName);
                                    Log.e("SKILLSACTI", "a: " + name + profilePicture + profileId);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                           // Log.e("kobe",error.toString());
                            }
                        }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("volunteer_id",volunteer_id);
                        params.put("api_token",api_token);
                        //JSONObject jsonObject=new JSONObject();
                        for(int i =0; i<skillSet.size(); i++) {
                            params.put("params" + i, skillSet.get(i));
                            Log.e("sudsarequestparaparams",skillSet.get(i));
                            skillset.add(skillSet.get(i));
                        }
                        params.put("count",String.valueOf(skillSet.size()));


                        SharedPreferences pref = getApplicationContext().getSharedPreferences("SKILLS_PREF", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putStringSet("skills", skillset);
                        editor.commit();
//                        sessionManager = new SessionManager(getApplicationContext());
//                        sessionManager.createSkillPref(skillset);

                        Log.e("sudsarequest",volunteer_id + api_token + String.valueOf(skillSet.size())+ "SKILLSETSUD" + skillset.toString()) ;
                        return params;
                    }
                };

                RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                request.add(stringRequest);
                break;
        }
    }
}
