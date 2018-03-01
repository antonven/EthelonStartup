package myapps.wycoco.com.ethelonstartup.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import myapps.wycoco.com.ethelonstartup.Activities.HomeActivity;
import myapps.wycoco.com.ethelonstartup.Activities.ProfileActivity;
import myapps.wycoco.com.ethelonstartup.Models.BadgeUpdateModel;
import myapps.wycoco.com.ethelonstartup.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PointsUpdateFragment extends AppCompatDialogFragment implements View.OnClickListener {


    ImageView badgeUpdate;
    TextView updateBody;
    ProgressBar progressBar;
    String email, fbProfileName,fbProfilePicture,profileId, update, body, image_url,badge_rank, badge_name;
    int count, size, points;
    Button confirmBtn;
    ArrayList<BadgeUpdateModel> results;

    public PointsUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_points_update_dialog ,container,false);


        confirmBtn = (Button)view.findViewById(R.id.confirmBtn);
        badgeUpdate = (ImageView)view.findViewById(R.id.badgeImage);
        updateBody = (TextView)view.findViewById(R.id.updateBody);

//        progressBar = (ProgressBar)view.findViewById(R.id.badgeProgress);


        try{
            badge_rank = getArguments().getString("badge_rank");
            Log.e("fuck","badge_rank"+badge_rank);

            image_url = getArguments().getString("image_url");
            body = getArguments().getString("body");
            update = getArguments().getString("update");
            badge_name = getArguments().getString("badge_name");
            points = getArguments().getInt("points");
            Log.e("fuckzz",badge_name);
            count = getArguments().getInt("count");
            size = getArguments().getInt("size");
        }catch (Exception e){

        }


        return view;
}


    private void starNextActivity() {
        final String api_token = getArguments().getString("api_token");
        final String volunteer_id =  getArguments().getString("volunteer_id");
        final String activity_id=  getArguments().getString("activity_id");


        SharedPreferences shared = getContext().getSharedPreferences("HOME_INFO", MODE_PRIVATE);
        email = shared.getString("email", "");
        fbProfileName = shared.getString("fbProfileName", "");
        fbProfilePicture = shared.getString("fbProfilePicture", "");
        profileId = shared.getString("profileId", "");

        Intent n = new Intent(getContext(), ProfileActivity.class);
        n.putExtra("api_token", api_token);
        n.putExtra("volunteer_id", volunteer_id);
        n.putExtra("activity_id", activity_id);
        n.putExtra("fbProfileName", fbProfileName);
        n.putExtra("fbProfilePicture", fbProfilePicture);
        n.putExtra("profileId", profileId);
        n.putExtra("profileId", email);
        n.putExtra("message", "true");
        Log.e("SHITPREFERENCES", api_token+ volunteer_id + activity_id + fbProfileName +fbProfilePicture + "");

        startActivity(n);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }


    @Override
    public void onClick(View view) {

    }
}
