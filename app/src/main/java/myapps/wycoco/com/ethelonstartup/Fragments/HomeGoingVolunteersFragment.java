package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.zip.Inflater;

import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.HomeAdapterVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeGoingVolunteersFragment extends Fragment {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitygetvolunteersbefore";
    RecyclerView recView;
    ArrayList<UserModel> users;
    HomeAdapterVolunteersAdapter homeAdapterVolunteersAdapter;
    GridLayoutManager linearLayoutManager;

    public HomeGoingVolunteersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_vol_layout, container, false);

        recView = (RecyclerView)view.findViewById(R.id.recView);

        fetchGoingVolunteers();
        return view;
    }


    public void fetchGoingVolunteers(){

        users  = new ArrayList<>();
        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("api_token", api_token);
        Log.e("ACTIVITY  NA MO ADTO",activity_id + " " +api_token);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("RESPONSE SA MOADTO", response.toString());
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject usersObject = response.getJSONObject(i);
                                    String user_image = usersObject.getString("image_url");

                                    UserModel user = new UserModel();
                                    user.setUserImage(user_image);
                                    users.add(user);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            homeAdapterVolunteersAdapter = new HomeAdapterVolunteersAdapter(getContext(), users);
                            linearLayoutManager = new GridLayoutManager(getContext(), 5);
                            recView.setLayoutManager(linearLayoutManager);
                            homeAdapterVolunteersAdapter.notifyDataSetChanged();
                            recView.setItemAnimator(new DefaultItemAnimator());
                            recView.setAdapter(homeAdapterVolunteersAdapter);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}
