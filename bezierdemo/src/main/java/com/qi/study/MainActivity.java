package com.qi.study;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qi.study.bezierview.SplitView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SplitView splitView = (SplitView) findViewById(R.id.splitview);
        findViewById(R.id.dongdong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   splitView.setRightVrticalLineX( 1.5f);
                splitView.startSplitAnimator();
            }
        });

    }


}
