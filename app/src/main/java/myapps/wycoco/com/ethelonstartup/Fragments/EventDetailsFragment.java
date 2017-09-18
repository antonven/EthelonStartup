package myapps.wycoco.com.ethelonstartup.Fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment {

    TextView eventDate1, eventTimeStart1, eventLocation1, eventSkills1, eventContact1;
    Button joinActvitiyBtn;
    Context mContext;




    public EventDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_event_details, container, false);



        String eventName = getArguments().getString("eventName");
        String eventSkills = getArguments().getString("eventPoints");
        String eventDate = getArguments().getString("eventDate");
        String eventTimeStart = getArguments().getString("eventTimeStart");
        String eventLocation = getArguments().getString("eventLocation");
        String contactNo = getArguments().getString("contactNo");
        String contactPerson = getArguments().getString("contactPerson");
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Black.ttf");


        eventDate1 = (TextView)view.findViewById(R.id.eventDate);

        eventTimeStart1 = (TextView)view.findViewById(R.id.eventTimeStart);
        eventLocation1 = (TextView)view.findViewById(R.id.eventLocation);
        eventSkills1 = (TextView)view.findViewById(R.id.eventSkills);
        eventContact1 = (TextView)view.findViewById(R.id.eventContact);
        eventDate1.setTypeface(typeface);
        eventLocation1.setTypeface(typeface);
        eventSkills1.setTypeface(typeface);
        eventContact1.setTypeface(typeface);
        eventDate1.setTypeface(typeface);

        eventDate1.setText(eventDate);
        eventTimeStart1.setText(eventTimeStart);
        eventLocation1.setText(eventLocation);
        eventSkills1.setText(eventSkills);
        eventContact1.setText(contactPerson + "/ " + contactNo);

        return view;
    }




}
