package study.qi.com.algorithm.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

import study.qi.com.algorithm.R;

/**
 *
 */
public class ShellSortActivity extends AppCompatActivity {
    private TextView mSourceArray;
    private TextView mSortedArray;
    private int[] mArrays;
    private int[] mTempArrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_sort);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        mArrays = new int[10];
        mTempArrays = new int[mArrays.length];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            mArrays[i] = random.nextInt(100);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int index : mArrays) {
            stringBuilder.append(index + ",");
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        mSourceArray.setText("无序数组:" + stringBuilder.toString());

        shellSort(mArrays);
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int index : mArrays) {
            stringBuilder2.append(index + ",");
        }
        stringBuilder2.replace(stringBuilder2.length() - 1, stringBuilder2.length(), "");
        mSortedArray.setText("排序数组:" + stringBuilder2.toString());
    }

    private void shellSort(int[] arrays) {
        for (int grap = arrays.length / 2; grap > 0; grap /= 2) {
            for (int i = grap; i < arrays.length; i += grap) {
                int temp = arrays[i];
                int j;
                for (j = i - grap; j >= 0; j--) {
                    if (temp < arrays[j]) {
                        arrays[j + 1] = arrays[j];
                    } else {
                        break;
                    }
                }
                arrays[j + 1] = temp;
            }
        }
    }

    private void initEvent() {
    }

    private void initView() {
        mSourceArray = findViewById(R.id.source_array);
        mSortedArray = findViewById(R.id.sorted_array);
    }


}
