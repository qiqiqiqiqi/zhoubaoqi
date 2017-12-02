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
import com.qi.wechatclient.utils.DisplayUtils;

import java.util.List;

/**
 * Created by feng on 2017/6/29.
 */

public class ContactsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Friend> mFriends;
    private View.OnClickListener mOnClickListener;
    private OnItemClickListener mOnItemClickListener;

    public ContactsAdapter(Context context, List<Friend> friends, View.OnClickListener onClickListener) {
        mContext = context;
        mFriends = friends;
        mOnClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, null);
            MessageViewHolder messageViewHolder = new MessageViewHolder(view);
            return messageViewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_contacts_top, null);
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
            final Friend friend = mFriends.get(position - 1);
            messageViewHolder.friendNick.setText(friend.getFriendName());
            messageViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position - 1, friend);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mFriends != null) {
            return mFriends.size() + 1;
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
        private TextView friendNick;
        private LinearLayout itemContainer;

        public MessageViewHolder(View itemView) {
            super(itemView);
            itemContainer = (LinearLayout) itemView.findViewById(R.id.item_container);
            friendHead = (ImageView) itemView.findViewById(R.id.friend_head);
            friendNick = (TextView) itemView.findViewById(R.id.friend_nick);
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
        void onItemClick(int positon, Friend friend);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;

    }
}
