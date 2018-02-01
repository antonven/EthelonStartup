package myapps.wycoco.com.ethelonstartup.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import myapps.wycoco.com.ethelonstartup.Activities.HomeActivity;
import myapps.wycoco.com.ethelonstartup.Activities.PortfolioActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dell on 9/26/2017.
 */

public class ConfirmDialogFragment extends DialogFragment {

//    private MyInterface mListener;
    String email, fbProfileName,fbProfilePicture,profileId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String api_token = getArguments().getString("api_token");
        final String volunteer_id = getArguments().getString("volunteer_id");
        final String activity_id= getArguments().getString("activity_id");


        SharedPreferences shared = getActivity().getSharedPreferences("HOME_INFO", MODE_PRIVATE);
        email = shared.getString("email", "");
        fbProfileName = shared.getString("fbProfileName", "");
        fbProfilePicture = shared.getString("fbProfilePicture", "");
        profileId = shared.getString("profileId", "");

        builder.setTitle("Activity attendance")
                .setMessage("Please press confirm to complete your attendance.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //if diri dapita para ang points para mo pop up every update sa star/ badge


                        Intent n = new Intent(getActivity(), HomeActivity.class);
                        n.putExtra("api_token", api_token);
                        n.putExtra("volunteer_id", volunteer_id);
                        n.putExtra("activity_id", activity_id);
                        n.putExtra("fbProfileName", fbProfileName);
                        n.putExtra("fbProfilePicture", fbProfilePicture);
                        n.putExtra("profileId", profileId);
                        n.putExtra("profileId", email);
                        Log.e("SHITPREFERENCES", api_token+ volunteer_id + activity_id + fbProfileName +fbProfilePicture + "");

                        startActivity(n);
//                        mListener.onChoose();
//                        getActivity().finish();

                    }
                });

        return builder.create();
    }

    private void getSharePref(){



        Log.i("SHARED PREFERENCEHOME", fbProfileName + fbProfilePicture + profileId);

    }



//    public static interface MyInterface {
//        public void onChoose();
//    }



//    @Override
//    public void onAttach(Activity activity) {
//        mListener = (MyInterface) activity;
//        super.onAttach(activity);
//    }
//
//    @Override
//    public void onDetach() {
//        mListener = null;
//        super.onDetach();
//    }
}
