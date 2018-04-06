package study.qi.com.opengl.renderer.image;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/10.
 */

public class ImageRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = ImageRenderer.class.getSimpleName();
    private AbstractFilter mFilter;
    private boolean refresh;
    private EGLConfig mEGLConfig;
    private int width, height;

    public AbstractFilter getFilter() {
        return mFilter;
    }

    public void setFilter(AbstractFilter filter) {
        mFilter = filter;
        refresh = true;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated():");
        mEGLConfig = config;
        if (mFilter != null) {
            mFilter.onSurfaceCreated(gl, config);
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged()");
        this.width = width;
        this.height = height;
        if (mFilter != null) {
            mFilter.onSurfaceChanged(gl, width, height);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame()");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        if (mFilter != null) {
            if (refresh && width != 0 && height != 0) {
                mFilter.onSurfaceCreated(gl, mEGLConfig);
                mFilter.onSurfaceChanged(gl, width, height);
                refresh = false;
            }
            mFilter.onDrawFrame(gl);
        }
    }
}
