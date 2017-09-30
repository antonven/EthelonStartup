package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoingVolunteersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitygetvolunteersbefore";
    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    ArrayList<UserModel> users;
    GoingVolunteersAdapter goingVolunteersAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public GoingVolunteersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_going_volunteers, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutGoingVolunteers);
        recyclerView = (RecyclerView)view.findViewById(R.id.recViewVolunteers);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bgContentTop));

        swipeRefreshLayout.setOnRefreshListener(this);
        fetchGoingVolunteers();

        return view;
    }

    @Override
    public void onRefresh() {
        fetchGoingVolunteers();
    }


    public void fetchGoingVolunteers(){

        swipeRefreshLayout.setRefreshing(true);

        users  = new ArrayList<>();
        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("api_token", api_token);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject usersObject = response.getJSONObject(i);
                                    String user_image = usersObject.getString("image_url");
                                    String user_name = usersObject.getString("name");
                                    String profile_id = getArguments().getString("profileId");

                                    UserModel user = new UserModel();
                                    user.setUser_id(profile_id);
                                    user.setUserFirstName(user_name);
                                    user.setUserImage(user_image);
                                    users.add(user);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            goingVolunteersAdapter = new GoingVolunteersAdapter(getContext(), users);

                            linearLayoutManager = new GridLayoutManager(getContext(), 3);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            goingVolunteersAdapter.notifyDataSetChanged();
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(goingVolunteersAdapter);
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
