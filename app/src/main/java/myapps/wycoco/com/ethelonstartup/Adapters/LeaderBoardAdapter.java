package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by dell on 9/1/2017.
 */

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    Context mContext;
    ArrayList<UserModel> users = new ArrayList<>();

    public LeaderBoardAdapter(Context mContext, ArrayList<UserModel> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @Override
    public LeaderBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_item_layout, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(LeaderBoardAdapter.ViewHolder holder, int position) {

        Glide.with(mContext).load(users.get(position).getUserImage())
                .centerCrop().crossFade().into(holder.leaderboardImage);
        holder.leaderboardName.setText(users.get(position).getUserFirstName());

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
        }
    }
}
