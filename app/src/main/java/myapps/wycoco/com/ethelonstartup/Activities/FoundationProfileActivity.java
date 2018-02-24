package myapps.wycoco.com.ethelonstartup.Activities;


import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.provider.CallLog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Fragments.MapsFragment;
import myapps.wycoco.com.ethelonstartup.Models.FoundationModel;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FoundationProfileActivity extends AppCompatActivity {

    private static final String URL = "http://" + new Localhost().getLocalhost() + "foundation";
    TextView foundationName, foundationEmail, foundationLocation;
    ImageView foundationImage;
    ArrayList<FoundationModel> fm = new ArrayList<>();
     String foundation_name, foundation_email, foundation_location, foundation_image, foundation_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_profile);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#8b0000"));
        }

        init();
        fetchFoundationInfo();





    }

    private void init(){
        foundationName = (TextView)findViewById(R.id.foundationName);
        foundationEmail = (TextView)findViewById(R.id.foundationEmail);
        foundationLocation = (TextView)findViewById(R.id.foundationLocation);
        foundationImage = (ImageView) findViewById(R.id.coverPhoto);


    }


    private void fetchFoundationInfo() {

        String foundation_id = getIntent().getStringExtra("foundation_id");

        Map<String, String> params = new HashMap<String, String>();
        params.put("foundation_id", foundation_id);
        Log.e("foundation_id", foundation_id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("NISUD_SA_DIRI", response.toString());


                            try {

                                Log.e("Foundation_details", response.toString());
                                String foundation_img = response.getString("image_url");
                                String foundation_name = response.getString("name");
                                String foundation_about = response.getString("description");
                                String foundation_email = response.getString("email");
                                String foundation_contact = response.getString("contactNumber");
                                String foundation_location = response.getString("location");

                                FoundationModel foundationModel = new FoundationModel(getIntent().getStringExtra("foundation_name"),
                                        foundation_img, foundation_about, foundation_location, foundation_email, foundation_contact);

                                fm.add(foundationModel);
                                Log.e("NISUD_FM", fm.size() + " asd");
                                foundationName.setText(foundationModel.getFoundation_name());
                                foundationEmail.setText(foundationModel.getFoundation_email());
                                Glide.with(getApplicationContext()).load(foundationModel.getFoundation_image())
                                        .fitCenter().crossFade().into(foundationImage);
                                foundationLocation.setText(foundationModel.getFoundation_location());

                                MapsFragment mp = new MapsFragment();
                                Bundle b = new Bundle();
                                b.putString("foundation_location", foundationModel.getFoundation_location());
                                mp.setArguments(b);
                                FragmentManager fm = getSupportFragmentManager();
                                fm.beginTransaction().replace(R.id.mapFrame, mp).commit();


                            } catch (JSONException e) {
                                Log.e("NISUD_2nd_ERROR", e.toString());

                            }
                        }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("NISUD_SA_ERROR", error.toString());

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());
        request.add(jsonObjectRequest);
    }
}
