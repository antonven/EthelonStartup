package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/30/2017.
 */

public class LoginViewPagerAdapter extends PagerAdapter {

    private String [] messages = {"From indifference to compassion", "From individualism to bayanihan", "From diversity to Ethelon"};
    LayoutInflater layoutInflater;
    Context mContext;

    public LoginViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return messages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.second_layout, null);
        TextView textView = (TextView)view.findViewById(R.id.textView2);
        textView.setTextColor(Color.parseColor("#ffffff"));
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Black.ttf");
        textView.setTypeface(typeface);
        textView.setText(messages[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view ,0);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        vp.removeView(view);
    }

}
