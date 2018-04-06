package study.qi.com.opengl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import study.qi.com.opengl.renderer.image.AbstractFilter;
import study.qi.com.opengl.renderer.image.ImageColorFilter;
import study.qi.com.opengl.renderer.image.ImageGLSurfaceView;

public class HandleImageActivity extends AppCompatActivity {

    private ImageGLSurfaceView mImageGLSurfaceView;
    private AbstractFilter mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_image);
        initView();
    }

    private void initView() {
        mImageGLSurfaceView = findViewById(R.id.glsurfaceview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mDefault:
                mFilter = new ImageColorFilter(this, ImageColorFilter.Filter.NONE);
                break;
            case R.id.mGray:
                mFilter = new ImageColorFilter(this, ImageColorFilter.Filter.GRAY);
                break;
            case R.id.mCool:
                mFilter = new ImageColorFilter(this, ImageColorFilter.Filter.COOL);
                break;
            case R.id.mWarm:
                mFilter = new ImageColorFilter(this, ImageColorFilter.Filter.WARM);
                break;
            case R.id.mBlur:
                mFilter = new ImageColorFilter(this, ImageColorFilter.Filter.BLUR);
                break;
//            case R.id.mMagn:
//
//                break;

        }
        mImageGLSurfaceView.setFilter(mFilter);
        return super.onOptionsItemSelected(item);
    }
}
