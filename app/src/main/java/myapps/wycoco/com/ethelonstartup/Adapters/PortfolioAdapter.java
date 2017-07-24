package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
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
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/22/2017.
 */

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder>{
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    Context mContext;
    ArrayList<ActivityModel> activities;

    public PortfolioAdapter() {
    }

    public PortfolioAdapter(Context mContext, ArrayList<ActivityModel> activities) {
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
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.time.setText(activities.get(position).getActivityStart());
        holder.eventHost.setText(activities.get(position).getActivityAddress());
        holder.points.setText(activities.get(position).getActivityPoints());
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventName.setText(activities.get(position).getActivityName());
        holder.eventName.setText(activities.get(position).getActivityName());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView price;
        TextView date;
        TextView time;
        TextView eventName;
        TextView eventHost;
        TextView volunteersCount;
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

            fc = (FoldingCell)itemView.findViewById(R.id.foldingCell);
            date = (TextView)itemView.findViewById(R.id.title_date_label);
            time = (TextView)itemView.findViewById(R.id.title_time_label);
            eventName = (TextView)itemView.findViewById(R.id.title_event);
            eventHost = (TextView)itemView.findViewById(R.id.title_host);
            volunteersCount = (TextView)itemView.findViewById(R.id.title_volunteers_count);
            points = (TextView)itemView.findViewById(R.id.title_points);
            contentRequestBtn = (TextView)itemView.findViewById(R.id.content_request_btn);
            status = (ImageView) itemView.findViewById(R.id.status);
            im = (ImageView) itemView.findViewById(R.id.title);
            clickedActivityTitle = (TextView)itemView.findViewById(R.id.activityTitle);
            clickedActivityHost = (TextView)itemView.findViewById(R.id.content_name_view);
            clickedActivityTime = (TextView)itemView.findViewById(R.id.content_from_address_2);


            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fc.toggle(false);
                }
            });
        }
    }
}
