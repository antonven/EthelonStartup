package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 1/29/2018.
 */

public class BasicGlideAdatper extends BaseAdapter{

        ArrayList<String> images = new ArrayList<>();
        Context mContext;

        public BasicGlideAdatper(ArrayList<String> images, Context mContext) {
            this.images = images;
            this.mContext = mContext;
        }

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

            if (view == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.black_overlay), android.graphics.PorterDuff.Mode.SRC_IN);
                imageView.setPadding(8, 8, 8, 8);

            }
            else
            {
                imageView = (ImageView) view;
            }
//            imageView.setImageResource(images.get(position));
            Glide.with(mContext).load(images.get(position))
                    .fitCenter().crossFade().into(imageView);
            return imageView;
        }


}
