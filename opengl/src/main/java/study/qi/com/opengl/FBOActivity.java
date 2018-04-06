package study.qi.com.opengl;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import study.qi.com.opengl.renderer.fbo.FBOGLSurfaceView;
import study.qi.com.opengl.renderer.fbo.FBORenderer;

public class FBOActivity extends AppCompatActivity implements FBOGLSurfaceView.FrameCallBack {

    private FBOGLSurfaceView mGlSurfaceView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbo);
        initView();
    }

    private void initView() {
        mGlSurfaceView = findViewById(R.id.glsurfaceview);
        mGlSurfaceView.setOnFrameCallBack(this);
        mImageView = findViewById(R.id.image);

    }

    @Override
    public void onFrameCallBack(final Bitmap bitmap) {

        mGlSurfaceView.post(new Runnable() {
            @Override
            public void run() {
                mImageView.setImageBitmap(bitmap);
            }
        });
    }
}
