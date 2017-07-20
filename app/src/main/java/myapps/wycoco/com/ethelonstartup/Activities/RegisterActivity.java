package myapps.wycoco.com.ethelonstartup.Activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettoGenerator;
import com.github.jinatonic.confetti.confetto.Confetto;

import java.util.List;
import java.util.Random;

import myapps.wycoco.com.ethelonstartup.Libraries.MaterialEditText;
import myapps.wycoco.com.ethelonstartup.Libraries.MaterialEditText;

import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 7/20/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    EditText userName, passWord, conPass, eMail;
    FrameLayout frame;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);



        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.signature2Color));


        frame = (FrameLayout)findViewById(R.id.frame1);
        userName = (EditText)findViewById(R.id.inputUsername);
        passWord = (EditText)findViewById(R.id.inputPassword);
        conPass = (EditText)findViewById(R.id.inputConfirmPassword);
        eMail = (EditText)findViewById(R.id.inputEmail);

//        CommonConfetti.rainingConfetti(frame, new int[]{Color.CYAN})
//                .infinite();
//
//        ConfettoGenerator confettoGenerator = new ConfettoGenerator() {
//            @Override
//            public Confetto generateConfetto(Random random) {
//                final Bitmap bitmap = allPos
//            }
//        }
    }

}