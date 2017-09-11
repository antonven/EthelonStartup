package myapps.wycoco.com.ethelonstartup.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import myapps.wycoco.com.ethelonstartup.Fragments.DialogFragmentAttendanceSuccess;
import myapps.wycoco.com.ethelonstartup.Fragments.EvaluateFragment;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/26/2017.
 */

public class AttendanceScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {

        String api_token = getIntent().getStringExtra("api_token");
        String activity_id = getIntent().getStringExtra("activity_id");
        String volunteer_id = getIntent().getStringExtra("volunteer_id");
        Log.e("Attendancesucces", "act_id" + activity_id);

        if(result.getText().equals(activity_id)) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            DialogFragmentAttendanceSuccess fd = new DialogFragmentAttendanceSuccess();
            Intent n = new Intent(this, EvaluateGroupActivity.class);
            n.putExtra("api_token", api_token);
            n.putExtra("activity_id", activity_id);
            n.putExtra("volunteer_id", volunteer_id);
            Toast.makeText(this, "" + activity_id + result.toString(), Toast.LENGTH_SHORT).show();

            startActivity(n);
//            fd.setArguments(n);
//            fd.show(fragmentManager, "Success");
        }else
            Toast.makeText(this, "Invalid Qr Code. Please scan again!" + result, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

}
