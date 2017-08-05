package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment {

    TextView eventDate1, eventTimeStart1, eventLocation1;


    public EventDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        String eventName = getArguments().getString("eventName");
        String eventHost = getArguments().getString("eventHost");
        String eventDate = getArguments().getString("eventDate");
        String eventTimeStart = getArguments().getString("eventTimeStart");
        String eventLocation = getArguments().getString("eventLocation");

        eventDate1 = (TextView)view.findViewById(R.id.eventDate);
        eventTimeStart1 = (TextView)view.findViewById(R.id.eventTimeStart);
        eventLocation1 = (TextView)view.findViewById(R.id.eventLocation);

        eventDate1.setText(eventDate);
        eventTimeStart1.setText(eventTimeStart);
        eventLocation1.setText(eventLocation);


        return view;
    }

}
