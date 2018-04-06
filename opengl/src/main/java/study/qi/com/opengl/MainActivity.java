package study.qi.com.opengl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mShapeButton;
    private Button mHandleImage;
    private Button mCamarePreview;
    private Button mFbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

    }

    private void initView() {
        mShapeButton = findViewById(R.id.shape);
        mShapeButton.setOnClickListener(this);
        mHandleImage = findViewById(R.id.handle_image);
        mHandleImage.setOnClickListener(this);
        mCamarePreview = findViewById(R.id.camare_preview);
        mCamarePreview.setOnClickListener(this);
        mFbo = findViewById(R.id.fbo);
        mFbo.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.shape:
                intent = new Intent(this, ShapeActivity.class);
                break;
            case R.id.handle_image:
                intent = new Intent(this, HandleImageActivity.class);
                break;
            case R.id.camare_preview:
                intent = new Intent(this, CameraActivity.class);
                break;
            case R.id.fbo:
                intent = new Intent(this, FBOActivity.class);
                break;
        }
        startActivity(intent);
    }
}
