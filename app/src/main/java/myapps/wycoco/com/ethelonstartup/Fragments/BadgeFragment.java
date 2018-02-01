package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.BadgeCollectionAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
//import myapps.wycoco.com.ethelonstartup.Adapters.ProfileSkillsController;
import myapps.wycoco.com.ethelonstartup.Adapters.ProfileSkillsController;
import myapps.wycoco.com.ethelonstartup.Models.BadgeModel;
import myapps.wycoco.com.ethelonstartup.Models.Badge_Level_Model;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.SkillBadgesModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class BadgeFragment extends Fragment {

    RecyclerView recView;
    ArrayList<SkillBadgesModel> badgesModels;
    ArrayList<Badge_Level_Model> badge_levels;
    ProfileSkillsController controller;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "volunteerprofile";


    public BadgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_badge, container, false);
        recView = (RecyclerView)view.findViewById(R.id.badgeRecView);

        loadSkillBadges();

        return view;

    }

    public void loadSkillBadges(){

        badgesModels = new ArrayList<>();
        badge_levels = new ArrayList<>();

        final String volunteer_id = getArguments().getString("volunteer_id");
        final String api_token = getArguments().getString("api_token");

//        controller = new ProfileSkillsController();

//        badgesModels.addAll(controller.getmSkillBadges());

        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);
        Log.e("GWAPOGWAPO", volunteer_id + api_token);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("ANTON BADGES", response.toString());

                        if(response.length() > 0) {
                            Log.i("NISUD", "ANG BOGO DIRI" + response.length());

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject badge = response.getJSONObject(i);
                                     Log.i("final_BADGES", badge.toString());
                                    JSONObject currentBadge = badge.getJSONObject("info");
                                    String currentBadgeName = currentBadge.getString("badge_name");
                                    String currentBadgeImage = currentBadge.getString("url");
                                    String currentBadgeRank = currentBadge.getString("badge");
                                    int gaugeExp = currentBadge.getInt("gaugeExp");
                                    int star = currentBadge.getInt("star");
                                    String skill = currentBadge.getString("skill");
                                    int statuss = getBadgeStatus(currentBadgeRank);
                                    JSONArray badges = badge.getJSONArray("badges");
                                    for(int p = 0; p < badges.length(); p++) {

                                        JSONObject bad = badges.getJSONObject(p);
                                        String badge_level = bad.getString("url");
                                        String badge_level_name = bad.getString("badge");
                                        String badge_skill = bad.getString("skill");
                                        String badge_status = null;

                                        int result = getBadgeStatus(currentBadgeRank);
                                        Log.i("STATUS_RESULTS", result + " " + badge_level_name);

                                        if(result == 1){
                                            if(badge_level_name.equals("Newbie")){
                                                badge_status = "earned";
                                            }else{
                                                badge_status = "notearned";
                                            }
                                        }
                                        else if(result == 2){
                                            if(badge_level_name.equals("Explorer") || badge_level_name.equals("Newbie")){
                                                badge_status = "earned";
                                            }else{
                                                badge_status = "notearned";
                                            }
                                        }
                                        else if(result == 3){
                                            if(badge_level_name.equals("Expert")||badge_level_name.equals("Explorer") || badge_level_name.equals("Newbie")){
                                                badge_status = "earned";
                                            }else{
                                                badge_status = "notearned";
                                            }
                                        }
                                        else if(result == 4){
                                            if(badge_level_name.equals("Legend") || badge_level_name.equals("Expert")||badge_level_name.equals("Explorer") || badge_level_name.equals("Newbie")){
                                                badge_status = "earned";
                                            }
                                        }

                                        Badge_Level_Model badge_level_model = new Badge_Level_Model(badge_level_name, badge_level, badge_skill, badge_status);
                                        badge_levels.add(badge_level_model);
                                        Log.e("BADGE_LEVELS_IMAGE", "BADGE LEVEL = "+ badge_level+ "BADGE LEVEL NAME "+ badge_level_name+ badge_skill + badge_status);
                                    }

                                    Log.i("DETAILS_BADGE", currentBadgeName + currentBadgeImage + gaugeExp + star);
                                    SkillBadgesModel model = new SkillBadgesModel(currentBadgeName, currentBadgeImage, gaugeExp, star,skill,statuss);

                                    badgesModels.add(model);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recView.setLayoutManager(layoutManager);

                            BadgeCollectionAdapter badgeCollectionAdapter = new BadgeCollectionAdapter(getApplicationContext(), badgesModels, badge_levels, volunteer_id, api_token);
                            recView.setItemAnimator(new DefaultItemAnimator());
                            recView.setAdapter(badgeCollectionAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonObjectRequest);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
    }

    public int getBadgeStatus(String badge_level_name){

        int badge_status = 0;
        switch (badge_level_name){
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

}
