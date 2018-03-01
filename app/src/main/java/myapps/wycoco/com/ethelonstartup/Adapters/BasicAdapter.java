package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 10/11/2017.
 */

public class BasicAdapter extends BaseAdapter {

    ArrayList<Integer> drawable = new ArrayList<>();
    Context mContext;

    public BasicAdapter(ArrayList<Integer> drawable, Context mContext) {
        this.drawable = drawable;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return drawable.size();
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
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(drawable.get(position));
        return imageView;
    }
}
