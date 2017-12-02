package study.qi.com.tantan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import study.qi.com.tantan.manager.CardCallback;
import study.qi.com.tantan.manager.CardLayoutManager;
import study.qi.com.tantan.view.RoundImageView;

public class MainActivity extends AppCompatActivity implements CardCallback.OnSwipedListener {

    private RecyclerView mRecyclerView;
    private List<Integer> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        datas.add(R.drawable.img_avatar_01);
        datas.add(R.drawable.img_avatar_02);
        datas.add(R.drawable.img_avatar_03);
        datas.add(R.drawable.img_avatar_04);
        datas.add(R.drawable.img_avatar_05);
        datas.add(R.drawable.img_avatar_06);
        datas.add(R.drawable.img_avatar_07);
        MyAdapter myAdapter = new MyAdapter();
        CardCallback cardCallback = new CardCallback(myAdapter, datas);
        cardCallback.setOnSwipedListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(cardCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        CardLayoutManager cardLayoutManager = new CardLayoutManager(mRecyclerView, itemTouchHelper);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(cardLayoutManager);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public void onSwipeing() {

    }

    @Override
    public void onSwiped() {

    }

    @Override
    public void onSwipedClear() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 3000);

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mRoundImageView.setImageResource(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private RoundImageView mRoundImageView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mRoundImageView = (RoundImageView) itemView.findViewById(R.id.iv_avatar);
            }
        }
    }
}
