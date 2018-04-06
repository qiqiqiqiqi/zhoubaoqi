package study.qi.com.opengl.renderer.shape;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/8.
 */

public class Oval extends Shape {
    private static final String TAG = Oval.class.getSimpleName();
    private String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 aColor;" +
                    "uniform mat4 vMVPMatrix;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "gl_Position=vMVPMatrix * vec4(vPosition.xyz,1);" +
                    "vColor=aColor;" +
                    "}";
    private String fragmentColorShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main(){" +
                    "gl_FragColor=vColor;" +
                    "}";
    private float[] projectMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] mvpMatrix = new float[16];
    private int count = 360;
    private float radius = 0.5f;
    private List<Float> vertexs = new ArrayList<Float>();
    private List<Float> vertexColors = new ArrayList<>();
    private float[] ovalCoords;
    private float[] color = new float[]{
            1.0f, 0.0f, 0.0f, 1.0f
    };
    private FloatBuffer mVectexBuffer;
    private float height;

    public Oval(View view) {
        this(view, 0);

    }

    public Oval(View view, float height) {
        super(view);
        this.height = height;
        initData();
    }

    public void setMvpMatrix(float[] mvpMatrix) {
        this.mvpMatrix = mvpMatrix;
    }

    private void initData() {
        float angle = 360 / count;
//        vertexs.add(0.0f);
//        vertexs.add(0.0f);
//        vertexs.add(0.0f);
        for (int i = 0; i <= count; i++) {
            vertexs.add((float) (radius * Math.cos(i * angle * Math.PI / 180)));
            vertexs.add((float) (radius * Math.sin(i * angle * Math.PI / 180)));
            vertexs.add(0.0f);
        }
        ovalCoords = new float[vertexs.size()];
        for (int j = 0; j < vertexs.size(); j++) {
            ovalCoords[j] = vertexs.get(j);
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        init();
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;
        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, 0.0f, 7.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        Matrix.frustumM(projectMatrix, 0,
                -ratio, ratio, -1, 1,
                3.0f, 7.0f);
        Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, mVectexBuffer);

        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1, false, mvpMatrix, 0);

        int vColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(vColorHandle, 1, color, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, ovalCoords.length / 3);
        GLES20.glDisableVertexAttribArray(positionHandle);

    }

    private void init() {
        ByteBuffer coordsByteBuffer = ByteBuffer.allocateDirect(ovalCoords.length * 4);
        coordsByteBuffer.order(ByteOrder.nativeOrder());
        mVectexBuffer = coordsByteBuffer.asFloatBuffer();
        mVectexBuffer.put(ovalCoords);
        mVectexBuffer.position(0);
        createProgram(vertexShaderCode, fragmentColorShaderCode);
    }
}
