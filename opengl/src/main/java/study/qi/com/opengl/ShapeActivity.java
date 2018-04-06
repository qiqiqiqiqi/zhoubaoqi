package study.qi.com.opengl;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import study.qi.com.opengl.renderer.shape.CustomRender;
import study.qi.com.opengl.renderer.shape.Shape;


public class ShapeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ShapeActivity.class.getSimpleName();
    private static final int REQUESTCODE_CHOOSE = 1001;
    private GLSurfaceView mGlSurfaceView;
    private Button mButton;
    private CustomRender mCustomRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        mButton = (Button) findViewById(R.id.select);
        mButton.setOnClickListener(this);
        mGlSurfaceView = (GLSurfaceView) findViewById(R.id.glsurfaceview);
        mGlSurfaceView.setEGLContextClientVersion(2);
        mCustomRender = new CustomRender(mGlSurfaceView);
        mGlSurfaceView.setRenderer(mCustomRender);
        mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGlSurfaceView != null) {
            mGlSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGlSurfaceView != null) {
            mGlSurfaceView.onPause();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChooseActivity.class);
        startActivityForResult(intent, REQUESTCODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUESTCODE_CHOOSE) {
                Class<? extends Shape> shape = (Class<? extends Shape>) data.getSerializableExtra("name");
                mCustomRender.setShape(shape);
            }
        }
    }
}
