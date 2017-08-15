package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;

import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.PortfolioModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/22/2017.
 */

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder>{
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    Context mContext;
    ArrayList<PortfolioModel> activities;

    public PortfolioAdapter() {
    }

    public PortfolioAdapter(Context mContext, ArrayList<PortfolioModel> activities) {
        this.mContext = mContext;
        this.activities = activities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_layout_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(PortfolioAdapter.ViewHolder holder, int position) {
//        holder.eventName.setText(activities.get(position).getActivityName());
//        holder.time.setText(activities.get(position).getActivityStart());
//        holder.eventHost.setText(activities.get(position).getFoundationId());
//        holder.points.setText(activities.get(position).getActivityPoints());
//        holder.eventName.setText(activities.get(position).getActivityName());
//        holder.eventName.setText(activities.get(position).getActivityName());
//        holder.eventName.setText(activities.get(position).getActivityName());
//        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventAddress.setText(activities.get(position).getActivityLocation());
        holder.eventHost.setText(activities.get(position).getFoundationId());
        holder.eventDate.setText(activities.get(position).getActivityDate());
        holder.eventTimeStart.setText(activities.get(position).getActivityStart());
        holder.eventVolunteers.setText(activities.get(position).getActivityGroup());
        holder.eventPoints.setText(activities.get(position).getActivityPoints());

        if(activities.get(position).getVolunteerStatus().equals("true")){
            holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ribbon_done2));
        }else
            holder.status.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ribbon2));


    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView price;
        TextView eventDate;
        TextView eventTimeStart;
        TextView eventName;
        TextView eventAddress;
        TextView eventHost;
        TextView eventVolunteers;
        TextView eventPoints;
        TextView points;
        TextView contentRequestBtn;
        ImageView status;
        ImageView im;
        TextView clickedActivityTitle;
        TextView clickedActivityHost;
        TextView clickedActivityDate;
        TextView clickedActivityTime;
        TextView clickedActivityCity;
        TextView clickedActivityStreet;
        TextView clickedActivityContactPerson;
        TextView clickedActvitiyContactNumber;
        TextView getClickedActivityType;

        FoldingCell fc;

        public ViewHolder(View itemView) {
            super(itemView);

            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventHost = (TextView)itemView.findViewById(R.id.eventHost);
            eventAddress = (TextView)itemView.findViewById(R.id.eventAddress);
            eventDate = (TextView)itemView.findViewById(R.id.eventDate);
            eventTimeStart = (TextView)itemView.findViewById(R.id.eventTimeStart);
            eventVolunteers = (TextView)itemView.findViewById(R.id.title_volunteers_count);
            eventPoints = (TextView)itemView.findViewById(R.id.eventPoints);

            contentRequestBtn = (TextView)itemView.findViewById(R.id.viewActivityDetailsBtn);
            status = (ImageView) itemView.findViewById(R.id.status);
            im = (ImageView) itemView.findViewById(R.id.title);
            clickedActivityTitle = (TextView)itemView.findViewById(R.id.clickedEventName);
            clickedActivityHost = (TextView)itemView.findViewById(R.id.clickedEventHost);
            clickedActivityTime = (TextView)itemView.findViewById(R.id.clickedEventTimeStart);


//            fc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    fc.toggle(false);
//                }
//            });
        }
    }
}
