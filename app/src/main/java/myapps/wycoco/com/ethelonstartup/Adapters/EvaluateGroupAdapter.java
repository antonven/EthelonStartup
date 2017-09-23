package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.VolunteerRateActivity;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 9/11/2017.
 */

public class EvaluateGroupAdapter extends RecyclerView.Adapter<EvaluateGroupAdapter.ViewHolder>{

    Context mContext;
    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    String activity_id, api_token, volunteer_id;
    ArrayList<EvaluationCriteria> criterias = new ArrayList<>();


    public EvaluateGroupAdapter(Context mContext, ArrayList<RateVolunteer> volunteers, String activity_id, String api_token, String volunteer_id) {
        this.mContext = mContext;
        this.volunteers = volunteers;
        this.activity_id = activity_id;
        this.api_token = api_token;
        this.volunteer_id = volunteer_id;
    }

    public EvaluateGroupAdapter(Context mContext, ArrayList<EvaluationCriteria> criterias){
        this.mContext = mContext;
        this.criterias = criterias;
    }

    @Override
    public EvaluateGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_volunteer_item, parent, false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(EvaluateGroupAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(volunteers.get(position).getVolunteer_image())
                .centerCrop().crossFade().into(holder.volunteerImage);
        holder.volunteerName.setText(volunteers.get(position).getVolunteer_name());
        if(criterias.size() == 0){
            holder.ratingBar.setRating(0);

        }else{
            holder.ratingBar.setRating(criterias.get(position).getCriteriaRating());
        }
    }

    @Override
    public int getItemCount() {
        return volunteers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView volunteerImage, evaluateStatus;
        TextView volunteerName;
        RatingBar ratingBar;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            volunteerImage = (ImageView)itemView.findViewById(R.id.rateVolunteerImage);
            volunteerName = (TextView)itemView.findViewById(R.id.rateVolunteerName);
            ratingBar = (RatingBar)itemView.findViewById(R.id.rateVolunteerRate);
            evaluateStatus = (ImageView)itemView.findViewById(R.id.rateVolunteerStatus);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.rateLinear);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String volunteer_name = volunteers.get(getAdapterPosition()).getVolunteer_name();
                    String activity_group_id = volunteers.get(getAdapterPosition()).getVolunteer_group_id();
                    String volunteer_rate_id = volunteers.get(getAdapterPosition()).getVolunteer_id();

                    Intent n = new Intent(mContext, VolunteerRateActivity.class);
                    n.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    n.putExtra("api_token", api_token);
                    n.putExtra("activity_id", activity_id);
                    n.putExtra("volunteer_id", volunteer_id);
                    n.putExtra("volunteer_name", volunteer_name);
                    n.putExtra("volunteer_id_to_rate", volunteer_rate_id);
                    n.putExtra("activitygroups_id", activity_group_id);
                    n.putExtra("criteri_size", criterias.size());
                    mContext.startActivity(n);

                }
            });

        }
    }
    public interface OnClickListener{
        public void onClick();
    }

}


