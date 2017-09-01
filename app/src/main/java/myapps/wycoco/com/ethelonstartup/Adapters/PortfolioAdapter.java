package myapps.wycoco.com.ethelonstartup.Adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Activities.AttendanceScanner;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/22/2017.
 */

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder>{
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    Context mContext;
    ArrayList<PortfolioModel> activities = new ArrayList<>();
    int count = 0;
    String api_token, activity_id, volunteer_id;
    private static final String URL = "http://" + new Localhost().getLocalhost() + "activitygetvolunteersbefore";


    public PortfolioAdapter() {
    }

    public PortfolioAdapter(Context mContext, ArrayList<PortfolioModel> activities, String api_token, String volunteer_id) {
        this.mContext = mContext;
        this.activities = activities;
        this.api_token = api_token;
        this.volunteer_id = volunteer_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_layout_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(PortfolioAdapter.ViewHolder holder, int position) {
        holder.clickedEventName.setText(activities.get(position).getActivityName());
        holder.clickedEventHost.setText(activities.get(position).getFoundationName());
        holder.clickedEventDescription.setText(activities.get(position).getActivityDes());
//        holder.clickedEventTimeStart.setText(activities.get(position).getActivityStart());
        holder.clickedEventVolunteers.setText(activities.get(position).getActivityGroup());
//        holder.clickedEventLocation.setText(activities.get(position).getActivityLocation());
        holder.clickedPoints.setText(activities.get(position).getActivityPoints());
        holder.contactPerson.setText(activities.get(position).getContactPerson());
        holder.activityContact.setText(activities.get(position).getActivityContact());
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventAddress.setText(activities.get(position).getActivityLocation());
        holder.eventHost.setText(activities.get(position).getFoundationName());
        holder.eventDate.setText(activities.get(position).getActivityDate());
        holder.eventTimeStart.setText(activities.get(position).getActivityStart());
        holder.eventVolunteers.setText(activities.get(position).getActivityGroup());
        holder.eventPoints.setText(activities.get(position).getActivityPoints());
        Glide.with(mContext).load(activities.get(position).getActivityImage())
                .centerCrop().crossFade().into(holder.clickedActivityImage);

        Log.e("Paran", "" + activities.get(position).getActivityImage());

        if(activities.get(position).getVolunteerStatus().equals("1")){
            holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ribbon_done2));
        }else
            holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ribbon2));


    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventDate, eventTimeStart, eventName, eventAddress, eventHost, eventVolunteers, eventPoints, contentRequestBtn,
        clickedEventHost, clickedEventDescription, clickedEventTimeStart, clickedEventVolunteers, clickedEventLocation, clickedPoints,
        contactPerson, activityContact, clickedEventName;
        ImageView status, clickedActivityImage;
        Button attendBtn;
        FoldingCell fc;
        RecyclerView recyclerView;
        ActivityListGoingVolunteersAdapter adapter;
        LinearLayoutManager linearLayoutManager;
        ArrayList<UserModel> users;

        public ViewHolder(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView)itemView.findViewById(R.id.goingRecycler);
            attendBtn = (Button)itemView.findViewById(R.id.attendBtn);
            fc = (FoldingCell)itemView.findViewById(R.id.foldingCell);
            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventHost = (TextView)itemView.findViewById(R.id.eventHost);
            eventAddress = (TextView)itemView.findViewById(R.id.eventAddress);
            eventDate = (TextView)itemView.findViewById(R.id.eventDate);
            eventTimeStart = (TextView)itemView.findViewById(R.id.eventTimeStart);
            eventVolunteers = (TextView)itemView.findViewById(R.id.title_volunteers_count);
            eventPoints = (TextView)itemView.findViewById(R.id.eventPoints);
            contentRequestBtn = (TextView)itemView.findViewById(R.id.viewActivityDetailsBtn);
            status = (ImageView) itemView.findViewById(R.id.status);
            clickedEventName= (TextView)itemView.findViewById(R.id.clickedEventName);
            clickedEventHost = (TextView)itemView.findViewById(R.id.clickedEventHost);
//            clickedEventTimeStart = (TextView)itemView.findViewById(R.id.clickedEventTimeStart);
            clickedEventDescription = (TextView)itemView.findViewById(R.id.clickedEventDescription);
            clickedEventVolunteers = (TextView)itemView.findViewById(R.id.clickedEventVolunteerCount);
//            clickedEventLocation = (TextView)itemView.findViewById(R.id.clickedEventLocation);
            clickedPoints = (TextView)itemView.findViewById(R.id.clickedEventPoints);
            contactPerson = (TextView)itemView.findViewById(R.id.contactPerson);
            activityContact = (TextView)itemView.findViewById(R.id.activityContact);
            clickedActivityImage = (ImageView)itemView.findViewById(R.id.head_image);

            fetchGoingVolunteers();

            attendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String activity_id = activities.get(getAdapterPosition()).getActivityId();
                    Log.i("Volunteer_id", "PortAdapter" + volunteer_id + activity_id);
                    Intent n = new Intent(mContext, AttendanceScanner.class);
                    n.putExtra("activity_id", activity_id);
                    n.putExtra("api_token", api_token);
                    n.putExtra("volunteer_id", volunteer_id);
                    n.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(n);
                }
            });


            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fc.toggle(false);
                }
            });

        }

        public void fetchGoingVolunteers(){


            users  = new ArrayList<>();
//            String activity_id = getArguments().getString("activity_id");
//            String api_token = getArguments().getString("api_token");

            Map<String, String> params = new HashMap<String, String>();
            params.put("activity_id", activity_id);
            params.put("api_token", api_token);
            Log.e("Wycoco", "GOINGVOLUNTEERSFRAGMENT " + api_token + activity_id + users.size());


            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, new JSONObject(params),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if (response.length() > 0) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        Log.e("GOINGVFRAGMENT", "RESPONSE" + response.toString());
                                        JSONObject usersObject = response.getJSONObject(i);
                                        String user_image = usersObject.getString("image_url");
                                        String user_name = usersObject.getString("name");
//                                        String profile_id = getArguments().getString("profileId");

                                        Log.e("GOINGVOLUNTEERS", "PICTURES" + user_image);
                                        UserModel user = new UserModel();
//                                        user.setUser_id(profile_id);
                                        user.setUserFirstName(user_name);
                                        user.setUserImage(user_image);
                                        users.add(user);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                adapter = new ActivityListGoingVolunteersAdapter(mContext, users);

                                linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                adapter.notifyDataSetChanged();
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(adapter);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(jsonArrayRequest);
        }
    }


}
