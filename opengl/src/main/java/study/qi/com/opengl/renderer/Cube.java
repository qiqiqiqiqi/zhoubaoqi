package study.qi.com.opengl.renderer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/8.
 */

public class Cube extends Shape {
    private static final String TAG = Cube.class.getSimpleName();
    private String vectexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 aColor;" +
                    "varying vec4 vColor;" +
                    "uniform mat4 vMVPMatrix;" +
                    "void main(){" +
                    "gl_Position=vMVPMatrix * vec4(vPosition.xyz,1);" +
                    "vColor=aColor;" +
                    "}";
    private String fragmentShaderCode =
            "precision mediump float;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "gl_FragColor=vColor;" +
                    "}";
    private static float[] cubeCoords = new float[]{
            0.5f, 0.5f, 0.5f,//前右上  0
            -0.5f, 0.5f, 0.5f,//前左上  1
            -0.5f, -0.5f, 0.5f,//前左下 2
            0.5f, -0.5f, 0.5f,//前右下  3

            0.5f, 0.5f, -0.5f,//后右上  4
            -0.5f, 0.5f, -0.5f,//后左上 5
            -0.5f, -0.5f, -0.5f,//后左下6
            0.5f, -0.5f, -0.5f,//后右下 7
    };

    private static short[] indexs = new short[]{
            0, 1, 2, 0, 2, 3,//前面
            4, 5, 6, 4, 6, 7,//后面
            1, 2, 6, 1, 6, 5,//左面
            0, 3, 7, 0, 7, 4,//右面
            0, 4, 5, 0, 5, 1,//上面
            2, 3, 7, 2, 7, 6,//下面

    };
    private float[] color = new float[]{
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,

            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
    };

    private float[] projectMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] modeMatrix = new float[16];
    private float[] mvpMatrix = new float[16];
    private float[] mvpMatrix2 = new float[16];
    private FloatBuffer mVectexBuffer;
    private ShortBuffer mVectexIndexsBuffer;
    private FloatBuffer mVectexColorBuffer;
    private long initFrameDrawingTime;
    private float rotateSpeed = 1.0f / 10;

    public Cube(View view) {
        super(view);

    }

    private void init() {
        ByteBuffer coordsBuffer = ByteBuffer.allocateDirect(cubeCoords.length * 4);
        coordsBuffer.order(ByteOrder.nativeOrder());
        mVectexBuffer = coordsBuffer.asFloatBuffer();
        mVectexBuffer.put(cubeCoords);
        mVectexBuffer.position(0);

        ByteBuffer coordsColorBuffer = ByteBuffer.allocateDirect(color.length * 4);
        coordsColorBuffer.order(ByteOrder.nativeOrder());
        mVectexColorBuffer = coordsColorBuffer.asFloatBuffer();
        mVectexColorBuffer.put(color);
        mVectexColorBuffer.position(0);

        ByteBuffer indexsBuffer = ByteBuffer.allocateDirect(indexs.length * 2);
        indexsBuffer.order(ByteOrder.nativeOrder());
        mVectexIndexsBuffer = indexsBuffer.asShortBuffer();
        mVectexIndexsBuffer.put(indexs);
        mVectexIndexsBuffer.position(0);

        createProgram(vectexShaderCode, fragmentShaderCode);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;
        Matrix.setLookAtM(viewMatrix, 0,
                3.0f, 3.0f, 3.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        Matrix.frustumM(projectMatrix, 0,
                -ratio, ratio, -1, 1,
                2, 20);
        Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, mVectexBuffer);

        Matrix.setIdentityM(modeMatrix, 0);
        Matrix.rotateM(modeMatrix, 0, getRotationAngle(), 0.0f, 1.0f, 0.0f);
        Matrix.setIdentityM(mvpMatrix2, 0);
        Matrix.multiplyMM(mvpMatrix2, 0, mvpMatrix, 0, modeMatrix, 0);

        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1, false, mvpMatrix2, 0);

        int colorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glVertexAttribPointer(colorHandle, 4, GLES20.GL_FLOAT, false, 0, mVectexColorBuffer);

        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, indexs.length, GLES20.GL_UNSIGNED_SHORT, mVectexIndexsBuffer);
        GLES20.glDisableVertexAttribArray(positionHandle);


    }

    private float getRotationAngle() {
        float angle;

        long now = System.currentTimeMillis();
        if (initFrameDrawingTime == 0L) {
            angle = 0.0f;
            initFrameDrawingTime = now;
        } else {
            long deltaTime = now - initFrameDrawingTime;
            angle = deltaTime * rotateSpeed;
        }

        return angle;
    }
}
