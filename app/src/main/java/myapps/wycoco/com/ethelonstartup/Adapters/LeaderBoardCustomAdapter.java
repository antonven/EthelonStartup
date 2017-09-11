//package myapps.wycoco.com.ethelonstartup.Adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.media.Image;
//import android.support.v7.widget.RecyclerView;
//import android.transition.Explode;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//
//import myapps.wycoco.com.ethelonstartup.Models.UserModel;
//import myapps.wycoco.com.ethelonstartup.R;
//import shivam.developer.featuredrecyclerview.FeatureRecyclerViewAdapter;
//import shivam.developer.featuredrecyclerview.FeaturedRecyclerView;
//
///**
// * Created by dell on 8/6/2017.
// */
//
//public class LeaderBoardCustomAdapter extends FeatureRecyclerViewAdapter<LeaderBoardCustomAdapter.CustomRecyclerViewHolder> {
//
//    private Context mContext;
//    private ArrayList<UserModel> userLeader = new ArrayList<>();
//    private Activity activity;
//
//    public LeaderBoardCustomAdapter(Context mContext, ArrayList<UserModel> userLeader) {
//        this.mContext = mContext;
//        this.userLeader = userLeader;
//    }
//
//    @Override
//    public LeaderBoardCustomAdapter.CustomRecyclerViewHolder onCreateFeaturedViewHolder(ViewGroup viewGroup, int i) {
//        return new CustomRecyclerViewHolder(
//                LayoutInflater.from(viewGroup.getContext())
//                        .inflate(R.layout.leaderboard_list_item, viewGroup, false));
//    }
//
//    @Override
//    public void onBindFeaturedViewHolder(LeaderBoardCustomAdapter.CustomRecyclerViewHolder holder, int position) {
//        Glide.with(mContext)
//                .load(userLeader.get(position).getUserImage()).into(holder.leaderImage);
//        holder.leaderName.setText(userLeader.get(position).getUserFirstName());
//    }
//
//    @Override
//    public int getFeaturedItemsCount() {
//        return userLeader.size();
//    }
//
//    @Override
//    public void onSmallItemResize(LeaderBoardCustomAdapter.CustomRecyclerViewHolder holder, int position, float offset) {
//        holder.leaderName.setAlpha(offset / 100f);
//    }
//
//    @Override
//    public void onBigItemResize(LeaderBoardCustomAdapter.CustomRecyclerViewHolder holder, int position, float offset) {
//        holder.leaderName.setAlpha(offset / 100f);
//    }
//
//    public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView leaderImage;
//        TextView leaderName;
//
//        public CustomRecyclerViewHolder(final View itemView) {
//            super(itemView);
//
//            leaderImage = (ImageView)itemView.findViewById(R.id.leaderImage);
//            leaderName = (TextView) itemView.findViewById(R.id.leaderName);
//
//            leaderImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Explode explode = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        explode = new Explode();
//                        activity.getWindow().setExitTransition(explode);
//                    }
//
//
//
//                }
//            });
//        }
//    }
//
//}
