package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myapps.wycoco.com.ethelonstartup.Libraries.VolleySingleton;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterForm2 extends Fragment {

    ArrayList<String> roles = new ArrayList<>();
    Spinner inputRole;
    EditText inputName;
    Button doneBtn;
    private String URL = "http://172.17.1.127/EthelonStartupWeb/public/api/loginwithfb";
    String role;

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

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputRole.setAdapter(roleAdapter);

        final String email = getArguments().getString("email");
        final String password = getArguments().getString("password");

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
                final String name = inputName.getText().toString();
                Log.e("kobe","id "+ email + password + name + role);
                Toast.makeText(getContext(), "" + name, Toast.LENGTH_SHORT).show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("password", password);
                        params.put("name", name);
                        params.put("role", role);
                        Log.e("kobe","id "+email + password + name + role);
                        Toast.makeText(getContext(), "NI PISLIT!" , Toast.LENGTH_SHORT).show();
                        return params;
                    }
                };
//                VolleySingleton.getInstance().addToRequestQueue(stringRequest);
            }
        });



        return v;
    }



}
