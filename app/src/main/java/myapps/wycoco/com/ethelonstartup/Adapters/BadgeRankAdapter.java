package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 2/1/2018.
 */

public class BadgeRankAdapter extends RecyclerView.Adapter<BadgeRankAdapter.ViewHolder> {

    Context context;
    private ArrayList<String> badgeState = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<ImageView> imageviews;

    public BadgeRankAdapter(Context context, ArrayList<String> badgeState, ArrayList<String> images){
        this.context = context;
        this.badgeState = badgeState;
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.badge_image_views, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        for(int i = 0; i < badgeState.size(); i++) {
            if (badgeState.get(i).equals("earned")) {
                Glide.with(context).load(images.get(position)).fitCenter().crossFade().into(holder.imageView);

            }else if(badgeState.get(i).equals("notearned")){
                holder.imageView.setColorFilter(ContextCompat.getColor(context, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                Glide.with(context).load(images.get(position)).fitCenter().crossFade().into(holder.imageView);

            }
        }


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.badgeImage);
        }
    }
}
