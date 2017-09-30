package myapps.wycoco.com.ethelonstartup.Activities;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

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

        if(result.getText().equals(activity_id)) {

            Intent n = new Intent(this, EvaluateGroupActivity.class);
            n.putExtra("api_token", api_token);
            n.putExtra("activity_id", activity_id);
            n.putExtra("volunteer_id", volunteer_id);

            startActivity(n);

        }else
            Toast.makeText(this, "Invalid Qr Code. Please scan again!" + result, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

}
