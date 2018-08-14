package com.qi.customview.view.pinned;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qi.customview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PinendActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();
    private String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinend);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 1000; i++) {
            datas.add(chars[(int) (Math.random() * 26)] + chars[(int) (Math.random() * 26)] + chars[(int) (Math.random() * 26)]);
        }
        Collections.sort(datas, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new PinnedSectionDecoration(PinendActivity.this, new PinnedSectionDecoration.DecorationCallback() {
            @Override
            public long getGroupId(int position) {
                return datas.get(position).charAt(0);
            }

            @Override
            public String getGroupFirstLine(int position) {
                return datas.get(position).substring(0, 1).toUpperCase();
            }
        }));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(PinendActivity.this).inflate(R.layout.item_pinned, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTextView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.item_pinned_text);
            }
        }
    }
}
