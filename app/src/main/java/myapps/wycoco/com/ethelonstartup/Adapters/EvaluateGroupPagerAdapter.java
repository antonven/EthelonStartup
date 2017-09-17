package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.location.Criteria;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 8/28/2017.
 */

public class EvaluateGroupPagerAdapter extends PagerAdapter {

//    ArrayList<RateVolunteer> volunteers = new ArrayList<>();

    LayoutInflater layoutInflater;
    RecyclerView recyclerCriteria;
    String api_token, volunteer_id, activity_id;
    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    ArrayList<EvaluationCriteria> criteria;
    EvaluationCriteriaAdapter evaluationCriteriaAdapter;

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitycriteria";




    Context mContext;

    public EvaluateGroupPagerAdapter() {
    }

    public EvaluateGroupPagerAdapter(ArrayList<RateVolunteer> volunteers, Context mContext, String activity_id, String api_token, String volunteer_id) {
        this.volunteers = volunteers;
        this.mContext = mContext;
        this.activity_id = activity_id;
        this.api_token = api_token;
        this.volunteer_id = volunteer_id;

    }

    @Override
    public int getCount() {
        return volunteers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.evaluate_group_item, null);
        ImageView volunteerImage = (ImageView)view.findViewById(R.id.volunteerPicture);
        TextView volunteerName = (TextView)view.findViewById(R.id.volunteerFirstName);


        Glide.with(mContext).load(volunteers.get(position).getVolunteer_image())
                .centerCrop().crossFade().into(volunteerImage);
        volunteerName.setText(volunteers.get(position).getVolunteer_name());
        fetchCriteria(view);

        ViewPager vp = (ViewPager) container;
        vp.addView(view ,0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void fetchCriteria(View view){

        recyclerCriteria = (RecyclerView)view.findViewById(R.id.recyclerCriteria);

//        String activity_id = getArguments().getString("activity_id");
//        String api_token = getArguments().getString("api_token");
//        String volunteer_id = getArguments().getString("volunteer_id");

        criteria = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);
        Log.e("Wycoco", "EVALUATEVOLUNTEERSFRAG " + api_token + activity_id + volunteers.size());

     /*   JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    Log.e("GOINGVFRAGMENT", "RESPONSE" + response.toString());
                                    JSONObject usersObject = response.getJSONObject(i);
                                        String criterion = usersObject.getString("criteria");
                                        EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
                                        evaluationCriteria.setCriteriaName(criterion);

                                        criteria.add(evaluationCriteria);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerCriteria.setLayoutManager(layoutManager);
                            evaluationCriteriaAdapter = new EvaluationCriteriaAdapter(mContext, criteria);
                            recyclerCriteria.setItemAnimator(new DefaultItemAnimator());
                            recyclerCriteria.setAdapter(evaluationCriteriaAdapter);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
//                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);*/
    }





}
