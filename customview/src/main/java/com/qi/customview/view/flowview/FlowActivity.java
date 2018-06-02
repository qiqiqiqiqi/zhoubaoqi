package com.qi.customview.view.flowview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qi.customview.R;

public class FlowActivity extends AppCompatActivity implements View.OnClickListener {

    private FlowView mFlowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        mFlowView = (FlowView) findViewById(R.id.flowview);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mFlowView.startAnimation();

    }
}
