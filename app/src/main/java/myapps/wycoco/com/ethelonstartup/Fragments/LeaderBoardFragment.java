package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Adapters.LeaderBoardCustomAdapter;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;
import shivam.developer.featuredrecyclerview.FeatureLinearLayoutManager;
import shivam.developer.featuredrecyclerview.FeaturedRecyclerView;

/**
 * Created by dell on 7/15/2017.
 *
 *
 */

public class LeaderBoardFragment extends Fragment {

    ArrayList<UserModel> userLeaders = new ArrayList<>();
    FeaturedRecyclerView featuredRecyclerView;
    LeaderBoardCustomAdapter adapter;
    UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        createDummyDataList();

        featuredRecyclerView = (FeaturedRecyclerView)v.findViewById(R.id.featured_recycler_view);
        FeatureLinearLayoutManager layoutManager = new FeatureLinearLayoutManager(getContext());
        featuredRecyclerView.setLayoutManager(layoutManager);
        adapter = new LeaderBoardCustomAdapter(getContext(), userLeaders);
        featuredRecyclerView.setAdapter(adapter);

        return v;
    }

    private void createDummyDataList(){
        for(int i = 0; i < 10; i++){
            String leaderImage = "https://graph.facebook.com/1877377522288783/picture?height=500&width=500&migration_overrides=%7Boctober_2012%3Atrue%7D";
            userModel = new UserModel();

            userModel.setUserImage(leaderImage);
            userModel.setUserFirstName("Anton Ven");
            userLeaders.add(userModel);
        }
    }
}
