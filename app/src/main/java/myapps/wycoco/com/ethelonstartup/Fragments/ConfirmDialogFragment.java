package myapps.wycoco.com.ethelonstartup.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import myapps.wycoco.com.ethelonstartup.Activities.HomeActivity;

/**
 * Created by dell on 9/26/2017.
 */

public class ConfirmDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Activity attendance")
                .setMessage("Please press confirm to complete your attendance.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent n = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                        startActivity(n);
                        getActivity().finish();
                    }
                });

        return builder.create();
    }
}
