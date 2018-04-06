package study.qi.com.opengl.camare;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import study.qi.com.opengl.renderer.camera.AbstractFilter;
import study.qi.com.opengl.renderer.camera.CameraPreviewFilter;

/**
 * Created by feng on 2018/3/13.
 */

public class CameraGLSurfaceView extends GLSurfaceView {

    private CameraPreviewFilter mCameraPreviewFilter;

    public CameraGLSurfaceView(Context context) {
        this(context, null);
    }

    public CameraGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setEGLContextClientVersion(2);
        mCameraPreviewFilter = new CameraPreviewFilter(context, this);
        setRenderer(mCameraPreviewFilter);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }

    @Override
    public void onResume() {
        super.onResume();
//        mCameraPreviewFilter.openCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCameraPreviewFilter.closeCamera();
    }

    public void switchCamera() {
        boolean switchCamera = mCameraPreviewFilter.switchCamera();
        if (switchCamera) {
            onPause();
            onResume();
        }
    }
}
