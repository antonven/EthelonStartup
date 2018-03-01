package myapps.wycoco.com.ethelonstartup.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by dell on 8/26/2017.
 */

public class AttendanceScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "qr";
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
        String activity_id = getIntent().getStringExtra("activity_id");

        if(result.getText().equals(activity_id)) {
            sendTimeIn();

        }else {
            Toast.makeText(this, "Invalid Qr Code. Please scan again!" + result, Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    public void sendTimeIn(){

        final String api_token = getIntent().getStringExtra("api_token");
        final String activity_id = getIntent().getStringExtra("activity_id");
        final String volunteer_id = getIntent().getStringExtra("volunteer_id");

            Map<String, String> params = new HashMap<String, String>();
            params.put("volunteer_id",volunteer_id);
            params.put("api_token",api_token);
            params.put("activity_id", activity_id);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("update").equals("timein")) {
                                Intent n = new Intent(getApplicationContext(), JoinActivitySuccess.class);
                                n.putExtra("api_token", api_token);
                                n.putExtra("activity_id", activity_id);
                                n.putExtra("volunteer_id", volunteer_id);
                                n.putExtra("message", "time_in");
                                startActivity(n);
                            } else if (response.getString("update").equals("timeout")) {

                                Intent n = new Intent(getApplicationContext(), EvaluateGroupActivity.class);
                                n.putExtra("api_token", api_token);
                                n.putExtra("activity_id", activity_id);
                                n.putExtra("volunteer_id", volunteer_id);

                                startActivity(n);
                            }
                        }catch (JSONException e){
                            Log.e("EE", e.toString());

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.e("kobe",error.toString());
                    }
                }){


        };




        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }


}

