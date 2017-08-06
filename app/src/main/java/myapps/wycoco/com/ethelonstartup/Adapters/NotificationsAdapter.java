package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Models.NotificationsModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/6/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<NotificationsModel> notifs =  new ArrayList<>();

    public NotificationsAdapter() {
    }

    public NotificationsAdapter(Context mContext, ArrayList<NotificationsModel> notifs) {
        this.mContext = mContext;
        this.notifs = notifs;
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(NotificationsAdapter.ViewHolder holder, int position) {

        Glide.with(mContext).load(notifs.get(position).getUserImage())
                .centerCrop().crossFade().into(holder.userImage);
        holder.userName.setText(notifs.get(position).getUserName());
        holder.notifContent.setText(notifs.get(position).getContent());
        holder.notifTime.setText(notifs.get(position).getTime());
        Glide.with(mContext).load(notifs.get(position).getUserImage())
                .centerCrop().crossFade().into(holder.notifTypeLogo);





    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        ImageView userImage, notifTypeLogo;
        TextView userName, notifContent, notifTime;

        public ViewHolder(View itemView) {
            super(itemView);

            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearNotif);
            userImage = (ImageView)itemView.findViewById(R.id.userImage);
            notifTypeLogo = (ImageView)itemView.findViewById(R.id.notifTypeLogo);
            userName = (TextView)itemView.findViewById(R.id.userName);
            notifContent = (TextView)itemView.findViewById(R.id.notifContent);
            notifTime = (TextView)itemView.findViewById(R.id.notifTime);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            });

        }
    }
}
