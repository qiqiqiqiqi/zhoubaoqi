package study.qi.com.opengl.renderer.image;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.io.IOException;

/**
 * Created by feng on 2018/3/10.
 */

public class ImageGLSurfaceView extends GLSurfaceView {

    private ImageColorFilter mImageColorFilter;
    private ImageRenderer mImageRenderer;

    public ImageGLSurfaceView(Context context) {
        this(context, null);
    }

    public ImageGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mImageColorFilter = new ImageColorFilter(context, ImageColorFilter.Filter.NONE);
        mImageRenderer = new ImageRenderer();
        mImageRenderer.setFilter(mImageColorFilter);
        setEGLContextClientVersion(2);
        this.setRenderer(mImageRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setBitmap();
    }

    private void setBitmap() {
        try {
            mImageRenderer.getFilter().setBitmap(BitmapFactory.decodeStream(getResources().getAssets().open("texture/dog.jpg")));
            requestRender();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFilter(AbstractFilter filter) {
        mImageRenderer.setFilter(filter);
        setBitmap();
    }
}
