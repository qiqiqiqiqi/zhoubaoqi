package study.qi.com.opengl.renderer;

import android.opengl.GLES20;
import android.util.Log;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/5.
 */

public class Triangle extends Shape {
    private static final String TAG = Triangle.class.getSimpleName();
    private final String vertexShaderCode =
            "attribute vec4 vPosition;\n" +
                    "void main(){\n" +
                    "  gl_Position = vec4(vPosition.xyz, 1);\n" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;\n" +
                    "uniform vec4 vColor;\n" +
                    "void main(){\n" +
                    "  gl_FragColor = vColor;\n" +
                    "}";

    public static float[] triangleCoords = {
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    private FloatBuffer mVertexBuffer;
    private int mPositionHandler;
    private int mFragmentColorHandler;

    private float color[] = {1.0f, 1.0f, 1.0f, 1.0f};

    public Triangle(View view) {
        super(view);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated():");
        init();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged():");

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame():");

        GLES20.glUseProgram(mProgram);
        mPositionHandler = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandler);
        GLES20.glVertexAttribPointer(mPositionHandler, 3, GLES20.GL_FLOAT,
                false, 3 * 4, mVertexBuffer);

        mFragmentColorHandler = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mFragmentColorHandler, 1, color, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, triangleCoords.length / 3);
        GLES20.glDisableVertexAttribArray(mPositionHandler);
    }

    public void init() {
        ByteBuffer floatBuffer = ByteBuffer.allocateDirect(
                triangleCoords.length * 4);
        floatBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = floatBuffer.asFloatBuffer();
        mVertexBuffer.put(triangleCoords);
        mVertexBuffer.position(0);
        createProgram(vertexShaderCode, fragmentShaderCode);
    }

}
