package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Adapters.GoingVolunteersAdapter;
import myapps.wycoco.com.ethelonstartup.Models.UserModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoingVolunteersFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    ArrayList<UserModel> users = new ArrayList<>();
    GoingVolunteersAdapter goingVolunteersAdapter;

    public GoingVolunteersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_going_volunteers, container, false);

        for(int i = 0; i < 10; i++) {
            UserModel user = new UserModel("https://graph.facebook.com/1877377522288783/picture?height=500&width=500&migration_overrides=%7Boctober_2012%3Atrue%7D"
                    , "Anton Ven", "Wycoco", "Volunteer");

            users.add(user);
        }

        goingVolunteersAdapter = new GoingVolunteersAdapter(getContext(), users);
        recyclerView = (RecyclerView)view.findViewById(R.id.recViewVolunteers);
        linearLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        goingVolunteersAdapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(goingVolunteersAdapter);


        return view;
    }

}
