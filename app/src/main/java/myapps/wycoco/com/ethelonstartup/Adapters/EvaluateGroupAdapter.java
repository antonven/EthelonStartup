package myapps.wycoco.com.ethelonstartup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.VolunteerRateActivity;
import myapps.wycoco.com.ethelonstartup.Fragments.RateVolunteerDialogFragment;
import myapps.wycoco.com.ethelonstartup.Fragments.VolunteerRatingFragment;
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
    VolunteerRatingFragment volunteerRatingFragment;

    public EvaluateGroupAdapter(Context mContext, ArrayList<RateVolunteer> volunteers, String activity_id, String api_token, String volunteer_id,VolunteerRatingFragment volunteerRatingFragment) {
        this.mContext = mContext;
        this.volunteers = volunteers;
        this.activity_id = activity_id;
        this.api_token = api_token;
        this.volunteer_id = volunteer_id;
        this.volunteerRatingFragment = volunteerRatingFragment;
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
        holder.status.setText(volunteers.get(position).getStatus());
        if(volunteers.get(position).getStatus().equals("Mana")) {
            holder.evaluateStatus.setColorFilter(Color.parseColor("#000000"));
        }

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
        TextView status;

        public ViewHolder(View itemView) {
            super(itemView);

            volunteerImage = (ImageView)itemView.findViewById(R.id.rateVolunteerImage);
            volunteerName = (TextView)itemView.findViewById(R.id.rateVolunteerName);
            ratingBar = (RatingBar)itemView.findViewById(R.id.rateVolunteerRate);
            evaluateStatus = (ImageView)itemView.findViewById(R.id.rateVolunteerStatus);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.rateLinear);
            status = (TextView)itemView.findViewById(R.id.status);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String volunteer_name = volunteers.get(getAdapterPosition()).getVolunteer_name();
                    String activity_group_id = volunteers.get(getAdapterPosition()).getVolunteer_group_id();
                    String volunteer_rate_id = volunteers.get(getAdapterPosition()).getVolunteer_id();
                    FragmentManager fm = ((AppCompatActivity)mContext).getSupportFragmentManager();

                    RateVolunteerDialogFragment rateVolunteerDialogFragment = new RateVolunteerDialogFragment();
                    rateVolunteerDialogFragment.setTargetFragment(volunteerRatingFragment,0);

                    //paghimo og laing dialog nga if iyang status kay mana kay kato nga dialog ang mo show nya if wala pa ang status kay ang ratevolunteerdialogfragment

                    Bundle n = new Bundle();
                    n.putString("volunteer_name", volunteer_name);
                    n.putString("activity_group_id", activity_group_id);
                    n.putString("volunteer_id_to_rate", volunteer_rate_id);
                    n.putString("api_token", api_token);
                    n.putInt("criteria_size", criterias.size());
                    n.putString("activity_id", activity_id);
                    n.putString("volunteer_id", volunteer_id);
                    n.putInt("index",getAdapterPosition());
                    rateVolunteerDialogFragment.setArguments(n);
                    rateVolunteerDialogFragment.show(fm, "Rate");


                }
            });

        }
    }
    public interface OnClickListener{
        public void onClick();
    }

}


