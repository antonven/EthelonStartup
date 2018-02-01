package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 1/29/2018.
 */

public class BasicGlideAdatper extends BaseAdapter{

        private ArrayList<String> images = new ArrayList<>();
        Context mContext;
        private ArrayList<String> badgeState = new ArrayList<>();

        public BasicGlideAdatper(ArrayList<String> images, Context mContext, ArrayList<String> badgeState) {
            this.images = images;
            this.mContext = mContext;
            this.badgeState = badgeState;
        }

//        @Override
//        public BasicGlideAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_volunteer_item, parent, false);
//            EvaluateGroupAdapter.ViewHolder view = new EvaluateGroupAdapter.ViewHolder(v);
//            return view;
//        }


    @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView;
            Log.e("BADGE_STATE_SIZE", badgeState.size() + " " + position);
            imageView = new ImageView(mContext);


            for (int i = 0; i<badgeState.size(); i++) {
                Log.i("BADGE_STATE", badgeState.get(i) + "hehe");

                if (badgeState.get(i).equals("earned")) {
                    Log.e("NISUD_SA_IF", " BOGO 1");
                    imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setPadding(8, 8, 8, 8);


                } else if (badgeState.get(i).equals("notearned")){
                    Log.e("NISUD_SA_ELSEIF", " BOGO 2 " );

                    imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(8, 8, 8, 8);
                    imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);

                }
            }
            Glide.with(mContext).load(images.get(position)).fitCenter().crossFade().into(imageView);

            return imageView;
//            if(view == null){
//                imageView = new ImageView(mContext) ;
//
//                imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                imageView.setPadding(8, 8, 8, 8);
//            }
//
//
//
//
////            }
//            else
//            {
//                imageView = (ImageView) view;
//            }
//            imageView.setImageResource(images.get(position));


        }


}
