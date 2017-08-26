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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import org.w3c.dom.Text;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.EventDetailsActivity;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/27/2017.
 */

public class HomeActivitiesListAdapter extends RecyclerView.Adapter<HomeActivitiesListAdapter.ViewHolder>{

    Context mContext;
    ArrayList<ActivityModel> activities = new ArrayList<>();
    String id, api_token;

    public HomeActivitiesListAdapter(Context mContext, ArrayList<ActivityModel> activities, String id, String api_token) {
        this.mContext = mContext;
        this.activities = activities;
        this.id = id;
        this.api_token = api_token;
//        this.activity_id = activity_id;
        Log.e("HOME ADAPTER", "act_id" + id + activities.size());


    }

    @Override
    public HomeActivitiesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_activities_list_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(HomeActivitiesListAdapter.ViewHolder holder, int position) {
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventAddress.setText(activities.get(position).getActivityLocation());
        holder.eventHost.setText(activities.get(position).getFoundationName());
        holder.eventDate.setText(activities.get(position).getActivityDate());
        holder.eventTimeStart.setText(activities.get(position).getActivityStart());
        holder.eventVolunteers.setText(activities.get(position).getActivityGroup());
        holder.eventPoints.setText(activities.get(position).getActivityPoints());
        holder.clickedEventName.setText(activities.get(position).getActivityName());
        holder.clickedEventHost.setText(activities.get(position).getFoundationName());
        holder.clickedEventDate.setText(activities.get(position).getActivityDate());
        holder.clickedEventTimeStart.setText(activities.get(position).getActivityStart());
        holder.clickedEventVolunteers.setText(activities.get(position).getActivityGroup());
        holder.clickedEventLocation.setText(activities.get(position).getActivityLocation());
        holder.clickedPoints.setText(activities.get(position).getActivityPoints());
        holder.contactPerson.setText(activities.get(position).getContactPerson());
        holder.activityContact.setText(activities.get(position).getActivityContact());
        Glide.with(mContext).load(activities.get(position).getActivityImage())
                .centerCrop().crossFade().into(holder.clickedActivityImage);

        Log.e("kobe","fuck"+ activities.get(position).getActivityGroup());
//        if(activities.get(position).getActivityStatus().equals("Done")){
//            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
//        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FoldingCell fc;
        TextView eventName, eventHost, eventAddress, eventDate, eventTimeStart, eventVolunteers, eventPoints;
        TextView clickedEventName, clickedEventHost, clickedEventLocation, clickedEventDate, clickedEventTimeStart,
                clickedEventVolunteers, clickedPoints, viewActivity, contactPerson, activityContact;
        RelativeLayout relativeLayout;
        ImageView clickedActivityImage;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.indicatorLine);
            fc = (FoldingCell)itemView.findViewById(R.id.foldingCell);
            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventHost = (TextView)itemView.findViewById(R.id.eventHost);
            eventAddress = (TextView)itemView.findViewById(R.id.eventAddress);
            eventDate = (TextView)itemView.findViewById(R.id.eventDate);
            eventTimeStart = (TextView)itemView.findViewById(R.id.eventTimeStart);
            eventVolunteers = (TextView)itemView.findViewById(R.id.title_volunteers_count);
            eventPoints = (TextView)itemView.findViewById(R.id.eventPoints);
            viewActivity = (TextView)itemView.findViewById(R.id.viewActivityDetailsBtn);
            clickedEventName = (TextView)itemView.findViewById(R.id.clickedEventName);
            clickedEventHost = (TextView)itemView.findViewById(R.id.clickedEventHost);
            clickedEventLocation = (TextView)itemView.findViewById(R.id.clickedEventLocation);
            clickedEventDate = (TextView)itemView.findViewById(R.id.clickedEventDate);
            clickedEventTimeStart = (TextView)itemView.findViewById(R.id.clickedEventTimeStart);
            clickedEventVolunteers = (TextView)itemView.findViewById(R.id.clickedEventVolunteerCount);
            clickedPoints = (TextView)itemView.findViewById(R.id.clickedEventPoints);
            clickedActivityImage = (ImageView)itemView.findViewById(R.id.head_image);
            contactPerson = (TextView)itemView.findViewById(R.id.contactPerson);
            activityContact = (TextView)itemView.findViewById(R.id.activityContact);

            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fc.toggle(false);
                }
            });

            viewActivity.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String eventName = activities.get(getAdapterPosition()).getActivityName();
            String eventHost = activities.get(getAdapterPosition()).getFoundationName();
            String eventDate = activities.get(getAdapterPosition()).getActivityDate();
            String eventTimeStart = activities.get(getAdapterPosition()).getActivityStart();
            String eventLocation = activities.get(getAdapterPosition()).getActivityLocation();
            String activity_id = activities.get(getAdapterPosition()).getActivityId();
            String eventImage = activities.get(getAdapterPosition()).getActivityImage();

            Intent in = new Intent(mContext, EventDetailsActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.putExtra("eventImage", eventImage);
            in.putExtra("eventName", eventName);
            in.putExtra("eventHost", eventHost);
            in.putExtra("eventDate", eventDate);
            in.putExtra("eventTimeStart", eventTimeStart);
            in.putExtra("eventLocation", eventLocation);
            in.putExtra("id", id);
            in.putExtra("activity_id", activity_id);
            in.putExtra("api_token", api_token);
            Log.e("Anton Gwapo", "" + api_token + activity_id);
            mContext.startActivity(in);
        }
    }
}
