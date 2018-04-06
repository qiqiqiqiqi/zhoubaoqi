package study.qi.com.opengl.renderer.shape;

import android.opengl.GLES20;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/7.
 */

public class CustomRender extends Shape {
    private static final String TAG = CustomRender.class.getSimpleName();
    private Class<? extends Shape> mClass;
    private Shape mShape;

    public CustomRender(View view) {
        super(view);
        mClass = Triangle.class;
    }

    public void setShape(Class<? extends Shape> shape) {
        this.mClass = shape;

    }

    private void init() {
        try {
            Constructor<? extends Shape> declaredConstructor = mClass.getDeclaredConstructor(View.class);
            declaredConstructor.setAccessible(true);
            mShape = declaredConstructor.newInstance(mView);
        } catch (Exception e) {
            mShape = new Triangle(mView);
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated():");
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        init();
        mShape.onSurfaceCreated(gl, config);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged():");
        GLES20.glViewport(0, 0, width, height);

        mShape.onSurfaceChanged(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame()");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mShape.onDrawFrame(gl);
    }
}
