package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.SharedPreferences;
import android.os.Build;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

import myapps.wycoco.com.ethelonstartup.R;

public class SkillPreferenceActivity extends AppCompatActivity {

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
        SharedPreferences shared = getSharedPreferences("SKILLS_PREF", MODE_PRIVATE);
        Set<String> skill_set = shared.getStringSet("skills", null);

        if(skill_set!=null) {
            for (String skill : skill_set) {
                skillz.add(skill);
            }
            Log.e("skill_SetPREF", skillz.toString());

            if(skill_set.contains("environment")){
                environmentCheck.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("medicine")){
                medicineCheck.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("arts")){
                artsCheck.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("sports")){
                sportsCheck.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("livelihood")){
                livelihood.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("culinary")){
                culinaryCheck.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("charity")){
                charityCheck.setVisibility(View.VISIBLE);
            }
            if(skill_set.contains("education")){
                educationalCheck.setVisibility(View.VISIBLE);
            }

        }


    }
}
