package study.qi.com.algorithm.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

import study.qi.com.algorithm.R;

/**
 * 快速排序
 * 快速排序是一种交换类的排序，它同样是分治法的经典体现。在一趟排序中将待排序的序列分割成两组，
 * 其中一部分记录的关键字均小于另一部分。然后分别对这两组继续进行排序，以使整个序列有序。在分割的过程中，
 * 枢纽值的选择至关重要，本文采取了三位取中法，可以很大程度上避免分组"一边倒"的情况。快速排序平均时间复杂度也为O(nlogn)级。
 * http://www.cnblogs.com/chengxiao/p/6262208.html
 */
public class QuickSortActivity extends AppCompatActivity {
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

        quickSort(mArrays, 0, mArrays.length - 1);
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

    private void quickSort(int[] sourceArrays, int left, int right) {
        if (left < right) {
            handlePovit(sourceArrays, left, right);
            int leftPoint = left;//指向第一个元素
            int povitPoint = right - 1;
            int rightPoint = right - 1;//开始指向枢纽元素

            while (true) {
                while (leftPoint < povitPoint && sourceArrays[++leftPoint] < sourceArrays[povitPoint]) {//从左边找到大于枢纽的数，注意角标越界
                }
                while (rightPoint > leftPoint && sourceArrays[--rightPoint] > sourceArrays[povitPoint]) {//从右边找到小于枢纽的数，注意角标越界
                }
                if (leftPoint < rightPoint) {//如果左右指针没有交叉则交换位置，否则当前右指针所指位置的元素与枢纽元素交换位置
                    swap(sourceArrays, leftPoint, rightPoint);
                } else {
                    break;
                }
            }

            swap(sourceArrays, leftPoint, povitPoint);
            quickSort(sourceArrays, left, leftPoint - 1);//以当前枢纽值的位置切割数组，左右两部分数组重复递归
            quickSort(sourceArrays, leftPoint + 1, right);
        }

    }

    /**
     * 找出枢纽值，并且把枢纽值放在当前数组的倒数第二位
     * @param sourceArrays
     * @param left
     * @param right
     */
    private void handlePovit(int[] sourceArrays, int left, int right) {
        int middle = (left + right) / 2;
        if (sourceArrays[left] > sourceArrays[middle]) {
            swap(sourceArrays, left, middle);
        }
        if (sourceArrays[left] > sourceArrays[right]) {
            swap(sourceArrays, left, right);
        }
        if (sourceArrays[middle] > sourceArrays[right]) {
            swap(sourceArrays, middle, right);
        }
        swap(sourceArrays, middle, right - 1);
    }

    private void swap(int[] sourceArrays, int left, int right) {
        int temp = sourceArrays[left];
        sourceArrays[left] = sourceArrays[right];
        sourceArrays[right] = temp;
    }
}
