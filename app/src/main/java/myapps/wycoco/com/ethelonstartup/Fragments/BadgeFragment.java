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
                                    JSONObject info = badge.getJSONObject("info");
                                    String badgeName = info.getString("badge_name");
                                    String badgeImage = info.getString("url");
                                    int gaugeExp = info.getInt("gaugeExp");
                                    int star = info.getInt("star");

                                    JSONArray badges = badge.getJSONArray("badges");
                                    for(int p = 0; p < badges.length(); p++) {
                                        JSONObject bad = badges.getJSONObject(p);
                                        String badge_level = bad.getString("url");
                                        String badge_level_name = bad.getString("badge");
                                        String badge_skill = bad.getString("skill");
                                        Badge_Level_Model badge_level_model = new Badge_Level_Model(badge_level_name, badge_level, badge_skill);
                                        badge_levels.add(badge_level_model);
                                        Log.i("BADGE_LEVELS_IMAGE", badge_level+ badge_level_name+ badge_skill);
                                    }

                                    Log.i("DETAILS_BADGE", badgeName + badgeImage + gaugeExp + star);
                                    SkillBadgesModel model = new SkillBadgesModel(badgeName, badgeImage, gaugeExp, star);

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

}
