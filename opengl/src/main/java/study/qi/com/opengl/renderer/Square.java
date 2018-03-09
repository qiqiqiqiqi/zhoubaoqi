package study.qi.com.opengl.renderer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by feng on 2018/3/7.
 */

public class Square extends Shape {
    private static final String TAG = Square.class.getSimpleName();
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 aColor;" +
                    "varying vec4 vColor;" +
                    "uniform mat4 vMVPMatrix;" +
                    "void main(){" +
                    "     gl_Position=vMVPMatrix * vec4(vPosition.xyz,1);" +
                    "     vColor=aColor;" +
                    "}";
    private final String fragmentColorShaderCode =
            "precision mediump float;" +
                    "varying vec4 vColor;" +
                    "void main(){" +
                    "gl_FragColor=vColor;" +
                    "}";


    private final static float[] squareCoords = {
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f
    };
    private final short[] indexs = {
            0, 1, 2,
            0, 2, 3,
    };
    private final float[] color = {
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
    };
    private float[] projectMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] modeMatrix = new float[16];
    private float[] mvpMatrix = new float[16];
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mVertexColorBuffer;
    private ShortBuffer mVertexIndexsBuffer;

    public Square(View view) {
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
        float ratio = (float) width / height;
        Matrix.setIdentityM(mvpMatrix, 0);
        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, 0.0f, 3.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        Matrix.frustumM(projectMatrix, 0,
                -ratio, ratio, -1, 1,
                3.0f, 10.1f);
        Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame():");
        GLES20.glUseProgram(mProgram);
        int postionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(postionHandle);
        GLES20.glVertexAttribPointer(postionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, mVertexBuffer);

        int colorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glVertexAttribPointer(colorHandle, 4, GLES20.GL_FLOAT, false, 0, mVertexColorBuffer);

        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1, false, mvpMatrix, 0);
        /**
         * int GL_POINTS       //将传入的顶点坐标作为单独的点绘制
         int GL_LINES        //将传入的坐标作为单独线条绘制，ABCDEFG六个顶点，绘制AB、CD、EF三条线
         int GL_LINE_STRIP   //将传入的顶点作为折线绘制，ABCD四个顶点，绘制AB、BC、CD三条线
         int GL_LINE_LOOP    //将传入的顶点作为闭合折线绘制，ABCD四个顶点，绘制AB、BC、CD、DA四条线。
         int GL_TRIANGLES    //将传入的顶点作为单独的三角形绘制，ABCDEF绘制ABC,DEF两个三角形
         int GL_TRIANGLE_FAN    //将传入的顶点作为扇面绘制，ABCDEF绘制ABC、ACD、ADE、AEF四个三角形
         int GL_TRIANGLE_STRIP   //将传入的顶点作为三角条带绘制，ABCDEF绘制ABC,BCD,CDE,DEF四个三角形
         */
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, indexs.length, GLES20.GL_UNSIGNED_SHORT, mVertexIndexsBuffer);
        //   GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,squareCoords.length/3);
        GLES20.glDisableVertexAttribArray(postionHandle);
    }

    private void init() {
        ByteBuffer coordsByteBuffer = ByteBuffer.allocateDirect(squareCoords.length * 4);
        coordsByteBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = coordsByteBuffer.asFloatBuffer();
        mVertexBuffer.put(squareCoords);
        mVertexBuffer.position(0);

        ByteBuffer colorByteBuffer = ByteBuffer.allocateDirect(color.length * 4);
        colorByteBuffer.order(ByteOrder.nativeOrder());
        mVertexColorBuffer = colorByteBuffer.asFloatBuffer();
        mVertexColorBuffer.put(color);
        mVertexColorBuffer.position(0);


        ByteBuffer indexsByteBuffer = ByteBuffer.allocateDirect(indexs.length * 2);
        indexsByteBuffer.order(ByteOrder.nativeOrder());
        mVertexIndexsBuffer = indexsByteBuffer.asShortBuffer();
        mVertexIndexsBuffer.put(indexs);
        mVertexIndexsBuffer.position(0);

        createProgram(vertexShaderCode, fragmentColorShaderCode);
    }
}
