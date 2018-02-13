package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
 * Created by dell on 9/1/2017.
 */

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    Context mContext;
    ArrayList<UserModel> users = new ArrayList<>();
    String api_token;

    public LeaderBoardAdapter(Context mContext, ArrayList<UserModel> users) {
        this.mContext = mContext;
        ;
        this.users = users;
        Log.e("leaderboard size :" , "piste ani" + users.size());
    }

    @Override
    public LeaderBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_item_layout, parent,false);
        ViewHolder view = new ViewHolder(v);


        return view;
    }

    @Override
    public void onBindViewHolder(LeaderBoardAdapter.ViewHolder holder, int position) {

        String img = users.get(position).getUserImage();
        Log.e("position", position + " " + img );

        if(!img.equals("null")) {
            Glide.with(mContext).load(users.get(position).getUserImage())
                    .centerCrop().crossFade().into(holder.leaderboardImage);
        }else{
            Log.e("BOGO_KA", "PISTENG SUD SA ELSE YAWA");
            holder.leaderboardImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_person_black_24dp));
        }
        holder.leaderboardName.setText(users.get(position).getUserFirstName());
        holder.leaderboardPosition.setText(position + 1 + "");
        holder.leaderboardPoints.setText(users.get(position).getUser_points() + " points");


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView leaderboardPosition, leaderboardName, leaderboardPoints;
        ImageView leaderboardImage;

        public ViewHolder(View itemView) {
            super(itemView);

            leaderboardName = (TextView)itemView.findViewById(R.id.leaderboardName);
            leaderboardImage = (ImageView)itemView.findViewById(R.id.leaderboardImage);
            leaderboardPosition = (TextView)itemView.findViewById(R.id.leaderboardPosition);
            leaderboardPoints = (TextView)itemView.findViewById(R.id.leaderboardPoints);

            itemView.setOnClickListener(new View.OnClickListener() {
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
