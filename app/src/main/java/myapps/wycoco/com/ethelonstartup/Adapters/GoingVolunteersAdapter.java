package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.ProfileActivity;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/5/2017.
 */

public class GoingVolunteersAdapter extends RecyclerView.Adapter<GoingVolunteersAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<UserModel> users = new ArrayList<>();

    public GoingVolunteersAdapter(Context mContext, ArrayList<UserModel> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @Override
    public GoingVolunteersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.going_volunteers_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(GoingVolunteersAdapter.ViewHolder holder, int position) {
        holder.volunteerFirstName.setText(users.get(position).getUserFirstName());
//        Glide.with(mContext).load(users.get(position).getUserImage())
//                .centerCrop().crossFade().into(holder.volunteerImage);
        String img = users.get(position).getUserImage();

        if(!img.equals("null")) {
            Glide.with(mContext).load(users.get(position).getUserImage())
                    .centerCrop().crossFade().into(holder.volunteerImage);
        }else{
            Log.e("BOGO_KA", "PISTENG SUD SA ELSE YAWA");
            holder.volunteerImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_person_black_24dp));
        }
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

            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Black.ttf");
            volunteerFirstName = (TextView)itemView.findViewById(R.id.volunteerFirstName);
            volunteerFirstName.setTypeface(typeface);
            volunteerImage = (ImageView)itemView.findViewById(R.id.volunteerPicture);



            volunteerImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("fbProfilePicture", "");
                    intent.putExtra("fbProfileName", "");
                    intent.putExtra("profileId", "");
                    intent.putExtra("message", "false");
                    intent.putExtra("volunteer_id",users.get(getAdapterPosition()).getUser_id());
                    intent.putExtra("api_token", users.get(getAdapterPosition()).getUser_token());
                    Log.e("FromLeaderboardAdapter",users.get(getAdapterPosition()).getUser_id());


                    mContext.startActivity(intent);
                }
            });
        }
    }
}
