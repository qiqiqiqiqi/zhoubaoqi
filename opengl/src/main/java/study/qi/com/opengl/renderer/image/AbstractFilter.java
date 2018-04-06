package study.qi.com.opengl.renderer.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import study.qi.com.opengl.uitls.ShaderUtils;

/**
 * Created by feng on 2018/3/10.
 */

public abstract class AbstractFilter implements GLSurfaceView.Renderer {
    private static final String TAG = AbstractFilter.class.getSimpleName();
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
    private float[] projectMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] modeMatrix = new float[16];
    private float[] mvpMatrix = new float[16];
    private FloatBuffer mVectexBuffer;
    private FloatBuffer mTextureBuffer;
    protected int mProgram;
    private Bitmap mBitmap;
    private int textureId;

    public AbstractFilter(Context context, String vectexShaderCode, String fragmentColorCode) {
        this.mContext = context;
        this.vectexShaderCode = vectexShaderCode;
        this.fragmentColorCode = fragmentColorCode;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated():");
        init();
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged():");
        if (mBitmap != null) {
            float screenRatio = (float) width / height;
            float bitmapRatio = (float) mBitmap.getWidth() / mBitmap.getHeight();

            GLES20.glViewport(0, 0, width, height);

            Matrix.setLookAtM(viewMatrix, 0,
                    0.0f, 0.0f, 7.0f,
                    0.0f, 0.0f, 0.0f,
                    0.0f, 1.0f, 0.0f);
            if (screenRatio < 1) {//vertical screen
                if (screenRatio > bitmapRatio) {//图片很高,防止上下压缩
                    Matrix.orthoM(projectMatrix, 0, -1, 1, -bitmapRatio / screenRatio, bitmapRatio / screenRatio, 3.0f, 7.0f);
                } else {//图片很长,防止上下拉伸
                    Matrix.orthoM(projectMatrix, 0, -1, 1, -bitmapRatio / screenRatio, bitmapRatio / screenRatio, 3.0f, 7.0f);
                }
            } else { //horitalzion screen
                if (screenRatio > bitmapRatio) {
                    Matrix.orthoM(projectMatrix, 0, -screenRatio / bitmapRatio, screenRatio / bitmapRatio, -1, 1, 3.0f, 7.0f);
                } else {
                    Matrix.orthoM(projectMatrix, 0, -screenRatio / bitmapRatio, screenRatio / bitmapRatio, -1, 1, 3.0f, 7.0f);

                }
            }

            Matrix.multiplyMM(mvpMatrix, 0, projectMatrix, 0, viewMatrix, 0);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame():");
        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        onDrawDataChange();
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, mVectexBuffer);

        int aTexturePositionHandle = GLES20.glGetAttribLocation(mProgram, "aTexturePosition");
        textureId = createTexture();
        GLES20.glEnableVertexAttribArray(aTexturePositionHandle);
        GLES20.glVertexAttribPointer(aTexturePositionHandle, 2, GLES20.GL_FLOAT, false, 0, mTextureBuffer);

        int vMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "vMVPMatrix");
        GLES20.glUniformMatrix4fv(vMVPMatrixHandle, 1, false, mvpMatrix, 0);
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

    private int createTexture() {
        int[] texture = new int[1];
        if (mBitmap != null && !mBitmap.isRecycled()) {
            //生成纹理
            GLES20.glGenTextures(1, texture, 0);
            //生成纹理
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);
            //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            //根据以上指定的参数，生成一个2D纹理
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mBitmap, 0);
            return texture[0];
        }
        return 0;
    }

    public abstract void onDrawDataChange();
}
