package myapps.wycoco.com.ethelonstartup.Fragments;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import myapps.wycoco.com.ethelonstartup.Activities.EventDetailsActivity;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/9/2017.
 */

public class DialogFragmentJoinAct extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Welcome Anton!")
                .setMessage("Are you sure you want to join this activity?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DialogFragmentJoinAct.this.dismiss();
                    }
                })
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

//                        Snackbar.make(getView().findViewById(R.id.funny) , "Event successfully added to portfolio!", Snackbar.LENGTH_SHORT).show();

                    }
                });


        return builder.create();
    }


}
