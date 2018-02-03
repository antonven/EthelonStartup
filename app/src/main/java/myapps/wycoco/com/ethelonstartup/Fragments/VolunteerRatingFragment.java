package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import myapps.wycoco.com.ethelonstartup.Adapters.EvaluateGroupAdapter;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerRatingFragment extends Fragment implements RateVolunteerDialogFragment.OnCompleteListener{


    LinearLayoutManager linearLayoutManager;
    ArrayList<RateVolunteer> volunteers;
    EvaluateGroupAdapter evaluateGroupAdapter;
    RecyclerView volrec;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "groupmatestorate";

    public VolunteerRatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_rate, container, false);

        volrec = (RecyclerView)view.findViewById(R.id.volRec);
        volunteers  = new ArrayList<>();
        final String activity_id = getArguments().getString("activity_id");
        final String api_token = getArguments().getString("api_token");
        final String volunteer_id = getArguments().getString("volunteer_id");

        Map<String, String> params = new HashMap<String, String>();
        params.put("activity_id", activity_id);
        params.put("volunteer_id", volunteer_id);
        params.put("api_token", api_token);
        Log.e("Wycoco", "EVALUATEVOLUNTEERSFRAG " + api_token + activity_id + volunteers.size());

        RateVolunteerDialogFragment dialogFragmentAttendanceSuccess = new RateVolunteerDialogFragment();
        dialogFragmentAttendanceSuccess.setTargetFragment(this, 0);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    Log.e("GOINGVFRAGMENT", "RESPONSE" + response.toString());
                                    JSONObject usersObject = response.getJSONObject(i);
                                    String volunteer_name = usersObject.getString("name");
                                    String volunteer_image = usersObject.getString("image_url");
                                    String volunteers_id = usersObject.getString("volunteer_id");
                                    String volunteer_group_id = usersObject.getString("activity_group_id");
                                    int number_of_volunteers = usersObject.getInt("num_of_vol");

                                    Log.e("GOINGVOLUNTEERS", "PICTURES" + volunteer_image);
                                    RateVolunteer volunteer = new RateVolunteer(volunteers_id, volunteer_name, volunteer_image, volunteer_group_id
                                            , number_of_volunteers, "wala pa");

                                    volunteers.add(volunteer);
//                                    fetchCriteria();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            evaluateGroupAdapter = new EvaluateGroupAdapter(getContext(), volunteers, activity_id, api_token, volunteer_id, VolunteerRatingFragment.this);

                            linearLayoutManager = new LinearLayoutManager(getContext());
                            volrec.setLayoutManager(linearLayoutManager);
                            evaluateGroupAdapter.notifyDataSetChanged();
                            volrec.setItemAnimator(new DefaultItemAnimator());
                            volrec.setAdapter(evaluateGroupAdapter);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

        return view;
    }

    @Override
    public void onComplete(int index, int ratings) {

        Log.e("VolunteerRatingFragment","126 on complete pila iya rating"+ratings);
        volunteers.get(index).setStatus("Mana");
        volunteers.get(index).setRating(ratings);
        evaluateGroupAdapter.notifyDataSetChanged();
    }
}
