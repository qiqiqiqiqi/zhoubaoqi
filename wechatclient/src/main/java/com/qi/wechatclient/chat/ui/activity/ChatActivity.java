package com.qi.wechatclient.chat.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.jude.easyrecyclerview.EasyRecyclerView;
import com.qi.wechatclient.R;
import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.chat.adapter.ChatAdapter;
import com.qi.wechatclient.chat.adapter.CommonFragmentPagerAdapter;
import com.qi.wechatclient.chat.enity.FullImageInfo;
import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.chat.ui.fragment.ChatEmotionFragment;
import com.qi.wechatclient.chat.ui.fragment.ChatFunctionFragment;
import com.qi.wechatclient.chat.util.Constants;
import com.qi.wechatclient.chat.util.GlobalOnItemClickManagerUtils;
import com.qi.wechatclient.chat.util.MediaManager;
import com.qi.wechatclient.chat.widget.EmotionInputDetector;
import com.qi.wechatclient.chat.widget.NoScrollViewPager;
import com.qi.wechatclient.chat.widget.StateButton;
import com.qi.wechatclient.common.BaseActivity;
import com.qi.wechatclient.dao.MessageInfoDao;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.model.Event.ReceiveMessageEvent;
import com.qi.wechatclient.model.request.SendMessageRequest;
import com.qi.wechatclient.pushReport.MessagePushReport.MessagePushReport;
import com.qi.wechatclient.pushReport.MessagePushReport.MessagePushReportListener;
import com.qi.wechatclient.utils.LogUtil;
import com.qi.wechatclient.view.NavigationBar;
import com.qi.wechatclient.view.notification.CommonNotification;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class ChatActivity extends BaseActivity implements EmotionInputDetector.OnSendMessageListener, MessagePushReportListener {
    private static final String TAG = ChatActivity.class.getSimpleName();
    EasyRecyclerView chatList;

    ImageView emotionVoice;

    EditText editText;

    TextView voiceText;
    ImageView emotionButton;
    ImageView emotionAdd;
    StateButton emotionSend;
    NoScrollViewPager viewpager;
    RelativeLayout emotionLayout;


    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    private NavigationBar mNavigationBar;
    private Friend mFriend;
    private SendMessageRequest mSendMessageRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mFriend = (Friend) getIntent().getSerializableExtra(com.qi.wechatclient.config.Constants.INTENT_FRIEND);
        LogUtil.d(TAG, "onCreate():mFriend=" + mFriend);
        initView();
        initData();
        initWidget();
        MessagePushReport.getInstance().registerPushReportListener(this);
    }

    private void initData() {
        mSendMessageRequest = new SendMessageRequest() {
            @Override
            public void onSendMessageRequestResult(int result, int messageType, String messageInfoID) {
                LogUtil.d(TAG, "onSendMessageRequestResult():result=" + result + ",messageInfoID=" + messageInfoID);
                if (result == ErrorCode.SUCCESS) {
                    for (MessageInfo messageInfo : messageInfos) {
                        if (messageInfoID.equals(messageInfo.getMessageInfoID())) {
                            messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                            chatAdapter.notifyItemChanged(messageInfos.indexOf(messageInfo));
                            chatList.scrollToPosition(chatAdapter.getCount() - 1);
                        }
                    }

                }
            }
        };


    }


    private void initView() {

        chatList = (EasyRecyclerView) findViewById(R.id.chat_list);
        emotionVoice = (ImageView) findViewById(R.id.emotion_voice);
        editText = (EditText) findViewById(R.id.edit_text);
        voiceText = (TextView) findViewById(R.id.voice_text);
        emotionButton = (ImageView) findViewById(R.id.emotion_button);
        emotionAdd = (ImageView) findViewById(R.id.emotion_add);
        emotionSend = (StateButton) findViewById(R.id.emotion_send);
        viewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        emotionLayout = (RelativeLayout) findViewById(R.id.emotion_layout);
        mNavigationBar = (NavigationBar) findViewById(R.id.navigation);
        if (mFriend != null) {
            mNavigationBar.setText(mFriend.getFriendName());
        }
    }

    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .bindToFriend(mFriend)
                .build();
        mDetector.setOnSendMessageListener(this);
        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);

        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);
        LoadData();
    }

    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(ChatActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(ChatActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.drawable.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.drawable.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    /**
     * 构造聊天数据
     */
    private void LoadData() {
        messageInfos = MessageInfoDao.getMessageInfoDaoInstance().queryMessageInfo(mCurrentUserID, mFriend.getFriendID());
        if (messageInfos != null && !messageInfos.isEmpty()) {
            chatAdapter.addAll(messageInfos);
            chatList.scrollToPosition(chatAdapter.getCount() - 1);
        }
//        this.messageInfos = new ArrayList<>();
//
//        MessageInfo messageInfo = new MessageInfo();
//        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
//        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//        messageInfo.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//        this.messageInfos.add(messageInfo);
//
//        MessageInfo messageInfo1 = new MessageInfo();
//        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
//        messageInfo1.setVoiceTime(3000);
//        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
//        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//        messageInfo1.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
//        this.messageInfos.add(messageInfo1);
//
//        MessageInfo messageInfo2 = new MessageInfo();
//        messageInfo2.setImageUrl("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
//        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//        messageInfo2.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//        this.messageInfos.add(messageInfo2);
//
//        MessageInfo messageInfo3 = new MessageInfo();
//        messageInfo3.setContent("[微笑][色][色][色]");
//        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
//        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
//        messageInfo3.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
//        this.messageInfos.add(messageInfo3);
//
//        chatAdapter.addAll(this.messageInfos);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        messageInfos.add(messageInfo);
        chatAdapter.add(messageInfo);
        chatList.scrollToPosition(chatAdapter.getCount() - 1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                chatAdapter.notifyDataSetChanged();
            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MessageInfo message = new MessageInfo();
                message.setContent("这是模拟消息回复");
                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                message.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
                messageInfos.add(message);
                chatAdapter.add(message);
                chatList.scrollToPosition(chatAdapter.getCount() - 1);
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSendMessage(final MessageInfo messageInfo) {
        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");

        messageInfos.add(messageInfo);
        chatAdapter.add(messageInfo);
        chatList.scrollToPosition(chatAdapter.getCount() - 1);
        mSendMessageRequest.startSendMessageRequest(messageInfo);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                chatAdapter.notifyDataSetChanged();
//            }
//        }, 2000);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                MessageInfo message = new MessageInfo();
//                message.setContent("这是模拟消息回复");
//                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//                message.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//                messageInfos.add(message);
//                chatAdapter.add(message);
//                chatList.scrollToPosition(chatAdapter.getCount() - 1);
//            }
//        }, 3000);
    }

    @Override
    public void onMessagePushReportListener(List<MessageInfo> messageInfos) {
        LogUtil.d(TAG, "onMessagePushReportListener():messageInfos=" + messageInfos);
        for (MessageInfo messageInfo : messageInfos) {
            if (mFriend != null && messageInfo.getFriendID().equals(mFriend.getFriendID())) {
                this.messageInfos.add(messageInfo);
                chatAdapter.add(messageInfo);
                CommonNotification.showNotificaton(mFriend.getFriendName(), messageInfo.getContent());
            }
        }
        if (chatAdapter != null && chatAdapter.getCount() > 0) {
            chatList.scrollToPosition(chatAdapter.getCount() - 1);
        }


    }
}
