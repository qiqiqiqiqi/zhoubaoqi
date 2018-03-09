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

public class Ball extends Shape {
    private static final String TAG = Ball.class.getSimpleName();
    private String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 aColor;" +
                    "uniform mat4 vMVPMatrix;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "gl_Position=vMVPMatrix * vec4(vPosition.xyz,1);" +
                    "float rColor;" +
                    "float gColor;" +
                    "float bColor;" +
                    "if(vPosition.y<0.0){" +
                    "rColor=-vPosition.y;" +
                    "if(vPosition.x<0.0){" +
                    "gColor=-vPosition.x;" +
                    "}else{" +
                    "gColor=vPosition.x;" +
                    "}" +
                    "if(vPosition.z<0.0){" +
                    "bColor=-vPosition.z;" +
                    "}else{" +
                    "bColor=vPosition.z;" +
                    "}" +
                    "}else{" +
                    "rColor=vPosition.y;" +
                    "if(vPosition.x<0.0){" +
                    "gColor=-vPosition.x;" +
                    "}else{" +
                    "gColor=vPosition.x;" +
                    "}" +
                    "if(vPosition.z<0.0){" +
                    "bColor=-vPosition.z;" +
                    "}else{" +
                    "bColor=vPosition.z;" +
                    "}" +
                    "}" +
                    "vColor=vec4(rColor,gColor,bColor,1.0);" +
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
    private float[] modeMatrix = new float[16];
    private float[] mvpMatrix = new float[16];
    private float[] mvpMatrix2 = new float[16];
    private int count = 360;
    private float radius = 1.0f;
    private List<Float> vertexs = new ArrayList<Float>();
    private List<Float> vertexColors = new ArrayList<>();
    private float[] ovalCoords;
    private float[] color = new float[]{
            1.0f, 0.0f, 0.0f, 1.0f
    };
    private FloatBuffer mVectexBuffer;
    private long initFrameDrawingTime;
    private float rotateSpeed = 1.0f / 10;

    public Ball(View view) {
        super(view);
        initData();
    }

    private void initData() {
        float lngAngle = 2;
        float latAngle = 2;
//        vertexs.add(0.0f);
//        vertexs.add(0.0f);
//        vertexs.add(0.0f);
        for (int j = -90; j < 90 + latAngle; j += latAngle) {
            float r = (float) (radius * Math.cos(j * Math.PI / 180));
            float h = (float) (radius * Math.sin(j * Math.PI / 180));

            float r2 = (float) (radius * Math.cos((j + latAngle) * Math.PI / 180));
            float h2 = (float) (radius * Math.sin((j + latAngle) * Math.PI / 180));
            float angle2 = lngAngle * 1;
            for (int i = 0; i <= 360 + lngAngle; i += angle2) {
                vertexs.add((float) (r * Math.cos(i * Math.PI / 180)));
                vertexs.add(h);
                vertexs.add(-(float) (r * Math.sin(i * Math.PI / 180)));

//                vertexs.add((float) (r2 * Math.cos(i * Math.PI / 180)));
//                vertexs.add(h2);
//                vertexs.add(-(float) (r2 * Math.sin(i * Math.PI / 180)));
            }
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
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;
        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, -3.0f, 7.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        Matrix.frustumM(projectMatrix, 0,
                -ratio, ratio, -1, 1,
                3.0f, 20.0f);
        Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 12, mVectexBuffer);
        Matrix.setIdentityM(modeMatrix, 0);
        Matrix.rotateM(modeMatrix, 0, getRotationAngle(), 0.0f, 1.0f, 0.0f);
        Matrix.setIdentityM(mvpMatrix2, 0);
        Matrix.multiplyMM(mvpMatrix2, 0, mvpMatrix, 0, modeMatrix, 0);
        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1, false, mvpMatrix2, 0);

//        int vColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
//        GLES20.glUniform4fv(vColorHandle, 1, color, 0);

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
