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

public class ProfileFragment extends Fragment {

    String imageUrl, profName, id;
    ImageView profilePicture;
    TextView profileName;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_layout, container, false);
        

        profilePicture = (ImageView)v.findViewById(R.id.profilePictureProfile);
        profileName = (TextView)v.findViewById(R.id.profileNameProfile);

        readBundle(getArguments());

        Glide.with(getContext()).load("https://graph.facebook.com/"+ id +"/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D1877377522288783").centerCrop().crossFade().into(profilePicture);
        profileName.setText(profName);

        return v;
    }

    public static ProfileFragment newInstance(String imageUrl, String name, String id) {
        
        Bundle args = new Bundle();
        args.putString("image", imageUrl);
        args.putString("name", name);
        args.putString("id", id);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void readBundle(Bundle bundle){
        if(bundle != null){
            imageUrl = bundle.getString("image");
            profName = bundle.getString("name");
            id = bundle.getString("id");
        }
    }
}
