package myapps.wycoco.com.ethelonstartup.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BadgeUpdateDialogFragment extends AppCompatDialogFragment {


    ImageView badgeUpdate;
    TextView updateBody;
    ProgressBar progressBar;

    public BadgeUpdateDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_badge_update_dialog ,container,false);

        badgeUpdate = (ImageView)view.findViewById(R.id.badgeImage);
        updateBody = (TextView)view.findViewById(R.id.updateBody);
//        progressBar = (ProgressBar)view.findViewById(R.id.badgeProgress);

        String badge_rank = getArguments().getString("badge_rank");
        String image_url = getArguments().getString("image_url");
        String body = getArguments().getString("body");
        int gaugeExp = getArguments().getInt("gaugeExp");

        Glide.with(getContext()).load(image_url)
                .fitCenter().crossFade().into(badgeUpdate);

//        progressBar.setProgress(gaugeExp);
        updateBody.setText("You have earned the "+ badge_rank + "!");
        //        badgeUpdate = ();
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }


}
