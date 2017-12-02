package study.qi.com.algorithm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import study.qi.com.algorithm.sort.HeapSortActivity;
import study.qi.com.algorithm.sort.InsertSortActivity;
import study.qi.com.algorithm.sort.MergeSortActivity;
import study.qi.com.algorithm.sort.QuickSortActivity;
import study.qi.com.algorithm.sort.ShellSortActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mHeapSort, mMergeSort;
    private Button mQuickSort;

    private Button mInsertkSort;
    private Button mShellSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        mHeapSort.setOnClickListener(this);
        mMergeSort.setOnClickListener(this);
        mQuickSort.setOnClickListener(this);
        mInsertkSort.setOnClickListener(this);
        mShellSort.setOnClickListener(this);
    }

    private void initView() {
        mHeapSort = findViewById(R.id.heapSort);
        mMergeSort = findViewById(R.id.mergeSort);
        mQuickSort = findViewById(R.id.quickSort);
        mInsertkSort = findViewById(R.id.insertSort);
        mShellSort = findViewById(R.id.shellSort);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.heapSort:
                Intent intent = new Intent(this, HeapSortActivity.class);
                startActivity(intent);
                break;
            case R.id.mergeSort:
                Intent mergeIntent = new Intent(this, MergeSortActivity.class);
                startActivity(mergeIntent);
                break;
            case R.id.quickSort:
                Intent quickIntent = new Intent(this, QuickSortActivity.class);
                startActivity(quickIntent);
                break;
            case R.id.insertSort:
                Intent insertIntent = new Intent(this, InsertSortActivity.class);
                startActivity(insertIntent);
                break;
            case R.id.shellSort:
                Intent shellIntent = new Intent(this, ShellSortActivity.class);
                startActivity(shellIntent);
                break;
        }
    }
}
