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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import study.qi.com.tantan.itemanimator.SlideItemAnimator;
import study.qi.com.tantan.manager.CardCallback;
import study.qi.com.tantan.manager.CardLayoutManager;
import study.qi.com.tantan.view.RoundImageView;

public class MainActivity extends AppCompatActivity implements CardCallback.OnSwipedListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<Integer> datas = new ArrayList<>();
    private TextView mNext;
    private MyAdapter mMyAdapter;
    private ItemTouchHelper mItemTouchHelper;

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
//        mItemTouchHelper = new ItemTouchHelper(cardCallback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        CardLayoutManager cardLayoutManager = new CardLayoutManager(mRecyclerView,null /*mItemTouchHelper*/);
        mRecyclerView.setItemAnimator(new SlideItemAnimator());
        mRecyclerView.setLayoutManager(cardLayoutManager);
        mRecyclerView.setAdapter(mMyAdapter);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mNext = (TextView) findViewById(R.id.next);
        mNext.setOnClickListener(this);
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
        if(datas!=null&&!datas.isEmpty()){
//            RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
//          //  mItemTouchHelper.startSwipe(childViewHolder);
//            int adapterPosition = childViewHolder.getAdapterPosition();
//            Integer remove = datas.remove(adapterPosition);
            datas.remove(0);
            mMyAdapter.notifyItemRemoved(0);
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
