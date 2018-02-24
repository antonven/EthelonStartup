package myapps.wycoco.com.ethelonstartup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.EventDetailsActivity;
import myapps.wycoco.com.ethelonstartup.Activities.FoundationProfileActivity;
import myapps.wycoco.com.ethelonstartup.Fragments.GoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.HomeGoingVolunteersFragment;
import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/27/2017.
 */

public class HomeActivitiesListAdapter extends RecyclerView.Adapter<HomeActivitiesListAdapter.ViewHolder>{

    Context mContext;
    private ArrayList<ActivityModel> activities = new ArrayList<>();
    private String id, api_token, profile_id, activity_id;
    private FragmentManager suppFragment;
    private ArrayList<Integer> images;

    public HomeActivitiesListAdapter(Context mContext, String activity_id,  ArrayList<ActivityModel> activities, String id, String api_token, String profile_id,
                                     FragmentManager suppFragment) {
        this.mContext = mContext;
        this.activities = activities;
        this.id = id;
        this.api_token = api_token;
        this.profile_id = profile_id;
        this.suppFragment = suppFragment;
        this.activity_id = activity_id;
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
//        holder.eventVolunteers.setText(String.valueOf(activities.get(position).getVolunteerCount()));
//        holder.eventPoints.setText(activities.get(position).getActivityPoints());
        holder.clickedEventName.setText(activities.get(position).getActivityName());
        holder.clickedEventHost.setText(activities.get(position).getFoundationName());
        holder.clickedEventDescription.setText(activities.get(position).getActivityDes());
        holder.clickedEventVolunteers.setText(String.valueOf(activities.get(position).getVolunteerCount()));
        holder.clickedPoints.setText(activities.get(position).getActivityPoints());
        holder.contactPerson.setText(activities.get(position).getContactPerson());
        holder.activityContact.setText(activities.get(position).getActivityContact());
        Glide.with(mContext).load(activities.get(position).getActivityImage())
                .centerCrop().crossFade().into(holder.clickedActivityImage);
        Glide.with(mContext).load(activities.get(position).getFoundationImage())
                .centerCrop().crossFade().into(holder.clickedFoundationImage);

        images = new ArrayList<>();
        ArrayList<String> skills = activities.get(position).getAct_skills();
        Log.e("HOMEADAPTER skills", skills.toString());
        for(int i = 0; i<skills.size(); i++){
            String skill = skills.get(i);
            if(skill.equals("Environment"))
                images.add(R.drawable.environment_volunteer);
            else if(skill.equals("Medical"))
                images.add(R.drawable.medical_volunteer);
            else if(skill.equals("Livelihood"))
                images.add(R.drawable.livelihood_volunteer);
            else if(skill.equals("Sports"))
                images.add(R.drawable.sports_volunteer);
            else if(skill.equals("Culinary"))
                images.add(R.drawable.culinary_volunteer);
            else if(skill.equals("Charity"))
                images.add(R.drawable.charity_volunteer);
            else if(skill.equals("Arts"))
                images.add(R.drawable.arts_volunteer);
            else if(skill.equals("Education"))
                images.add(R.drawable.education_volunteer);
        }
        Log.e("Home item skills", images.toString());

        holder.gridView.setAdapter(new BasicAdapter(images, mContext));

//H
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FoldingCell fc;
        TextView eventName, eventHost, eventAddress, eventDate, eventTimeStart, eventVolunteers, eventPoints;
        TextView clickedEventName, clickedEventHost, clickedEventDescription,
                clickedEventVolunteers, clickedPoints, viewActivity, contactPerson, activityContact;
        RelativeLayout relativeLayout;
        GridView gridView;
        ImageView clickedActivityImage, clickedFoundationImage;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.indicatorLine);
            fc = (FoldingCell)itemView.findViewById(R.id.foldingCell);
            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventHost = (TextView)itemView.findViewById(R.id.eventHost);
            eventAddress = (TextView)itemView.findViewById(R.id.eventAddress);
            eventDate = (TextView)itemView.findViewById(R.id.eventDate);
            eventTimeStart = (TextView)itemView.findViewById(R.id.eventTimeStart);
//            eventVolunteers = (TextView)itemView.findViewById(R.id.title_volunteers_count);
//            eventPoints = (TextView)itemView.findViewById(R.id.eventPoints);
            viewActivity = (TextView)itemView.findViewById(R.id.viewActivityDetailsBtn);
            clickedEventName = (TextView)itemView.findViewById(R.id.clickedEventName);
            clickedEventHost = (TextView)itemView.findViewById(R.id.clickedEventHost);
            clickedEventDescription = (TextView)itemView.findViewById(R.id.clickedEventDescription);
            clickedEventVolunteers = (TextView)itemView.findViewById(R.id.clickedEventVolunteerCount);
            clickedPoints = (TextView)itemView.findViewById(R.id.clickedEventPoints);
            clickedActivityImage = (ImageView)itemView.findViewById(R.id.head_image);
            contactPerson = (TextView)itemView.findViewById(R.id.contactPerson);
            activityContact = (TextView)itemView.findViewById(R.id.activityContact);
            clickedFoundationImage = (ImageView)itemView.findViewById(R.id.foundation_img);
            gridView = (GridView)itemView.findViewById(R.id.gridView);


            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Black.ttf");
            eventName.setTypeface(typeface);
            eventHost.setTypeface(typeface);
            eventAddress.setTypeface(typeface);
            eventDate.setTypeface(typeface);
            eventTimeStart.setTypeface(typeface);
//            eventVolunteers.setTypeface(typeface);
//            eventPoints.setTypeface(typeface);
            clickedEventName.setTypeface(typeface);
            clickedEventHost.setTypeface(typeface);
            clickedEventDescription.setTypeface(typeface);
            clickedEventVolunteers.setTypeface(typeface);
            clickedPoints.setTypeface(typeface);
            contactPerson.setTypeface(typeface);
            activityContact.setTypeface(typeface);

            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fc.toggle(false);
                }
            });


            viewActivity.setOnClickListener(this);
            eventHost.setOnClickListener(this);
            clickedEventHost.setOnClickListener(this);
            clickedFoundationImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Intent n;
            String eventName = activities.get(getAdapterPosition()).getActivityName();
            String eventHost = activities.get(getAdapterPosition()).getFoundationName();
            String eventDate = activities.get(getAdapterPosition()).getActivityDate();
            String eventTimeStart = activities.get(getAdapterPosition()).getActivityStart();
            String eventLocation = activities.get(getAdapterPosition()).getActivityLocation();
            String activity_id = activities.get(getAdapterPosition()).getActivityId();
            String eventImage = activities.get(getAdapterPosition()).getActivityImage();
            String eventContactNo = activities.get(getAdapterPosition()).getActivityContact();
            String eventContactPerson = activities.get(getAdapterPosition()).getContactPerson();
            String eventSkills = activities.get(getAdapterPosition()).getActivityPoints();
            String volunteerStatus = activities.get(getAdapterPosition()).getVolunteerStatus();
            String foundation_image = activities.get(getAdapterPosition()).getFoundationImage();
            String foundation_id = activities.get(getAdapterPosition()).getFoundationId();

            switch (view.getId()) {
                case R.id.viewActivityDetailsBtn:

                    n = new Intent(mContext, EventDetailsActivity.class);
                    n.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    n.putExtra("eventImage", eventImage);
                    n.putExtra("eventName", eventName);
                    n.putExtra("eventHost", eventHost);
                    n.putExtra("eventDate", eventDate);
                    n.putExtra("eventTimeStart", eventTimeStart);
                    n.putExtra("eventLocation", eventLocation);
                    n.putExtra("contactNo", eventContactNo);
                    n.putExtra("contactPerson", eventContactPerson);
                    n.putExtra("eventPoints", eventSkills);
                    n.putExtra("id", id);
                    n.putExtra("activity_id", activity_id);
                    n.putExtra("api_token", api_token);
                    n.putExtra("profileId", profile_id);
                    n.putExtra("volunteerStatus", volunteerStatus);
                    n.putExtra("foundationImage", foundation_image);
                    n.putExtra("foundation_id", foundation_id);
                    mContext.startActivity(n);
                    break;

                case R.id.eventHost:
                    n = new Intent(mContext, FoundationProfileActivity.class);
                    n.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    n.putExtra("foundation_id", foundation_id);
                    n.putExtra("foundation_name", eventHost);
                    mContext.startActivity(n);
                    break;


                case R.id.clickedEventHost:
                    n = new Intent(mContext, FoundationProfileActivity.class);
                    n.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    n.putExtra("foundation_id", foundation_id);
                    n.putExtra("foundation_name", eventHost);
                    mContext.startActivity(n);
                    break;

                case R.id.foundation_img:
                    n = new Intent(mContext, FoundationProfileActivity.class);
                    n.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    n.putExtra("foundation_id", foundation_id);
                    n.putExtra("foundation_name", eventHost);
                    mContext.startActivity(n);
                    break;

            }
        }
    }
}
