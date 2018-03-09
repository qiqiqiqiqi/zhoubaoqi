package study.qi.com.opengl;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import study.qi.com.opengl.renderer.Triangle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mShapeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

    }

    private void initView() {
        mShapeButton = findViewById(R.id.shape);
        mShapeButton.setOnClickListener(this);
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
        }
        startActivity(intent);
    }
}
