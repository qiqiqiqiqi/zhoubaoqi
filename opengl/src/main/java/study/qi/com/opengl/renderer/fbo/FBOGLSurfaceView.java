package study.qi.com.opengl.renderer.fbo;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.nio.ByteBuffer;

/**
 * Created by feng on 2018/3/25.
 */

public class FBOGLSurfaceView extends GLSurfaceView implements FBORenderer.OnShotScreenListener {

    private float mPreviousY;
    private float mPreviousX;
    private FBORenderer mFboRenderer;
    private FrameCallBack mFrameCallBack;

    public FBOGLSurfaceView(Context context) {
        this(context, null);
    }

    public FBOGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.setEGLContextClientVersion(2);
        mFboRenderer = new FBORenderer(context,
                "fbo/fbo_color_vertex.frag",
                "fbo/fbo_color_fragment.frag",
                "fbo/fbo_color_window_vectex.frag",
                "fbo/fbo_color_window_fragment.frag");
        mFboRenderer.setOnShotScreenListener(this);
        this.setRenderer(mFboRenderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public boolean onTouchEvent(MotionEvent e) {
        float y = e.getY();
        float x = e.getX();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dy = y - mPreviousY;
                float dx = x - mPreviousX;
                mFboRenderer.yAngle += dx;
                mFboRenderer.xAngle += dy;
                requestRender();
        }
        mPreviousY = y;
        mPreviousX = x;
        return true;
    }

    @Override
    public void onShotScreenListener(int width, int height, ByteBuffer buffer) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
        buffer.position(0);
        if (mFrameCallBack != null) {
            mFrameCallBack.onFrameCallBack(bitmap);
        }
    }

    public interface FrameCallBack {
        void onFrameCallBack(Bitmap bitmap);
    }

    public void setOnFrameCallBack(FrameCallBack frameCallBack) {
        mFrameCallBack = frameCallBack;
    }
}
