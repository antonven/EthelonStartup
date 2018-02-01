package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Activities.LoginAsFoundationActivity;
import myapps.wycoco.com.ethelonstartup.Models.BadgeModel;
import myapps.wycoco.com.ethelonstartup.Models.Badge_Level_Model;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.Models.SkillBadgesModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 1/18/2018.
 */

public class BadgeCollectionAdapter extends RecyclerView.Adapter<BadgeCollectionAdapter.ViewHolder>{

    Context mContext;
    private ArrayList<SkillBadgesModel> skillBadgesModels = new ArrayList<>();
    private ArrayList<String> starBadges, badgeState;
    private ArrayList<Badge_Level_Model> badge_levels = new ArrayList<>();
    private static final String URL = "http://" + new Localhost().getLocalhost() + "volunteerprofile";
    private String volunteer_id, api_token;
    GridLayoutManager linearLayoutManager;

    public BadgeCollectionAdapter() {
    }

    public BadgeCollectionAdapter(Context mContext, ArrayList<SkillBadgesModel> skillBadgesModels, ArrayList<Badge_Level_Model> badge_levels, String volunteer_id, String api_token) {
        this.mContext = mContext;
        this.skillBadgesModels = skillBadgesModels;
        this.volunteer_id = volunteer_id;
        this.api_token = api_token;
        this.badge_levels = badge_levels;
    }

