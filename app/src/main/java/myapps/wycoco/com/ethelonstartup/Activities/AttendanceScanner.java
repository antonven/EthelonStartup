package myapps.wycoco.com.ethelonstartup.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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
//       String result1 = result.getText();
//        Toast.makeText(this, "" + result1 + result.toString(), Toast.LENGTH_SHORT).show();
        Intent n = new Intent(this, SuccessActivity.class);
        startActivity(n);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
}
