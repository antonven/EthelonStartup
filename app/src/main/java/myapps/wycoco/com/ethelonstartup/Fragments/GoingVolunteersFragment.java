package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.android.volley.toolbox.JsonObjectRequest;
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
public class GoingVolunteersFragment extends Fragment {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitygetvolunteersbefore";
    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    ArrayList<UserModel> users = new ArrayList<>();
    GoingVolunteersAdapter goingVolunteersAdapter;


    public GoingVolunteersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_going_volunteers, container, false);

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("api_token", api_token);
        Log.e("Wycoco", "" + api_token + activity_id);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject usersObject = response.getJSONObject(i);
                                String user_image = usersObject.getString("image_url");
                                String user_name = usersObject.getString("name");

                                UserModel user = new UserModel();
                                user.setUserFirstName(user_name);
                                user.setUserImage(user_image);
                                users.add(user);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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



        goingVolunteersAdapter = new GoingVolunteersAdapter(getContext(), users);
        recyclerView = (RecyclerView)view.findViewById(R.id.recViewVolunteers);
        linearLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        goingVolunteersAdapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(goingVolunteersAdapter);



        return view;
    }

}
