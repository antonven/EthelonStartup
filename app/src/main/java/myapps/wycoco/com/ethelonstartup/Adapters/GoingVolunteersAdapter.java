package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/5/2017.
 */

public class GoingVolunteersAdapter extends RecyclerView.Adapter<GoingVolunteersAdapter.ViewHolder> {

    Context mContext;
    ArrayList<UserModel> users = new ArrayList<>();

    public GoingVolunteersAdapter() {
    }

    public GoingVolunteersAdapter(Context mContext, ArrayList<UserModel> users) {
        this.mContext = mContext;
        this.users = users;
        Log.e("USERS ADAPTER", "" + users.size());
    }

    @Override
    public GoingVolunteersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.going_volunteers_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(GoingVolunteersAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(users.get(position).getUserImage())
                .centerCrop().crossFade().into(holder.volunteerImage);
        holder.volunteerFirstName.setText(users.get(position).getUserFirstName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView volunteerImage;
        TextView volunteerFirstName;

        public ViewHolder(View itemView) {
            super(itemView);

            volunteerFirstName = (TextView)itemView.findViewById(R.id.volunteerFirstName);
            volunteerImage = (ImageView)itemView.findViewById(R.id.volunteerPicture);
        }
    }
}
