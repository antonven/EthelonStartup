package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
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
    private ArrayList<String> starBadges;
    private ArrayList<String> environmentRanks, medicalRanks, educationRanks, sportsRanks, charityRanks, livelihoodRanks, artRanks, culinaryRanks;

    private ArrayList<Badge_Level_Model> badge_levels;

    private static final String URL = "http://" + new Localhost().getLocalhost() + "volunteerprofile";
    private String volunteer_id, api_token;

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

        starBadges = new ArrayList<>();
        Log.i("BADGE_PICTURES", badge_levels.size() + " ");
//        for(int o = 0; o < badge_levels.size(); o++){
//            if(badge_levels.get(o).getBadge_skill().equals("Arts")){
//
//            }
//        }

        if(skillBadgesModels.get(position).getBadgeName().equals("Artist Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Arts")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }else if(skillBadgesModels.get(position).getBadgeName().equals("Helper Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Charity")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Master Chef Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Culinary")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Surgeon Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Medical")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Environmentalist Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Environment")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_ENVIRONMENT", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Master Carpenter Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Livelihood")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Professor Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Education")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }
        else if(skillBadgesModels.get(position).getBadgeName().equals("Olympian Badge")){
            for(int i = 0; i<badge_levels.size(); i++){
                if(badge_levels.get(i).getBadge_skill().equals("Sports")) {
                    starBadges.add(badge_levels.get(i).getBadge_level_image());
                }
                Log.i("BADGE_PICTURESSS", badge_levels.get(i).getBadge_level_image());

            }
            holder.gridView.setAdapter(new BasicGlideAdatper(starBadges, mContext));

        }

    }

    @Override
    public int getItemCount() {
        return skillBadgesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView badgeImage;
        TextView badgeName, badgePercentage;
        GridView gridView;

        public ViewHolder(View itemView) {
            super(itemView);

            starBadges = new ArrayList<>();

            badgeImage = (ImageView)itemView.findViewById(R.id.badgeImage);
            badgeName = (TextView)itemView.findViewById(R.id.badgeName);
            badgePercentage = (TextView)itemView.findViewById(R.id.badgePercentage);
            gridView = (GridView)itemView.findViewById(R.id.gridView);



        }
    }
}
