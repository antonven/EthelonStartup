package myapps.wycoco.com.ethelonstartup.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Adapters.NotificationsAdapter;
import myapps.wycoco.com.ethelonstartup.Models.NotificationsModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {

    RecyclerView recyclerView;
    NotificationsAdapter notificationsAdapter;
    ArrayList<NotificationsModel> notifications = new ArrayList<>();
    NotificationsModel notificationsModel;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recViewNotif);

        for(int i = 0; i<10; i++) {

            notificationsModel = new NotificationsModel("1", "Kirsten Repunte", "", "New", "has invited you to join this activity.",
                    "invite", "4 hours ago.");

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            notifications.add(notificationsModel);
            notificationsAdapter = new NotificationsAdapter(getContext(), notifications);
            recyclerView.setAdapter(notificationsAdapter);
        }

        return view;
    }

}
