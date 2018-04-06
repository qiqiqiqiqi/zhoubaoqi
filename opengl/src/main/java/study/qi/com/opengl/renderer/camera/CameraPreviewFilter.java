package study.qi.com.opengl.renderer.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import study.qi.com.opengl.camare.CameraGLSurfaceView;
import study.qi.com.opengl.camare.KitkatCamera;

/**
 * Created by feng on 2018/3/12.
 */

public class CameraPreviewFilter extends AbstractFilter implements SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = CameraPreviewFilter.class.getSimpleName();
    private CameraGLSurfaceView mGLSurfaceView;
    private KitkatCamera mKitkatCamera;
    private SurfaceTexture mSurfaceTexture;
    private int mTextureID;

    public CameraPreviewFilter(Context context, CameraGLSurfaceView glSurfaceView) {
        super(context, "filter/oes_vertex.sh", "filter/oes_fragment.sh");
        mGLSurfaceView = glSurfaceView;
        mKitkatCamera = new KitkatCamera();
        cameraID = BACK_CAMERA;

    }

    @Override
    public void onCreated(GL10 gl, EGLConfig config) {

        mTextureID = createExternalTexture();
        boolean open = mKitkatCamera.open(cameraID);//1
        Log.d(TAG, "onCreated():mTextureID=" + mTextureID + ",open=" + open);
        mSurfaceTexture = new SurfaceTexture(mTextureID);//2
        mKitkatCamera.setPreviewTexture(mSurfaceTexture);
        mSurfaceTexture.setOnFrameAvailableListener(this);
        Point previewSize = mKitkatCamera.getPreviewSize();
        setDataSize(previewSize.x, previewSize.y);
        mKitkatCamera.preview();
    }

    @Override
    public void onChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onChanged()");
        if (cameraID == FRONT_CAMERA) {
            Matrix.rotateM(modeMatrix, 0, 90, 0.0f, 0.0f, 1.0f);
            Matrix.scaleM(modeMatrix, 0, 1.0f, -1.0f, 1.0f);
        } else {
            Matrix.rotateM(modeMatrix, 0, -90, 0.0f, 0.0f, 1.0f);
        }
    }

    @Override
    public void onFrame(GL10 gl) {
        Log.d(TAG, "onFrame");
        if (mSurfaceTexture != null) {
            mSurfaceTexture.updateTexImage();
        }
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureID);
        int vTextureHandle = GLES20.glGetUniformLocation(mProgram, "vTexture");
        GLES20.glUniform1i(vTextureHandle, 0);

    }

    public boolean switchCamera() {
        closeCamera();
        cameraID = cameraID == BACK_CAMERA ? FRONT_CAMERA : BACK_CAMERA;
        return mKitkatCamera.open(cameraID);

    }

    public void openCamera() {
        mKitkatCamera.open(cameraID);
    }

    public void openCamera(int cameraID) {
        closeCamera();
        mKitkatCamera.open(cameraID);
    }

    public void closeCamera() {
        mKitkatCamera.close();
    }

    private int createExternalTexture() {
        int[] texture = new int[1];
        //生成纹理
        GLES20.glGenTextures(1, texture, 0);
        //生成纹理
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);
        //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        return texture[0];
    }


    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.d(TAG, "onFrameAvailable()");
        mGLSurfaceView.requestRender();
    }
}
