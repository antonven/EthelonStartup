package myapps.wycoco.com.ethelonstartup.Adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import org.w3c.dom.Text;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.AttendanceScanner;
import myapps.wycoco.com.ethelonstartup.Activities.EvaluateGroupActivity;
import myapps.wycoco.com.ethelonstartup.Activities.PortfolioEventDetailsActivity;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/22/2017.
 */

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder>{

    Context mContext;
    private ArrayList<PortfolioModel> activities = new ArrayList<>();
    private String api_token, id, volunteer_id, profile_id;
    private ArrayList<Integer> images;

    public PortfolioAdapter(Context mContext, ArrayList<PortfolioModel> activities, String id, String api_token, String volunteer_id, String profile_id) {
        this.mContext = mContext;
        this.activities = activities;
        this.api_token = api_token;
        this.volunteer_id = volunteer_id;
        this.profile_id = profile_id;
        this.id = id;
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
        holder.clickedEventVolunteers.setText(activities.get(position).getActivityGroup());
//        holder.eventVolunteers.setText(String.valueOf(activities.get(position).getVolunteer_count()));
        holder.clickedPoints.setText(activities.get(position).getActivityPoints());
        holder.contactPerson.setText(activities.get(position).getContactPerson());
        holder.activityContact.setText(activities.get(position).getActivityContact());
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventAddress.setText(activities.get(position).getActivityLocation());
        holder.eventHost.setText(activities.get(position).getFoundationName());
        holder.eventDate.setText(activities.get(position).getActivityDate());
        holder.eventTimeStart.setText(activities.get(position).getActivityStart());
        Glide.with(mContext).load(activities.get(position).getActivityImage())
                .centerCrop().crossFade().into(holder.clickedActivityImage);
        Glide.with(mContext).load(activities.get(position).getFoundationImage())
                .centerCrop().crossFade().into(holder.clickedFoundationImage);
        holder.joinCount.setText(activities.get(position).getVolunteer_count() + " have joined this activity");
        if(activities.get(position).getVolunteerStatus().equals("1")){
            holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ribbon_done2));
//            holder.attendBtn.setVisibility(View.GONE);

        }else {
            holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ribbon2));
            if(activities.get(position).getTimeIn() == 0){
                holder.attendBtn.setVisibility(View.VISIBLE);
            }else {
                holder.attendBtn.setVisibility(View.GONE);
                holder.timeOutBtn.setVisibility(View.VISIBLE);
            }

        }


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

        holder.gridView.setAdapter(new BasicAdapter(images, mContext));

    }


    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventDate, eventTimeStart, eventName, eventAddress, eventHost, eventVolunteers, eventPoints,
        clickedEventHost, clickedEventDescription, clickedEventPoints, clickedEventVolunteers, clickedPoints,
        contactPerson, activityContact, clickedEventName, viewActivity, joinCount;
        ImageView status, clickedActivityImage, clickedFoundationImage;
        Button attendBtn, timeOutBtn;
        FoldingCell fc;
        GridView gridView;
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

//            recyclerView = (RecyclerView)itemView.findViewById(R.id.goingRecycler);
            attendBtn = (Button)itemView.findViewById(R.id.attendBtn);
            timeOutBtn = (Button)itemView.findViewById(R.id.timeOut);
            fc = (FoldingCell)itemView.findViewById(R.id.foldingCell);
            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventHost = (TextView)itemView.findViewById(R.id.eventHost);
            eventAddress = (TextView)itemView.findViewById(R.id.eventAddress);
            eventDate = (TextView)itemView.findViewById(R.id.eventDate);
            eventTimeStart = (TextView)itemView.findViewById(R.id.eventTimeStart);
//            eventVolunteers = (TextView)itemView.findViewById(R.id.title_volunteers_count);
//            eventPoints = (TextView)itemView.findViewById(R.id.eventPoints);
            status = (ImageView) itemView.findViewById(R.id.status);
            clickedEventName= (TextView)itemView.findViewById(R.id.clickedEventName);
            clickedEventHost = (TextView)itemView.findViewById(R.id.clickedEventHost);
            clickedEventPoints = (TextView)itemView.findViewById(R.id.pointsEarned);
            clickedEventDescription = (TextView)itemView.findViewById(R.id.clickedEventDescription);
            clickedEventVolunteers = (TextView)itemView.findViewById(R.id.clickedEventVolunteerCount);
            clickedPoints = (TextView)itemView.findViewById(R.id.clickedEventPoints);
            contactPerson = (TextView)itemView.findViewById(R.id.contactPerson);
            activityContact = (TextView)itemView.findViewById(R.id.activityContact);
            clickedActivityImage = (ImageView)itemView.findViewById(R.id.head_image);
            viewActivity = (TextView)itemView.findViewById(R.id.viewActivityDetailsBtn);
            clickedFoundationImage = (ImageView)itemView.findViewById(R.id.foundationImage);
            joinCount = (TextView)itemView.findViewById(R.id.joinCount);
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
//            eventPoints.setTypeface(typeface);

            viewActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
                    int points = activities.get(getAdapterPosition()).getPoints();


                    Intent in = new Intent(mContext, PortfolioEventDetailsActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("eventImage", eventImage);
                    in.putExtra("eventName", eventName);
                    in.putExtra("eventHost", eventHost);
                    in.putExtra("eventDate", eventDate);
                    in.putExtra("eventTimeStart", eventTimeStart);
                    in.putExtra("eventLocation", eventLocation);
                    in.putExtra("contactNo", eventContactNo);
                    in.putExtra("contactPerson", eventContactPerson);
                    in.putExtra("eventPoints", eventSkills);
                    in.putExtra("id", id);
                    in.putExtra("activity_id", activity_id);
                    in.putExtra("api_token", api_token);
                    in.putExtra("profileId", profile_id);
                    in.putExtra("volunteer_id", volunteer_id);
                    in.putExtra("points",points);
                    in.putExtra("from","normal");

                    mContext.startActivity(in);
                }
            });

            attendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String activity_id = activities.get(getAdapterPosition()).getActivityId();
                   //** Log.i("Volunteer_id", "PortAdapter" + volunteer_id + activity_id);
//
//                    Intent n = new Intent(mContext, AttendanceScanner.class);
//                    n.putExtra("activity_id", activity_id);
//                    n.putExtra("api_token", api_token);
//                    n.putExtra("volunteer_id", volunteer_id);
//                    n.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(n);



//
                    Intent n = new Intent(mContext, EvaluateGroupActivity.class);
                    n.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    n.putExtra("api_token", api_token);
                    n.putExtra("activity_id", activity_id);
                    n.putExtra("volunteer_id", volunteer_id);

                    mContext.startActivity(n);
                }
            });

            timeOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String activity_id = activities.get(getAdapterPosition()).getActivityId();

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
    }




}
