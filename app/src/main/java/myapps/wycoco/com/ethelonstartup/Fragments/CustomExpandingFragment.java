package myapps.wycoco.com.ethelonstartup.Fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.qslll.library.fragments.ExpandingFragment;

import myapps.wycoco.com.ethelonstartup.Libraries.Travel;

/**
 * Created by dell on 7/26/2017.
 */

public class CustomExpandingFragment extends ExpandingFragment {

    static final String ARG_TRAVEL = "ARG_TRAVEL";
    Travel travel;

    public static CustomExpandingFragment newInstance(Travel travel){
        CustomExpandingFragment fragment = new CustomExpandingFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TRAVEL, travel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            travel = args.getParcelable(ARG_TRAVEL);
        }
    }

    @Override
    public Fragment getFragmentTop() {
        return null;
    }

    @Override
    public Fragment getFragmentBottom() {
        return null;
    }
}
