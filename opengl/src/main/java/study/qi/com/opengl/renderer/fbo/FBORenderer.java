package study.qi.com.opengl.renderer.fbo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import android.widget.Toast;

import org.obj2openjl.v3.Obj2OpenJL;
import org.obj2openjl.v3.model.OpenGLModelData;
import org.obj2openjl.v3.model.RawOpenGLModel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import study.qi.com.opengl.R;
import study.qi.com.opengl.uitls.ShaderUtils;

import static android.opengl.GLES20.GL_FRAMEBUFFER;
import static android.opengl.GLES20.GL_FRAMEBUFFER_COMPLETE;
import static android.opengl.GLES20.GL_RENDERBUFFER;
import static android.opengl.GLES20.glCheckFramebufferStatus;

/**
 * Created by feng on 2018/3/10.
 */

public class FBORenderer implements GLSurfaceView.Renderer {
    private static final String TAG = FBORenderer.class.getSimpleName();
    private Context mContext;
    private String vectexShaderCode, windowVectexShaderCode;
    private String fragmentColorCode, windowFragmentColorCode;
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
    private FloatBuffer mWindowVectexBuffer;
    private FloatBuffer mWindowTextureBuffer;
    protected int mProgram;
    private int mWindowProgram;
    private FloatBuffer mVectexbuffer;
    private FloatBuffer mTextureBuffer;
    private FloatBuffer mNormalsBuffer;
    private int mVectexCount;
    private int mWindowVectexCount;
    private int mLocalTextureID;
    private int width, heigth;
    private int[] mFrameBuffers;
    private int[] mRenderBuffers;
    private int[] mTextures;
    private int mTextureID;
    public float xAngle, yAngle;
    private ByteBuffer mShotBuffer;
    private OnShotScreenListener mOnShotScreenListener;

    public FBORenderer(Context context, String vectexShaderCode, String fragmentColorCode, String windowVectexShaderCode, String windowFragmentColorCode) {
        this.mContext = context;
        this.vectexShaderCode = vectexShaderCode;
        this.fragmentColorCode = fragmentColorCode;
        this.windowFragmentColorCode = windowFragmentColorCode;
        this.windowVectexShaderCode = windowVectexShaderCode;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated():");
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        init();
        createTexture(R.mipmap.texture);
    }

    private void createTexture(int res) {
        int[] texture = new int[1];

        //生成纹理
        GLES20.glGenTextures(1, texture, 0);
        mLocalTextureID = texture[0];
        //生成纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mLocalTextureID);
        //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_MIRRORED_REPEAT);
        //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_MIRRORED_REPEAT);
        //根据以上指定的参数，生成一个2D纹理
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), res);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged():width=" + width + ",height=" + height);
        this.width = width;
        this.heigth = height;
        float screenRatio = (float) width / height;
        mShotBuffer = ByteBuffer.allocate(width * height * 4);
        GLES20.glViewport(0, 0, width, height);
        Matrix.setIdentityM(projectMatrix, 0);
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setIdentityM(modeMatrix, 0);
        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, 0.0f, 3.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
