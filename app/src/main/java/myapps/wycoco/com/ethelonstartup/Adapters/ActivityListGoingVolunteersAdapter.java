package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/29/2017.
 */

public class ActivityListGoingVolunteersAdapter extends RecyclerView.Adapter<ActivityListGoingVolunteersAdapter.ViewHolder> {

    ArrayList<UserModel> users = new ArrayList<>();
    Context mContext;


    public ActivityListGoingVolunteersAdapter(Context mContext, ArrayList<UserModel> users) {
        this.users = users;
        this.mContext = mContext;
        Log.e("GVADAPTER", "pictures: " + users.size());
    }

    @Override
    public ActivityListGoingVolunteersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.going_volunteer_activity_list_item, parent,false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ActivityListGoingVolunteersAdapter.ViewHolder holder, int position) {

        Glide.with(mContext).load(users.get(position).getUserImage())
                .centerCrop().crossFade().into(holder.volunteerImage);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView volunteerImage;

        public ViewHolder(View itemView) {
            super(itemView);

            volunteerImage = (ImageView)itemView.findViewById(R.id.goingVolunteerImage);
        }
    }
}
