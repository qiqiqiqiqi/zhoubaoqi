package com.qi.wechatclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qi.wechatclient.R;
import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.chat.ui.activity.ChatActivity;
import com.qi.wechatclient.dao.FriendDao;
import com.qi.wechatclient.dao.MessageInfoDao;
import com.qi.wechatclient.fragment.adapter.MessageAdapter;
import com.qi.wechatclient.pushReport.MessagePushReport.MessagePushReport;
import com.qi.wechatclient.pushReport.MessagePushReport.MessagePushReportListener;
import com.qi.wechatclient.utils.LogUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener, MessageAdapter.OnItemClickListener, MessagePushReportListener {

    private static final String TAG = MessageFragment.class.getSimpleName();
    private RecyclerView mMessageRecyclerView;
    private RelativeLayout mMessageTitleContainer;
    private int mDistanceY;
    private TextView mMessageTitle;
    private List<MessageInfo> mMessageInfos;
    private MessageAdapter mMessageAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        MessagePushReport.getInstance().registerPushReportListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        refresh();
    }

    private void refresh() {
        mMessageInfos = MessageInfoDao.getMessageInfoDaoInstance().queryCurrentFriendsMessageInfo(mCurrentUserId);
        LogUtil.d(TAG, "initData():mMessageInfos=" + mMessageInfos);
        if (mMessageAdapter == null) {
            mMessageAdapter = new MessageAdapter(getActivity(), mMessageInfos, this);
            mMessageAdapter.setOnItemClickListener(this);
            mMessageRecyclerView.setAdapter(mMessageAdapter);
        } else {
            mMessageAdapter.setData(mMessageInfos);
            mMessageAdapter.notifyDataSetChanged();
        }
    }

    private void initEvent() {
        mMessageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LogUtil.d(TAG, "onScrolled()");
                super.onScrolled(recyclerView, dx, dy);
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = mMessageTitleContainer.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    mMessageTitleContainer.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mMessageTitle.setTextColor(Color.argb((int) alpha, 120, 121, 120));
                    if (mDistanceY == 0) {
                        mMessageTitle.setTextColor(getResources().getColor(R.color.common_white));
                    }
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    mMessageTitleContainer.setBackgroundResource(R.color.common_white);
                }
            }
        });
    }

    private void initView(View view) {
        mMessageTitleContainer = (RelativeLayout) view.findViewById(R.id.message_title);
        mMessageTitle = (TextView) view.findViewById(R.id.main_title);
        mMessageRecyclerView = (RecyclerView) view.findViewById(R.id.message_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMessageRecyclerView.setLayoutManager(linearLayoutManager);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(int position, MessageInfo messageInfo) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        Friend friend = FriendDao.getFriendDaoInstance().queryFriend(mCurrentUserId, messageInfo.getFriendID());
        LogUtil.d(TAG, "onItemClick():messageInfo=" + messageInfo + ",friend=" + friend);
        intent.putExtra(com.qi.wechatclient.config.Constants.INTENT_FRIEND, friend);
        startActivity(intent);
    }

    @Override
    public void onVisiable(boolean visiable) {
        super.onVisiable(visiable);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMessageRecyclerView != null) {
            refresh();
        }
    }

    @Override
    public void onMessagePushReportListener(List<MessageInfo> messageInfos) {
        LogUtil.d(TAG, "onMessagePushReportListener():messageInfos=" + messageInfos);
        refresh();
    }
}
