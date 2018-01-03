package myapps.wycoco.com.ethelonstartup.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Activities.SkillsActivity;
import myapps.wycoco.com.ethelonstartup.Models.Localhost;
import myapps.wycoco.com.ethelonstartup.R;
import myapps.wycoco.com.ethelonstartup.Service.SessionManager;
import myapps.wycoco.com.ethelonstartup.Utils.SingletonClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterForm2 extends Fragment {

    ArrayList<String> roles = new ArrayList<>();
    Spinner inputRole;
    EditText inputName;
    Button doneBtn;
    ProgressBar progressBar;
    private String URL = "http://"+new Localhost().getLocalhost()+"register";
    String role;
    SessionManager sessionManager;

    public RegisterForm2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register2_form, container, false);

        roles.add("Volunteer");
        roles.add("Foundation");

        inputName = (EditText)v.findViewById(R.id.inputName);
        inputRole = (Spinner)v.findViewById(R.id.inputRole);
        doneBtn = (Button)v.findViewById(R.id.doneBtn);
        progressBar = (ProgressBar)v.findViewById(R.id.progressBar2);

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputRole.setAdapter(roleAdapter);

        sessionManager = new SessionManager(getContext());


        inputRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                role = inputRole.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                final String email = getArguments().getString("email");
                final String password = getArguments().getString("password");
                final String name = inputName.getText().toString();
                Log.e("kobe","id "+ email + password + name + role);
                Toast.makeText(getContext(), "" + name, Toast.LENGTH_SHORT).show();


                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("name", name);
                params.put("role", "Volunteer");
                params.put("fcm_token", FirebaseInstanceId.getInstance().getToken());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String message = response.getString("message");
                            if(message.equals("email already exists")){
                                Toast.makeText(getContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                            }else{
                                String api_token = response.getString("api_token");
                                String volunteer_id = response.getString("volunteer_id");
                                String name = response.getString("name");
                                sessionManager.createLoginSessionForEthelon(email, name, volunteer_id, api_token);
                                Intent intent = new Intent(getContext(), SkillsActivity.class);
                                intent.putExtra("api_token",api_token);
                                intent.putExtra("id",volunteer_id);
                                intent.putExtra("profileName",name);

                                startActivity(intent);
                                progressBar.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.toString());
                    }
                }
                );

                SingletonClass.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));
//                RequestQueue request = Volley.newRequestQueue(getContext());
//                request.add(jsonObjectRequest);
            }
        });



        return v;
    }





}
