package myapps.wycoco.com.ethelonstartup.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Adapters.LeaderBoardAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.NotificationsAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Config;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.NotificationsModel;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;
import static myapps.wycoco.com.ethelonstartup.R.drawable.e;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String URL = "http://"+new Localhost().getLocalhost()+"notif";

    RecyclerView recyclerView;
    NotificationsAdapter notificationsAdapter;
    ArrayList<NotificationsModel> notifications;

    BroadcastReceiver mRegistrationBroadcastReceiver;
    SwipeRefreshLayout swipeRefreshLayout;
    String api_token, id, profileId;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recView);

        Log.e("fuck", FirebaseInstanceId.getInstance().getToken() + " ");

        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.bgContentTop));
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                fetchNotifications();
            }
        }
        );

        return v;
    }

    @Override
    public void onRefresh() {
        fetchNotifications();
    }

    public void fetchNotifications(){

        swipeRefreshLayout.setRefreshing(true);
        notifications = new ArrayList<>();
        id = getArguments().getString("id");
        api_token = getArguments().getString("api_token");
        profileId = getArguments().getString("profileId");


        Map<String, String> params = new HashMap<String, String>();
        params.put("volunteer_id", id);
        params.put("api_token", api_token);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if(response.length() > 0){

                            for(int i = 0; i < response.length(); i++){

                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Log.e("TANGINA", "Notifications List: " + response.toString() + response.length());

                                    String notification_id = jsonObject.getString("notification_id");
                                    String notification_user_id = jsonObject.getString("notification_user_id");
                                    String body = jsonObject.getString("body");
                                    int isRead = jsonObject.getInt("isRead");
                                    String type = jsonObject.getString("major_type");
                                    String data = jsonObject.getString("data");
                                    String image_url = jsonObject.getString("image_url");
                                    String sender_id = jsonObject.getString("sender_id");
                                    String hours = jsonObject.getString("hours");

                                    NotificationsModel notificationsModel = new NotificationsModel(
                                            notification_id,
                                            notification_user_id,
                                            image_url,
                                            isRead,
                                            body,
                                            type,
                                            data,
                                            sender_id,
                                            hours);

                                    notifications.add(notificationsModel);
                                    Log.e("BOGO", "BOGO" + notificationsModel);


                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            Collections.reverse(notifications);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            NotificationsAdapter notificationsAdapter = new NotificationsAdapter(getApplicationContext(), notifications,api_token,id,profileId);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(notificationsAdapter);
                        }

                        swipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
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


}
