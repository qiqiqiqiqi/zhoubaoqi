package com.qi.wechatclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qi.wechatclient.R;
import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.chat.ui.activity.ChatActivity;
import com.qi.wechatclient.config.Constants;
import com.qi.wechatclient.dao.FriendDao;
import com.qi.wechatclient.fragment.adapter.ContactsAdapter;
import com.qi.wechatclient.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends BaseFragment implements View.OnClickListener, ContactsAdapter.OnItemClickListener {
    private static final String TAG = ContactsFragment.class.getSimpleName();

    private RecyclerView mContactsRecyclerview;
    private TextView mTitleView;
    private ImageView mAddView;
    private RelativeLayout mTitleContainer;
    private int mScrollY;
    private int mTitleContainerHeight;
    private ContactsAdapter mContactsAdapter;
    private List<Friend> mFriends;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        mFriends = FriendDao.getFriendDaoInstance().queryFriendList(mCurrentUserId);
        refresh();
        LogUtil.d(TAG, "initData():mFriends=" + mFriends);
    }

    private void initEvent() {
        mContactsRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollY += dy;
                mTitleContainerHeight = mTitleContainer.getBottom();
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mScrollY <= mTitleContainerHeight) {
                    float scale = (float) mScrollY / mTitleContainerHeight;
                    float alpha = scale * 255;
                    mTitleContainer.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mTitleView.setTextColor(Color.argb((int) alpha, 120, 121, 120));
                    if (mScrollY == 0) {
                        mTitleView.setTextColor(getResources().getColor(R.color.common_white));
                    }
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    mTitleContainer.setBackgroundResource(R.color.common_white);
                }
            }
        });
    }

    private void initView(View view) {
        mTitleContainer = (RelativeLayout) view.findViewById(R.id.contacts_title);
        mContactsRecyclerview = (RecyclerView) view.findViewById(R.id.contacts_recyclerview);
        mTitleView = (TextView) view.findViewById(R.id.main_title);
        mAddView = (ImageView) view.findViewById(R.id.main_add);
        mAddView.setOnClickListener(this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        mContactsRecyclerview.setLayoutManager(gridLayoutManager);

    }

    private void refresh() {
        if (mContactsAdapter == null) {
            mContactsAdapter = new ContactsAdapter(getActivity(), mFriends, this);
            mContactsAdapter.setOnItemClickListener(this);
            mContactsRecyclerview.setAdapter(mContactsAdapter);
        } else {
            mContactsAdapter.notifyDataSetChanged();
        }
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

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("demo");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/storage/emulated/0/fitforce/screenshot.png");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(getActivity());
    }


    @Override
    public void onClick(View v) {
        showShare();
    }

    @Override
    public void onItemClick(int positon, Friend friend) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(Constants.INTENT_FRIEND, friend);
        startActivity(intent);

    }


}
