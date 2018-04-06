package study.qi.com.opengl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import study.qi.com.opengl.camare.CameraGLSurfaceView;

public class CameraActivity extends AppCompatActivity {

    private CameraGLSurfaceView mCameraGLSurfaceView;
    private Button mSwitchCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camate);
        initView();
    }

    private void initView() {
        mCameraGLSurfaceView = findViewById(R.id.camare_preview);
        mSwitchCamera = findViewById(R.id.swicthcamera);
        mSwitchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraGLSurfaceView.switchCamera();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraGLSurfaceView.onPause();
    }
}
