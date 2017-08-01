package myapps.wycoco.com.ethelonstartup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterForm2 extends Fragment {


    public RegisterForm2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register2_form, container, false);

        
        return v;
    }

}
