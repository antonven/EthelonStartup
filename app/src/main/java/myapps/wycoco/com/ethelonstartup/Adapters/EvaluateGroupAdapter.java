package myapps.wycoco.com.ethelonstartup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

import myapps.wycoco.com.ethelonstartup.Fragments.DialogFragmentAttendanceSuccess;
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

                    FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                    DialogFragmentAttendanceSuccess dialog = new DialogFragmentAttendanceSuccess();
                    Bundle n = new Bundle();
                    n.putString("api_token", api_token);
                    n.putString("activity_id", activity_id);
                    n.putString("volunteer_id", volunteer_id);
                    dialog.setArguments(n);
                    dialog.show(fm, "EvaluateCriteria");
                }
            });

        }
    }
}


