package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

//import myapps.wycoco.com.ethelonstartup.Adapters.LeaderBoardCustomAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.HomeActivitiesListAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.LeaderBoardAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;
import shivam.developer.featuredrecyclerview.FeatureLinearLayoutManager;
import shivam.developer.featuredrecyclerview.FeaturedRecyclerView;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dell on 7/15/2017.
 *
 *
 */

public class LeaderBoardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String URL = "http://"+new Localhost().getLocalhost()+"leaderboard";
    ArrayList<UserModel> userLeaders ;
    RecyclerView featuredRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    LeaderBoardAdapter leaderBoardAdapter;
    UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);

//        createDummyDataList();

        featuredRecyclerView = (RecyclerView) v.findViewById(R.id.featured_recycler_view);
        FeatureLinearLayoutManager layoutManager = new FeatureLinearLayoutManager(getContext());
        featuredRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bgContentTop));

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                fetchLeaderBoard();
            }
        }
        );

        return v;
    }

    private void fetchLeaderBoard(){

        swipeRefreshLayout.setRefreshing(true);

        userLeaders = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if(response.length() > 0){
                            for(int i = 0; i < response.length(); i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    String leaderboard_name = jsonObject.getString("name");
                                    String leaderboard_image = jsonObject.getString("image_url");
//                                    int leaderboard_points = Integer.parseInt(jsonObject.getString("points"));

                                    userModel = new UserModel();
                                    userModel.setUserFirstName(leaderboard_name);
                                    userModel.setUserImage(leaderboard_image);
//                                    userModel.setUser_points(leaderboard_points);

                                    userLeaders.add(userModel);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                featuredRecyclerView.setLayoutManager(layoutManager);
                                leaderBoardAdapter = new LeaderBoardAdapter(getApplicationContext(), userLeaders);
                                featuredRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                featuredRecyclerView.setAdapter(leaderBoardAdapter);
                            }
                            swipeRefreshLayout.setRefreshing(false);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonArrayRequest);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
    }

    private void createDummyDataList(){
        for(int i = 0; i < 10; i++){
            String leaderImage = "https://graph.facebook.com/1877377522288783/picture?height=500&width=500&migration_overrides=%7Boctober_2012%3Atrue%7D";
            userModel = new UserModel();

            userModel.setUserImage(leaderImage);
            userModel.setUserFirstName("Anton Ven");
            userLeaders.add(userModel);
        }
    }

    @Override
    public void onRefresh() {
        fetchLeaderBoard();
    }
}
