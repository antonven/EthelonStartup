package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import org.w3c.dom.Text;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/27/2017.
 */

public class HomeActivitiesListAdapter extends RecyclerView.Adapter<HomeActivitiesListAdapter.ViewHolder>{

    Context mContext;
    ArrayList<ActivityModel> activities;

    public HomeActivitiesListAdapter(Context mContext, ArrayList<ActivityModel> activities) {
        this.mContext = mContext;
        this.activities = activities;
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
        holder.eventAddress.setText(activities.get(position).getActivityAddress());
        holder.eventHost.setText(activities.get(position).getActivityLocation());
        holder.eventDate.setText(activities.get(position).getActivityDate());
        holder.eventTimeStart.setText(activities.get(position).getActivityStart());
        holder.eventVolunteers.setText(activities.get(position).getContactId());
        holder.eventPoints.setText(activities.get(position).getActivityPoints());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FoldingCell fc;
        TextView eventName, eventHost, eventAddress, eventDate, eventTimeStart, eventVolunteers, eventPoints;
        TextView clickedName, clickedHost, clickedAddress, clickedDate, clickTimeStart, clickVolunteers, clickedPoints, viewActivity;

        public ViewHolder(View itemView) {
            super(itemView);

            fc = (FoldingCell)itemView.findViewById(R.id.foldingCell);
            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventHost = (TextView)itemView.findViewById(R.id.eventHost);
            eventAddress = (TextView)itemView.findViewById(R.id.eventAddress);
            eventDate = (TextView)itemView.findViewById(R.id.eventDate);
            eventTimeStart = (TextView)itemView.findViewById(R.id.eventTimeStart);
            eventVolunteers = (TextView)itemView.findViewById(R.id.title_volunteers_count);
            eventPoints = (TextView)itemView.findViewById(R.id.eventPoints);
            viewActivity = (TextView)itemView.findViewById(R.id.viewActivityDetailsBtn);

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
//            mContext.startActivity(new Intent(mContext, ));
        }
    }
}
