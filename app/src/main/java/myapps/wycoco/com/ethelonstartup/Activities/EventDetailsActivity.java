package myapps.wycoco.com.ethelonstartup.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.phelat.fun.Control.FunControl;
import com.phelat.fun.Layouts.Funny;
import com.phelat.fun.Widget.FunnyButton;

import myapps.wycoco.com.ethelonstartup.R;

public class EventDetailsActivity extends AppCompatActivity {

    Funny funny;
    FunnyButton funnyButton;
    LinearLayout funnyContainer;
    FunControl funControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        funny = (Funny) findViewById(R.id.funny2);

        funnyButton = (FunnyButton) findViewById(R.id.viewDetailsBtn);

        funnyContainer = (LinearLayout) findViewById(R.id.funnyContainer);

        funControl = new FunControl.Builder()
                .setFunnyLayout(funny)
                .setFunnyButton(funnyButton)
                .setContainer(funnyContainer)
                .setAnimationDuration(400)
                .setGravityToExpand(Gravity.NO_GRAVITY)
                .build();


        funnyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!funControl.isExpanded())
                    funControl.expand();
                else if(funControl.isExpanded())
                    funControl.collapse();
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (funControl.isExpanded()){
            funControl.collapse();
        }else{
            super.onBackPressed();
        }
    }
}
