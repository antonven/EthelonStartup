package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import myapps.wycoco.com.ethelonstartup.Adapters.EvaluateGroupAdapter;
import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/15/2017.
 */

public class GroupmatesFragment extends Fragment {

    LinearLayoutManager linearLayoutManager;
    ArrayList<UserModel> volunteers;
    GoingVolunteersAdapter goingVolunteersAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView volrec;
    TextView groupType;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "groupmatestorate";

    public GroupmatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_rate, container, false);

        volrec = (RecyclerView) view.findViewById(R.id.volRec);
        groupType = (TextView)view.findViewById(R.id.groupType);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeLayout);
        volunteers = new ArrayList<>();

        fetchGroup();

        return view;

    }

    public void fetchGroup(){

        final String activity_id = getArguments().getString("activity_id");
        final String api_token = getArguments().getString("api_token");
        final String volunteer_id = getArguments().getString("volunteer_id");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);

        RateVolunteerDialogFragment dialogFragmentAttendanceSuccess = new RateVolunteerDialogFragment();
        dialogFragmentAttendanceSuccess.setTargetFragment(this,0);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String groupTypetxt = null;
                        if (response.length() > 0) {
                            Log.e("NAAY GROUP", "BOGO " + response.toString());


                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    groupTypetxt = jsonObject.getString("type");

                                    JSONObject usersObject = response.getJSONObject(i);
                                    String volunteer_name = usersObject.getString("name");
                                    String volunteer_image = usersObject.getString("image_url");
//                                    String groupTypetxt = usersObject.getString("type");
                                    Log.i("GROUP_TYPE", groupTypetxt);
                                    UserModel volunteer = new UserModel();
                                    volunteer.setUserFirstName(volunteer_name);
                                    volunteer.setUserImage(volunteer_image);

                                    volunteers.add(volunteer);
//                                    fetchCriteria();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "BOGO", Toast.LENGTH_SHORT).show();
                                }
                            }
                            groupType.append(groupTypetxt);

                            goingVolunteersAdapter = new GoingVolunteersAdapter(getContext(), volunteers);

                            linearLayoutManager = new GridLayoutManager(getContext(), 3);
                            volrec.setLayoutManager(linearLayoutManager);
                            goingVolunteersAdapter.notifyDataSetChanged();
                            volrec.setItemAnimator(new DefaultItemAnimator());
                            volrec.setAdapter(goingVolunteersAdapter);
                            swipeRefreshLayout.setRefreshing(false);


                        }
                        else {
                            Toast.makeText(getContext(), "WALAY GROUPMATEs", Toast.LENGTH_SHORT).show();
                        }
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
