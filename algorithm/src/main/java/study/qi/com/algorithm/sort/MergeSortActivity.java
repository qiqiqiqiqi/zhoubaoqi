package study.qi.com.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import study.qi.com.algorithm.R;

/**
 * 归并排序
 */
public class MergeSortActivity extends AppCompatActivity {
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

        sort(mArrays, 0, mArrays.length - 1, mTempArrays);
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int index : mArrays) {
            stringBuilder2.append(index + ",");
        }
        stringBuilder2.replace(stringBuilder2.length() - 1, stringBuilder2.length(), "");
        mSortedArray.setText("排序数组:" + stringBuilder2.toString());
    }

    private void initEvent() {
    }

    private void initView() {
        mSourceArray = findViewById(R.id.source_array);
        mSortedArray = findViewById(R.id.sorted_array);
    }

    private void sort(int[] sourceArrays, int left, int right, int[] tempArrays) {
        if (left < right) {
            sort(sourceArrays, left, (left + right) / 2, tempArrays);
            sort(sourceArrays, (left + right) / 2 + 1, right, tempArrays);
            merge(sourceArrays, left, (left + right) / 2, right, tempArrays);
        }
    }

    private void merge(int[] sourceArrays, int left, int middle, int right, int[] tempArrays) {
        int left_start = left;
        int right_start = middle + 1;
        int temp_index = 0;
        while (left_start <= middle && right_start <= right) {
            if (sourceArrays[left_start] < sourceArrays[right_start]) {
                tempArrays[temp_index++] = sourceArrays[left_start++];
            } else {
                tempArrays[temp_index++] = sourceArrays[right_start++];
            }
        }

        while (left_start <= middle) {
            tempArrays[temp_index++] = sourceArrays[left_start++];
        }

        while (right_start <= right) {
            tempArrays[temp_index++] = sourceArrays[right_start++];
        }
        temp_index = 0;
        while (left <= right) {
            sourceArrays[left++] = tempArrays[temp_index++];
        }
    }
}