//        if (screenRatio < 1) {//vertical screen
//            Matrix.orthoM(projectMatrix, 0, -1, 1, -screenRatio, screenRatio, 3.0f, 7.0f);
//        } else { //horitalzion screen
//            Matrix.orthoM(projectMatrix, 0, -screenRatio, screenRatio, -1, 1, 3.0f, 7.0f);
//
//        }
        Matrix.perspectiveM(projectMatrix, 0, 45, (float) width / height, 2, 5);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "onDrawFrame():");
        Matrix.rotateM(modeMatrix, 0, yAngle, 1, 0, 0);
        Matrix.rotateM(modeMatrix, 0, xAngle, 0, 1, 0);
        renderToFrameBuffer();
        renderToWindow();

        GLES20.glReadPixels(0, 0, width, heigth, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, mShotBuffer);
        if (mOnShotScreenListener != null) {

            mOnShotScreenListener.onShotScreenListener(width, heigth, mShotBuffer);
        }
        // clearup
        GLES20.glDeleteTextures(1, mTextures, 0);
        GLES20.glDeleteFramebuffers(1, mFrameBuffers, 0);
        GLES20.glDeleteRenderbuffers(1, mRenderBuffers, 0);
    }

    private void renderToWindow() {
        GLES20.glBindFramebuffer(GL_FRAMEBUFFER, 0);//0用来表示窗口系统生成的帧缓冲区
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(mWindowProgram);
        int aPosition = GLES20.glGetAttribLocation(mWindowProgram, "aPosition");
        GLES20.glEnableVertexAttribArray(aPosition);
        GLES20.glVertexAttribPointer(aPosition, 3, GLES20.GL_FLOAT, false, 3 * 4, mWindowVectexBuffer);

        int aTextureCoord = GLES20.glGetAttribLocation(mWindowProgram, "aTextureCoord");
        GLES20.glEnableVertexAttribArray(aTextureCoord);
        GLES20.glVertexAttribPointer(aTextureCoord, 2, GLES20.GL_FLOAT, false, 2 * 4, mWindowTextureBuffer);

        int uTexture = GLES20.glGetUniformLocation(mWindowProgram, "uTexture");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureID);
        GLES20.glUniform1i(uTexture, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, mWindowVectexCount);
        // GLES20.glDisableVertexAttribArray(aPosition);

    }

    private void renderToFrameBuffer() {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        //生成frameBuffer
        mFrameBuffers = new int[1];
        GLES20.glGenFramebuffers(mFrameBuffers.length, mFrameBuffers, 0);
        GLES20.glBindFramebuffer(GL_FRAMEBUFFER, mFrameBuffers[0]);
        //生成texture
        mTextures = new int[1];
        GLES20.glGenTextures(mTextures.length, mTextures, 0);
        mTextureID = mTextures[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureID);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_MIRRORED_REPEAT);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_MIRRORED_REPEAT);
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, width, heigth,
                0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_SHORT_5_6_5, null);
        /**
         * public static native void glFramebufferTexture2D(
         int target, // 创建的帧缓冲类型的目标，一般为GL_FRAMEBUFFER
         int attachment, // 附着点，这里附着的事一个纹理，需要传入参数为一个颜色附着点
         int textarget, // 希望附着的纹理类型
         int texture, // 附加的纹理对象ID
         int level // Mipmap level 一般设置为0
         );
         attachment可以为一下几个枚举值： GL_COLOR_ATTACHMENT0、GL_DEPTH_ATTACHMENT、
         GL_STENCIL_ATTACHMENT分别对应颜色缓冲、深度缓冲和模板缓冲。
         */
        //纹理附着到帧缓冲中去
        GLES20.glFramebufferTexture2D(GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0,
                GLES20.GL_TEXTURE_2D, mTextureID, 0);
        //生成renderBuffer
        mRenderBuffers = new int[1];
        GLES20.glGenRenderbuffers(mRenderBuffers.length, mRenderBuffers, 0);
        int renderID = mRenderBuffers[0];
        GLES20.glBindRenderbuffer(GL_RENDERBUFFER, renderID);
        //生成一个renderbuffer后，它本身并不会自动的分配内存空间，我们需要调用API来分配指定的内存空间
        //设置为深度的Render Buffer，并传入大小
        GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16, width, heigth);
        /**
         * public static native void glFramebufferRenderbuffer(
         int target,
         int attachment,
         int renderbuffertarget, // 必须为GL_RENDERBUFFER
         int renderbuffer // 渲染缓冲区对象
         );
         */
        GLES20.glFramebufferRenderbuffer(GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT, GLES20.GL_RENDERBUFFER, renderID);

        //在完成所有附着的添加后，需要使用函数glCheckFramebufferStatus 函数检查帧缓冲区是否完整。
        if (GLES20.glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            Toast.makeText(mContext, "FRAMEBUFFER ERROR", Toast.LENGTH_LONG).show();
        }
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(mProgram);
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, mVectexbuffer);

        int aTexturePositionHandle = GLES20.glGetAttribLocation(mProgram, "aTextureCoord");
        GLES20.glEnableVertexAttribArray(aTexturePositionHandle);
        GLES20.glVertexAttribPointer(aTexturePositionHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, mTextureBuffer);

        int aNormalPosition = GLES20.glGetAttribLocation(mProgram, "aNormalPosition");
        GLES20.glEnableVertexAttribArray(aNormalPosition);
        GLES20.glVertexAttribPointer(aNormalPosition, 3, GLES20.GL_FLOAT, false, 3 * 4, mNormalsBuffer);

        int vProjectMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uPmatrix");
        GLES20.glUniformMatrix4fv(vProjectMatrixHandle, 1, false, projectMatrix, 0);

        int vViewMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uVmatrix");
        GLES20.glUniformMatrix4fv(vViewMatrixHandle, 1, false, viewMatrix, 0);

        int vModeMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMmatrix");
        GLES20.glUniformMatrix4fv(vModeMatrixHandle, 1, false, modeMatrix, 0);

        int uLightLocationHandle = GLES20.glGetUniformLocation(mProgram, "uLightLocation");
        GLES20.glUniform3f(uLightLocationHandle, 0, 10.0f, 10.0f);

        int uTexture = GLES20.glGetUniformLocation(mProgram, "uTexture");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mLocalTextureID);//图片
        GLES20.glUniform1i(uTexture, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mVectexCount);
        //  GLES20.glDisableVertexAttribArray(positionHandle);
    }

    private void init() {

        readObj();
        mProgram = ShaderUtils.createProgram(mContext.getResources(), vectexShaderCode, fragmentColorCode);

        //window vectexcoord
        ByteBuffer vectexCoordsBuffer = ByteBuffer.allocateDirect(vectexCoords.length * 4);
        mWindowVectexCount = vectexCoords.length / 3;
        vectexCoordsBuffer.order(ByteOrder.nativeOrder());
        mWindowVectexBuffer = vectexCoordsBuffer.asFloatBuffer();
        mWindowVectexBuffer.put(vectexCoords);
        mWindowVectexBuffer.position(0);
        //window texturecoord
        ByteBuffer textureCoordsBuffer = ByteBuffer.allocateDirect(textureCoords.length * 4);
        textureCoordsBuffer.order(ByteOrder.nativeOrder());
        mWindowTextureBuffer = textureCoordsBuffer.asFloatBuffer();
        mWindowTextureBuffer.put(textureCoords);
        mWindowTextureBuffer.position(0);
        mWindowProgram = ShaderUtils.createProgram(mContext.getResources(), windowVectexShaderCode, windowFragmentColorCode);
    }

    private void readObj() {
        InputStream is = null;
        try {
            is = mContext.getAssets().open("obj/obj.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        RawOpenGLModel rawOpenGLModel = new Obj2OpenJL().convert(is);
        OpenGLModelData openGLModelData = rawOpenGLModel.normalize().center().getDataForGLDrawArrays();
        //顶点坐标
        float[] vertices = openGLModelData.getVertices();
        mVectexCount = vertices.length / 3;
        ByteBuffer vectexCoordsBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        vectexCoordsBuffer.order(ByteOrder.nativeOrder());
        mVectexbuffer = vectexCoordsBuffer.asFloatBuffer();
        mVectexbuffer.put(vertices);
        mVectexbuffer.position(0);
        //纹理坐标
        float[] textureCoordinates = openGLModelData.getTextureCoordinates();
        ByteBuffer textureCoordsBuffer = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
        textureCoordsBuffer.order(ByteOrder.nativeOrder());
        mTextureBuffer = textureCoordsBuffer.asFloatBuffer();
        mTextureBuffer.put(textureCoordinates);
        mTextureBuffer.position(0);
        //法线
        float[] normals = openGLModelData.getNormals();
        ByteBuffer normalsCoordBuffer = ByteBuffer.allocateDirect(normals.length * 4);
        normalsCoordBuffer.order(ByteOrder.nativeOrder());
        mNormalsBuffer = normalsCoordBuffer.asFloatBuffer();
        mNormalsBuffer.put(normals);
        mNormalsBuffer.position(0);

    }

    interface OnShotScreenListener {
        void onShotScreenListener(int width, int height, ByteBuffer buffer);
    }

    public void setOnShotScreenListener(OnShotScreenListener onShotScreenListener) {
        mOnShotScreenListener = onShotScreenListener;
    }

}
