package myapps.wycoco.com.ethelonstartup.Fragments;


import android.content.Context;
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

    TextView eventDate1, eventTimeStart1, eventLocation1;
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
        String eventHost = getArguments().getString("eventHost");
        String eventDate = getArguments().getString("eventDate");
        String eventTimeStart = getArguments().getString("eventTimeStart");
        String eventLocation = getArguments().getString("eventLocation");


        eventDate1 = (TextView)view.findViewById(R.id.eventDate);
        eventTimeStart1 = (TextView)view.findViewById(R.id.eventTimeStart);
        eventLocation1 = (TextView)view.findViewById(R.id.eventLocation);
//        joinActvitiyBtn = (Button)view.findViewById(R.id.joinActivityBtn);
//        SparkButton heartButton = new SparkButton(mContext);




        return view;
    }




}
