package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Models.RateVolunteer;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/28/2017.
 */

public class EvaluateGroupPagerAdapter extends PagerAdapter {

    ArrayList<RateVolunteer> volunteers = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context mContext;

    public EvaluateGroupPagerAdapter() {
    }

    public EvaluateGroupPagerAdapter(ArrayList<RateVolunteer> volunteers, Context mContext) {
        this.volunteers = volunteers;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return volunteers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.evaluate_group_item, null);
        ImageView volunteerImage = (ImageView)view.findViewById(R.id.volunteerPicture);
        TextView volunteerName = (TextView)view.findViewById(R.id.volunteerFirstName);
        RecyclerView recyclerCriteria = (RecyclerView)view.findViewById(R.id.recyclerCriteria);

        Glide.with(mContext).load(volunteers.get(position).getVolunteer_image())
                .centerCrop().crossFade().into(volunteerImage);
        volunteerName.setText(volunteers.get(position).getVolunteer_name());
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
