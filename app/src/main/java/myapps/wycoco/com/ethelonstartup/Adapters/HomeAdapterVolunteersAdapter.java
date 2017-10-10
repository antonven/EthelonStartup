package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 10/10/2017.
 */

public class HomeAdapterVolunteersAdapter extends RecyclerView.Adapter<HomeAdapterVolunteersAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<UserModel> userModels = new ArrayList<>();

    public HomeAdapterVolunteersAdapter(Context mContext, ArrayList<UserModel> userModels) {
        this.mContext = mContext;
        this.userModels = userModels;
    }

    @Override
    public HomeAdapterVolunteersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter_volunteers_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeAdapterVolunteersAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(userModels.get(position).getUserImage())
                .centerCrop().crossFade().into(holder.volImage);
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView volImage;
        public ViewHolder(View itemView) {
            super(itemView);

            volImage = (ImageView)itemView.findViewById(R.id.volImage);
        }
    }
}
