package myapps.wycoco.com.ethelonstartup.Fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterForm1 extends Fragment {


    public RegisterForm1() {
        // Required empty public constructor
    }
    EditText passWord, eMail;
    Button nextStepBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register1_form, container, false);

        passWord = (EditText)v.findViewById(R.id.inputPassword);
        eMail = (EditText)v.findViewById(R.id.inputEmail);
        nextStepBtn = (Button)v.findViewById(R.id.nextStepBtn);


        passWord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int DRAWABLE_RIGHT = 2;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= (passWord.getRight()-passWord.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){

                        passWord.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.visible_on), null);
                        passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
                return false;
            }
        });

        nextStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                Fragment reg2 = new RegisterForm2();

                String email = eMail.getText().toString();
                String password = passWord.getText().toString();

                Bundle n = new Bundle();
                n.putString("email", email);
                n.putString("password", password);
                reg2.setArguments(n);

                if(email.equals("") || password.equals("")){
                    Toast.makeText(getContext(), "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
                }else {
                    fm.beginTransaction()
                            .setCustomAnimations(R.anim.slide_right_animation, R.anim.slide_out_left_animation)
                            .replace(R.id.frameRegisterForm, reg2)
                            .addToBackStack("reg2")
                            .commit();
                }

            }
        });
        return v;
    }

    public static RegisterForm1 newInstance() {
        
        Bundle args = new Bundle();
        RegisterForm1 fragment = new RegisterForm1();
        fragment.setArguments(args);
        return fragment;
    }

}
