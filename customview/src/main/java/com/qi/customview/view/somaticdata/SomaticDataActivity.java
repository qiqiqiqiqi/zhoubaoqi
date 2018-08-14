package com.qi.customview.view.somaticdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.qi.customview.R;

public class SomaticDataActivity extends AppCompatActivity {

    private SomaticDataSurfaceView mSomaticDataSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somatic_data);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        mSomaticDataSurfaceView = (SomaticDataSurfaceView) findViewById(R.id.surfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSomaticDataSurfaceView.onVisiable(true);
    }
}
