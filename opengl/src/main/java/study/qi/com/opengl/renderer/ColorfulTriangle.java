package study.qi.com.opengl.renderer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/7.
 */

public class ColorfulTriangle extends Shape {
    private static final String TAG = ColorfulTriangle.class.getSimpleName();
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 aColor;" +
                    "uniform mat4 vMVPMatrix;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "     gl_Position=vMVPMatrix * vec4(vPosition.xyz, 1);" +
                    "     vColor=aColor;" +
                    "}";
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "    gl_FragColor=vColor;" +
                    "}";
    private static final float[] triangleCoords = {
            0.5f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f
    };
    private final float[] color = {
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 1.0f
    };
    private float[] mMVPMatrix = new float[16];
    private float[] mProjectMatrix = new float[16];
    private float[] mViewMarix = new float[16];
    private FloatBuffer mVertexBuffer;
    private int mPositionHandle;
    private FloatBuffer mVertexColorBuffer;

    public ColorfulTriangle(View view) {
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
        //计算宽高比
        Matrix.setIdentityM(mMVPMatrix, 0);
        float ratio = (float) width / height;
        Matrix.setLookAtM(mViewMarix, 0,
                0.0f, 0.0f, 2.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        //默认的眼睛的位置在OpenGL坐标的原点处(0,0,0)。视线方向是平行于Z轴往里看。
        //near表示的是眼睛到作图平面的距离(绝对值哦！)，far表示眼睛到最远可见处的平面范围。
        //于是，默认情况下z的作图范围就是在-near到-far的位置。--->()
        Matrix.frustumM(mProjectMatrix, 0,
                -ratio, ratio, -1, 1,
                2.0f, 11.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mViewMarix, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame():");
        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT,
                false, 3 * 4, mVertexBuffer);

        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1,
                false, mMVPMatrix, 0);

        int colorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glVertexAttribPointer(colorHandle, 4, GLES20.GL_FLOAT, false,
                0, mVertexColorBuffer);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, triangleCoords.length / 3);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    private void init() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuffer.asFloatBuffer();
        mVertexBuffer.put(triangleCoords);
        mVertexBuffer.position(0);

        ByteBuffer colorByteBuffer = ByteBuffer.allocateDirect(color.length * 4);
        colorByteBuffer.order(ByteOrder.nativeOrder());
        mVertexColorBuffer = colorByteBuffer.asFloatBuffer();
        mVertexColorBuffer.put(color);
        mVertexColorBuffer.position(0);

        createProgram(vertexShaderCode, fragmentShaderCode);
    }
}