    @Override
    public BadgeCollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.badge_item_list, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(BadgeCollectionAdapter.ViewHolder holder, int position) {
        holder.badgeName.setText(skillBadgesModels.get(position).getBadgeName());
//        holder.badgePercentage.setText(String.valueOf(skillBadgesModels.get(position).getBadgePercentage()));
        Glide.with(mContext).load(skillBadgesModels.get(position).getBadgeImage())
                .fitCenter().crossFade().into(holder.badgeImage);
        String status;
        starBadges = new ArrayList<>();
        badgeState = new ArrayList<>();
        Log.e("PISTING YAWA",badge_levels.size()+"");

        for(int i = 0; i < badge_levels.size(); i++){

            if(badge_levels.get(i).getBadge_skill().equals("Arts") && skillBadgesModels.get(position).getSkill().equals("Arts")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Arts")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.arts_1);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.arts_2);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.arts_3);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.arts_4);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Education") && skillBadgesModels.get(position).getSkill().equals("Education")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Education")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.education_newbie);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Education")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.education_explorer);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert")&& badge_levels.get(i).getBadge_skill().equals("Education")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.education_expert);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend")&& badge_levels.get(i).getBadge_skill().equals("Education")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.education_legend);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Environment") && skillBadgesModels.get(position).getSkill().equals("Environment")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Environment")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.environment_newbie);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Environment")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.environment_archon);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert") && badge_levels.get(i).getBadge_skill().equals("Environment")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.environment_expert);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend") && badge_levels.get(i).getBadge_skill().equals("Environment")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.environment_legend);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Sports") && skillBadgesModels.get(position).getSkill().equals("Sports")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Sports")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.sports_newbie);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Sports")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.sports_archon);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert") && badge_levels.get(i).getBadge_skill().equals("Sports")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.sports_expert);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend") && badge_levels.get(i).getBadge_skill().equals("Sports")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.sports_legend);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Livelihood") && skillBadgesModels.get(position).getSkill().equals("Livelihood")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Livelihood")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.livelihood_newbie);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Livelihood")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.livelihood_archon);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert") && badge_levels.get(i).getBadge_skill().equals("Livelihood")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.livelihood_expert);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend") && badge_levels.get(i).getBadge_skill().equals("Livelihood")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.livelihood_legend);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Medical") && skillBadgesModels.get(position).getSkill().equals("Medical")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Medical")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.medicine_newbie);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Medical")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.medicine_archon);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert") && badge_levels.get(i).getBadge_skill().equals("Medical")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.medicine_expert);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend") && badge_levels.get(i).getBadge_skill().equals("Medical")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.medicine_legend);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Culinary") && skillBadgesModels.get(position).getSkill().equals("Culinary")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Culinary")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.arts_1);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Culinary")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.arts_2);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert") && badge_levels.get(i).getBadge_skill().equals("Culinary")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.arts_3);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend") && badge_levels.get(i).getBadge_skill().equals("Culinary")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.arts_4);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }

            if(badge_levels.get(i).getBadge_skill().equals("Charity") && skillBadgesModels.get(position).getSkill().equals("Charity")){
                Log.e("PISTING YAWA","NISUD SA ARTS");


                if(badge_levels.get(i).getBadge_level_name().equals("Newbie") && badge_levels.get(i).getBadge_skill().equals("Charity")){
                    Log.e("PISTING YAWA","NEWBIE");
                    holder.first.setImageResource(R.drawable.charit_newbie);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.first.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }

                }else  if(badge_levels.get(i).getBadge_level_name().equals("Explorer") && badge_levels.get(i).getBadge_skill().equals("Charity")){
                    Log.e("PISTING YAWA","EXPLORER");
                    holder.second.setImageResource(R.drawable.charity_explorer);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.second.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Expert") && badge_levels.get(i).getBadge_skill().equals("Charity")){
                    Log.e("PISTING YAWA","EXPERT");
                    holder.third.setImageResource(R.drawable.charity_expert);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.third.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
                else  if(badge_levels.get(i).getBadge_level_name().equals("Legend") && badge_levels.get(i).getBadge_skill().equals("Charity")){
                    Log.e("PISTING YAWA","LEGEND");
                    holder.fourth.setImageResource(R.drawable.charity_legend);

                    if(skillBadgesModels.get(position).getStatus() < getPriority(badge_levels.get(i).getBadge_level_name())){
                        holder.fourth.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }
        }

        Log.i("BADGE_PICTURES", badge_levels.size() + " ");
       /* for(int o = 0; o < badge_levels.size(); o++){
            if(badge_levels.get(o).getBadge_skill().equals("Arts")){

            }
        }

        if(skillBadgesModels.get(position).getBadgeName().equals("Artist Badge")) {

            for (int i = 0; i < badge_levels.size(); i++) {
                if (badge_levels.get(i).getBadge_skill().equals("Arts")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);

                }

            }

            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));
        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Helper Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Charity")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);
                }



            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Master Chef Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Culinary")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);

                }



            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Surgeon Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Medical")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);
                }



            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));


        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Environmentalist Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Environment")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);

                }


            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Master Carpenter Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Livelihood")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);
                }



            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Professor Badge")){
            for(int i = 0; i<badge_levels.size(); i++){

                if(badge_levels.get(i).getBadge_skill().equals("Education")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status + badgeState.size() );
                }

            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));
         }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Olympian Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Sports")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                    String badge_status = badge_levels.get(i).getBadge_status();
                    badgeState.add(badge_status);
                    Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image() + " " + badge_status);

                }


            }
            linearLayoutManager = new GridLayoutManager(mContext, 4);
            holder.gridView.setLayoutManager(linearLayoutManager);
            holder.gridView.setAdapter(new BadgeRankAdapter(mContext, badgeState, starBadges));
        }
*/
    }
    public int getPriority(String badge){
        int badge_status = 0;

        switch (badge){
            case "Newbie":
                badge_status = 1;
                break;
            case "Explorer":
                badge_status = 2;
                break;
            case "Expert":
                badge_status = 3;
                break;
            case "Legend":
                badge_status = 4;
                break;
        }
        return badge_status;

    }

    @Override
    public int getItemCount() {
        return skillBadgesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView badgeImage, first, second, third, fourth;
        TextView badgeName, badgePercentage;
        RecyclerView gridView;

        public ViewHolder(View itemView) {
            super(itemView);

            starBadges = new ArrayList<>();

            badgeImage = (ImageView)itemView.findViewById(R.id.badgeImage);
            badgeName = (TextView)itemView.findViewById(R.id.badgeName);
            badgePercentage = (TextView)itemView.findViewById(R.id.badgePercentage);
            //gridView = (RecyclerView)itemView.findViewById(R.id.gridView);
            first = (ImageView)itemView.findViewById(R.id.first);
            second = (ImageView)itemView.findViewById(R.id.second);
            third = (ImageView)itemView.findViewById(R.id.third);
            fourth = (ImageView)itemView.findViewById(R.id.fourth);



        }
    }
}
