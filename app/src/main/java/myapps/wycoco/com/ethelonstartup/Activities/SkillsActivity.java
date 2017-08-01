package myapps.wycoco.com.ethelonstartup.Activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.R;

public class SkillsActivity extends AppCompatActivity implements View.OnClickListener {

    Button backStep, nextStep;
    ImageView environmentCheck, livelihoodCheck, culinaryCheck, charityCheck, sportsCheck, educationalCheck;
    GridView gridView;
    ImageView environmental, livelihood, educational, culinary, charity, sports, medical;
    String []interests = {};
    ArrayList<String> skillSet = new ArrayList<>();
    int count1=0, count2=0, count3=0, count4=0, count5=0, count6=0;
    private String URL = "http://192.168.1.5/EthelonStartupWeb/public/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skills_interests_choice);

        environmentCheck = (ImageView)findViewById(R.id.environmentCheck);
        livelihoodCheck = (ImageView)findViewById(R.id.livelihoodCheck);
        culinaryCheck = (ImageView)findViewById(R.id.culinaryCheck);
        charityCheck = (ImageView)findViewById(R.id.charityCheck);
        sportsCheck = (ImageView)findViewById(R.id.sportsCheck);
        educationalCheck = (ImageView)findViewById(R.id.educationalCheck);

        gridView = (GridView)findViewById(R.id.gridView);

        environmental = (ImageView)findViewById(R.id.environmental);
        livelihood = (ImageView)findViewById(R.id.livelihood);
        educational = (ImageView)findViewById(R.id.educational);
        culinary = (ImageView)findViewById(R.id.culinary);
        charity = (ImageView)findViewById(R.id.charity);
        sports = (ImageView)findViewById(R.id.sports);

        environmental.setOnClickListener(this);
        livelihood.setOnClickListener(this);
        culinary.setOnClickListener(this);
        educational.setOnClickListener(this);
        charity.setOnClickListener(this);
        sports.setOnClickListener(this);



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
        }
    }


}
