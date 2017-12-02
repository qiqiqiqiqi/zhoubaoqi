package study.qi.com.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import study.qi.com.algorithm.R;

//https://www.cnblogs.com/chengxiao/p/6129630.html
public class HeapSortActivity extends AppCompatActivity {

    private TextView mSourceArray;
    private TextView mSortedArray;
    private int[] mArrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heap_sort);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        mArrays = new int[10];
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

        sort(mArrays);
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

    public void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
