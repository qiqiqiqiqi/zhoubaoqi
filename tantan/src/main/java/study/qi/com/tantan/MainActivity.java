package study.qi.com.tantan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import study.qi.com.tantan.itemanimator.SlideInItemAnimator;
import study.qi.com.tantan.itemanimator.SlideOutItemAnimator;
import study.qi.com.tantan.manager.CardCallback;
import study.qi.com.tantan.manager.CardLayoutManager2;
import study.qi.com.tantan.view.RoundImageView;

public class MainActivity extends AppCompatActivity implements CardCallback.OnSwipedListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<Integer> datas = new ArrayList<>();
    private List<Integer> preDatas = new ArrayList<>();
    private TextView mNext;
    private MyAdapter mMyAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private CardLayoutManager2 mCardLayoutManager;
    private TextView mPre;

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
        mMyAdapter = new MyAdapter();
        CardCallback cardCallback = new CardCallback(mMyAdapter, datas);
        cardCallback.setOnSwipedListener(this);
        mItemTouchHelper = new ItemTouchHelper(cardCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mCardLayoutManager = new CardLayoutManager2(mRecyclerView, mItemTouchHelper);
        setItemInAnimator();
        mRecyclerView.setLayoutManager(mCardLayoutManager);
        mRecyclerView.setAdapter(mMyAdapter);
    }

    private void setItemOutAnimator() {
        SlideOutItemAnimator slideOutItemAnimator = new SlideOutItemAnimator();
        slideOutItemAnimator.setRemoveDuration(500);
        slideOutItemAnimator.setAddDuration(1);
        mRecyclerView.setItemAnimator(slideOutItemAnimator);
    }

    private void setItemInAnimator() {
        SlideInItemAnimator slideInItemAnimator = new SlideInItemAnimator();
        slideInItemAnimator.setRemoveDuration(1);
        slideInItemAnimator.setAddDuration(500);
        mRecyclerView.setItemAnimator(slideInItemAnimator);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mNext = (TextView) findViewById(R.id.next);
        mPre = (TextView) findViewById(R.id.pre);
        mNext.setOnClickListener(this);
        mPre.setOnClickListener(this);
    }

    @Override
    public void onSwipeing() {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer integer, int direction) {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                setItemOutAnimator();
                if (datas != null && !datas.isEmpty()) {
                    Integer remove = datas.remove(0);
                    preDatas.add(remove);
                    mMyAdapter.notifyItemRemoved(0);

                }
                if (datas.isEmpty()) {
                    mRecyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initData();
                        }
                    }, 3000);
                }
                break;
            case R.id.pre:
                setItemInAnimator();
                if (!preDatas.isEmpty()) {
                    datas.add(0, preDatas.remove(preDatas.size() - 1));
                    mMyAdapter.notifyItemInserted(0);
                }

                break;
        }


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
