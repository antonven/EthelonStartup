package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Activities.PortfolioEventDetailsActivity;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.NotificationsModel;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/6/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<NotificationsModel> notifications =  new ArrayList<>();
    String api_token;
    String volunteer_id;
    String profile_id;

    public NotificationsAdapter(Context mContext, ArrayList<NotificationsModel> notifications, String api_token, String volunteer_id, String profile_id) {
        this.mContext = mContext;
        this.volunteer_id = volunteer_id;
        this.api_token = api_token;
        this.notifications = notifications;
        this.profile_id = profile_id;
        Log.e("Array size", "Shit" + notifications.size());
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(NotificationsAdapter.ViewHolder holder, int position) {

        Glide.with(mContext).load(notifications.get(position).getImage_url())
                .centerCrop().crossFade().into(holder.userImage);
//        holder.userName.setText(notifications.get(position).getSender_id());
        holder.notifContent.setText(notifications.get(position).getBody());
        holder.notifTime.setText(notifications.get(position).getTime());
//        Glide.with(mContext).load(notifs.get(position).get())
//                .centerCrop().crossFade().into(holder.notifTypeLogo);

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        ImageView userImage, notifTypeLogo;
        TextView userName, notifContent, notifTime;

        ViewHolder(View itemView) {
            super(itemView);

            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearNotif);
            userImage = (ImageView)itemView.findViewById(R.id.userImage);
            notifTypeLogo = (ImageView)itemView.findViewById(R.id.notifTypeLogo);
//            userName = (TextView)itemView.findViewById(R.id.userName);
            notifContent = (TextView)itemView.findViewById(R.id.notifContent);
            notifTime = (TextView)itemView.findViewById(R.id.notifTime);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));

                    Log.e("adapter 108","clicked");

                    if(notifications.get(getAdapterPosition()).getType().equals("activity_group")){

                            String notifUrl = "http://"+new Localhost().getLocalhost()+"notificationClicked";



                            Map<String, String> params = new HashMap<String, String>();
                            params.put("volunteer_id",volunteer_id);
                            params.put("api_token",api_token);
                            params.put("notif_type",notifications.get(getAdapterPosition()).getType());
                            params.put("data",notifications.get(getAdapterPosition()).getData());

                            Log.e("kobeeeee","vol id = "+volunteer_id + "     api = " + api_token + "      type = " +notifications.get(getAdapterPosition()).getType() + "      data= "+ notifications.get(getAdapterPosition()).getData());

                            JsonRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, notifUrl, new JSONObject(params), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {

                                        Log.e("PortfolioActivities", response.toString());

                                        String activityName = response.getString("name");
                                        String foundationId = response.getString("foundation_id");
                                        String activity_id = response.getString("activity_id");
                                        String activityImage = response.getString("image_url");
                                        String activityQr = response.getString("imageQr_url");
                                        String activityDes = response.getString("description");
                                        String activityLocation = response.getString("location");
                                        String activityStart = response.getString("start_time");
                                        String activityEnd = response.getString("end_time");
                                        String activityDate = response.getString("startDate");
                                        String activityGroup = response.getString("group");
                                        String activityLong = response.getString("long");
                                        String activityLat = response.getString("lat");
                                        String activityPoints = response.getString("points_equivalent");
                                        String activityStatus = response.getString("status");
                                        String activityCreated = response.getString("created_at");
                                        String activityUpdated = response.getString("updated_at");
                                        String contactPerson = response.getString("contactperson");
                                        String activityContact = response.getString("contact");
                                        String volunteerStatus = response.getString("joined");
                                        String foundationName = response.getString("foundtion_name");
                                        int points = response.getInt("points");
                                        int volunteer_count = response.getInt("volunteer_count");
                                        String foundationImage = response.getString("foundation_img");
                                        JSONArray act_skills = response.getJSONArray("activity_skills");
                                        ArrayList<String> final_skills = new ArrayList<String>();


                                        for(int x = 0; x<act_skills.length(); x++){
                                            JSONObject obj = act_skills.getJSONObject(x);
                                            String skill = obj.getString("name");
                                            final_skills.add(skill);
                                        }
                                        // Log.i("final_skills", final_skills + "" + activityName);

                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = dateFormat.parse(activityDate);
                                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                                        Date time = timeFormat.parse(activityStart);

                                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM dd, EEE");
                                        String finalDate = dateFormat1.format(date);
                                        SimpleDateFormat timeFormat1 = new SimpleDateFormat("hh:mm a");
                                        String finalTime = timeFormat1.format(time);

                                        PortfolioModel portfolioModel = new PortfolioModel(activity_id, foundationId, activityName, activityImage,
                                                activityQr,
                                                activityDes,
                                                activityLocation,
                                                finalTime,
                                                activityEnd,
                                                finalDate,
                                                activityGroup,
                                                activityLong,
                                                activityLat,
                                                activityPoints,
                                                activityStatus,
                                                activityCreated,
                                                activityUpdated,
                                                contactPerson,
                                                activityContact,
                                                volunteerStatus,
                                                foundationName,
                                                points,
                                                volunteer_count,
                                                foundationImage,
                                                final_skills);


                                        Intent in = new Intent(mContext, PortfolioEventDetailsActivity.class);
                                        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        in.putExtra("eventImage", activityImage);
                                        in.putExtra("eventName", activityName);
                                        in.putExtra("eventHost", foundationName);
                                        in.putExtra("eventDate", activityDate);
                                        in.putExtra("eventTimeStart", activityStart);
                                        in.putExtra("eventLocation", activityLocation);
                                        in.putExtra("contactNo", activityContact);
                                        in.putExtra("contactPerson", contactPerson);
                                        in.putExtra("eventPoints", activityPoints);
                                        in.putExtra("id", volunteer_id);
                                        in.putExtra("activity_id", activity_id);
                                        in.putExtra("api_token", api_token);
                                        in.putExtra("profileId", profile_id);
                                        in.putExtra("volunteer_id", volunteer_id);
                                        in.putExtra("points",points);
                                        in.putExtra("from","notification");

                                        mContext.startActivity(in);
                                        Log.e("adapter 218",response.toString());

                                        //Log.e("PortfolioActivities", response.toString());

                                    } catch (JSONException | ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("ZZZZZZZZZZZZZCCROR 229",error.toString());
                                }
                            }
                            );

                            RequestQueue request = Volley.newRequestQueue(mContext);
                            request.add(jsonrequest);

                    }
                }
            });

        }
    }
}
