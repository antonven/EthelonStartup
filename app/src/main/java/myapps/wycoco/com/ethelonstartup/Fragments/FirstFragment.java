package myapps.wycoco.com.ethelonstartup.Fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/15/2017.
 */

public class FirstFragment extends Fragment {

    String imageUrl, profName, id;
    ImageView profilePicture;
    TextView profileName;

    public FirstFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_layout, container, false);

        return v;

    }



}
