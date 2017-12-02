package com.qi.wechatclient.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qi.wechatclient.R;
import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.cache.UserCache;
import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.dao.FriendDao;
import com.qi.wechatclient.utils.DisplayUtils;

import java.util.List;

/**
 * Created by feng on 2017/6/29.
 */

public class MessageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MessageInfo> mMessageInfoList;
    private View.OnClickListener mOnClickListener;
    private OnItemClickListener mOnItemClickListener;

    public MessageAdapter(Context context, List<MessageInfo> messageInfos, View.OnClickListener onClickListener) {
        mContext = context;
        mMessageInfoList = messageInfos;
        mOnClickListener = onClickListener;
    }

    public void setData(List<MessageInfo> messageInfos) {
        mMessageInfoList = messageInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
            MessageViewHolder messageViewHolder = new MessageViewHolder(view);
            return messageViewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_message_top, null);
            MessageTopViewHolder messageTopViewHolder = new MessageTopViewHolder(view);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) messageTopViewHolder.top_container.getLayoutParams();
            layoutParams.height = DisplayUtils.getScreenHeight(mContext) * 1 / 2;
            messageTopViewHolder.top_container.setLayoutParams(layoutParams);
            return messageTopViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MessageViewHolder) {
            MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
            final MessageInfo messageInfo = mMessageInfoList.get(position - 1);
            Friend friend = FriendDao.getFriendDaoInstance().queryFriend(messageInfo.getUserID(), messageInfo.getFriendID());
            messageViewHolder.friendName.setText(friend.getFriendName());
            messageViewHolder.friendCurrentMessage.setText(messageInfo.getContent());
            messageViewHolder.messageContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position - 1, messageInfo);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mMessageInfoList != null) {
            return mMessageInfoList.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView friendHead;
        private TextView friendName;
        private TextView friendCurrentMessage;
        private LinearLayout messageContainer;

        public MessageViewHolder(View itemView) {
            super(itemView);
            friendHead = (ImageView) itemView.findViewById(R.id.friend_head);
            friendName = (TextView) itemView.findViewById(R.id.friend_name);
            friendCurrentMessage = (TextView) itemView.findViewById(R.id.friend_current_message);
            messageContainer = (LinearLayout) itemView.findViewById(R.id.messageContainer);
        }
    }

    class MessageTopViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout top_container;

        public MessageTopViewHolder(View itemView) {
            super(itemView);
            top_container = (LinearLayout) itemView.findViewById(R.id.top_container);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, MessageInfo messageInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
