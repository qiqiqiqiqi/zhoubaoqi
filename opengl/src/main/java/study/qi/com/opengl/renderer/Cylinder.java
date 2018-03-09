package study.qi.com.opengl.renderer;

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

public class Cylinder extends Shape {
    private static final String TAG = Cylinder.class.getSimpleName();
    private String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 aColor;" +
                    "uniform mat4 vMVPMatrix;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "gl_Position=vMVPMatrix * vec4(vPosition.xyz,1);" +
                    "if(vPosition.z!=0.0){" +
                    "    vColor=vec4(0.0,1.0,0.0,1.0);" +
                    "}else{" +
                    "    vColor=vec4(0.0,0.0,1.0,1.0);" +
                    "}" +
                    "}";
    private String fragmentColorShaderCode =
            "precision mediump float;" +
                   /* "uniform vec4 vColor;" +*/
                    "varying vec4 vColor;" +
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
    private final Oval mBottomOval;
    private final Oval mTopOval;

    public Cylinder(View view) {
        super(view);
        mBottomOval = new Oval(view);
        mTopOval = new Oval(view, 2);
        initData();
    }

    private void initData() {
        float angle = 360 / count;
        for (int i = 0; i <= count; i++) {
            vertexs.add((float) (radius * Math.cos(i * angle * Math.PI / 180)));
            vertexs.add((float) (radius * Math.sin(i * angle * Math.PI / 180)));
            vertexs.add(0.0f);
            vertexs.add((float) (radius * Math.cos(i * angle * Math.PI / 180)));
            vertexs.add((float) (radius * Math.sin(i * angle * Math.PI / 180)));
            vertexs.add(2.0f);
        }
        ovalCoords = new float[vertexs.size()];
        for (int j = 0; j < vertexs.size(); j++) {
            ovalCoords[j] = vertexs.get(j);
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        init();
        mBottomOval.onSurfaceCreated(gl, config);
        mTopOval.onSurfaceCreated(gl, config);
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;
        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, 7.0f, -2.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        Matrix.frustumM(projectMatrix, 0,
                -ratio, ratio, -1, 1,
                3.0f, 20.0f);
        Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);
//        mBottomOval.onSurfaceChanged(gl, width, height);
//        mTopOval.onSurfaceChanged(gl, width, height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, mVectexBuffer);

        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1, false, mvpMatrix, 0);

//        int vColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
//        GLES20.glUniform4fv(vColorHandle, 1, color, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, ovalCoords.length / 3);
        GLES20.glDisableVertexAttribArray(positionHandle);
        mBottomOval.onDrawFrame(gl);
        mBottomOval.setMvpMatrix(mvpMatrix);
        mTopOval.onDrawFrame(gl);
        mTopOval.setMvpMatrix(mvpMatrix);
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
