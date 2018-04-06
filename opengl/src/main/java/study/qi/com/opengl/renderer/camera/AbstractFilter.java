package study.qi.com.opengl.renderer.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import study.qi.com.opengl.uitls.Gl2Utils;
import study.qi.com.opengl.uitls.ShaderUtils;

/**
 * Created by feng on 2018/3/10.
 */

public abstract class AbstractFilter implements GLSurfaceView.Renderer {
    private static final String TAG = AbstractFilter.class.getSimpleName();
    public static final int FRONT_CAMERA = 1;
    public static final int BACK_CAMERA = 0;
    protected Context mContext;
    protected String vectexShaderCode;
    protected String fragmentColorCode;
    private float[] vectexCoords = new float[]{
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
    };
    private float[] textureCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
    };
    protected float[] projectMatrix = new float[16];
    protected float[] viewMatrix = new float[16];
    protected float[] modeMatrix = new float[16];
    protected float[] mvpMatrix = new float[16];
    private FloatBuffer mVectexBuffer;
    private FloatBuffer mTextureBuffer;
    protected int mProgram;
    private Bitmap mBitmap;
    private int textureId;
    private int dataWidth, dataHeight;
    protected int cameraID = BACK_CAMERA;

    public AbstractFilter(Context context, String vectexShaderCode, String fragmentColorCode) {
        this.mContext = context;
        this.vectexShaderCode = vectexShaderCode;
        this.fragmentColorCode = fragmentColorCode;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setDataSize(int dataWidth, int dataHeight) {
        this.dataWidth = dataWidth;
        this.dataHeight = dataHeight;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated():");
        init();
        onCreated(gl, config);
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged():width=" + width + ",height=" + height + ",dataWidth=" + dataWidth + ",dataHeight=" + dataHeight);

        float screenRatio = (float) width / height;
        float dataRatio = (float) dataWidth / dataHeight;

        GLES20.glViewport(0, 0, width, height);
        Matrix.setIdentityM(projectMatrix, 0);
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setIdentityM(modeMatrix, 0);
        onChanged(gl, width, height);
        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, 0.0f, 7.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        if (screenRatio < 1) {//vertical screen
            if (screenRatio > dataRatio) {//图片很高,防止上下压缩
                Matrix.orthoM(projectMatrix, 0, -1, 1, -dataRatio / screenRatio, dataRatio / screenRatio, 3.0f, 7.0f);
            } else {//图片很长,防止上下拉伸
                Matrix.orthoM(projectMatrix, 0, -1, 1, -dataRatio / screenRatio, dataRatio / screenRatio, 3.0f, 7.0f);
            }
        } else { //horitalzion screen
            if (screenRatio > dataRatio) {
                Matrix.orthoM(projectMatrix, 0, -screenRatio / dataRatio, screenRatio / dataRatio, -1, 1, 3.0f, 7.0f);
            } else {
                Matrix.orthoM(projectMatrix, 0, -screenRatio / dataRatio, screenRatio / dataRatio, -1, 1, 3.0f, 7.0f);

            }
        }

        // Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame():");
        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, mVectexBuffer);

        onFrame(gl);

        int aTexturePositionHandle = GLES20.glGetAttribLocation(mProgram, "aTexturePosition");
        GLES20.glEnableVertexAttribArray(aTexturePositionHandle);
        GLES20.glVertexAttribPointer(aTexturePositionHandle, 2, GLES20.GL_FLOAT, false, 0, mTextureBuffer);

        int vProjectMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vProjectMatrix");
        GLES20.glUniformMatrix4fv(vProjectMatrixHandle, 1, false, projectMatrix, 0);

        int vViewMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vViewMatrix");
        GLES20.glUniformMatrix4fv(vViewMatrixHandle, 1, false, viewMatrix, 0);

        int vModeMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vModeMatrix");
        GLES20.glUniformMatrix4fv(vModeMatrixHandle, 1, false, modeMatrix, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, vectexCoords.length / 3);
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    private void init() {
        ByteBuffer vectexCoordsBuffer = ByteBuffer.allocateDirect(vectexCoords.length * 4);
        vectexCoordsBuffer.order(ByteOrder.nativeOrder());
        mVectexBuffer = vectexCoordsBuffer.asFloatBuffer();
        mVectexBuffer.put(vectexCoords);
        mVectexBuffer.position(0);

        ByteBuffer textureCoordsBuffer = ByteBuffer.allocateDirect(textureCoords.length * 4);
        textureCoordsBuffer.order(ByteOrder.nativeOrder());
        mTextureBuffer = textureCoordsBuffer.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
        mProgram = ShaderUtils.createProgram(mContext.getResources(), vectexShaderCode, fragmentColorCode);
    }


    public abstract void onCreated(GL10 gl, EGLConfig config);

    public abstract void onChanged(GL10 gl, int width, int height);

    public abstract void onFrame(GL10 gl);

}
