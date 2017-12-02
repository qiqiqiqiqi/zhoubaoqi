package com.qi.wechatclient.model.request;

import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by feng on 2017/6/20.
 */

public class BaseRequest {
    private static final String TAG = BaseRequest.class.getSimpleName();

    public BaseRequest() {
        registerEvent(this);
    }

    /**
     * 注册事件
     *
     * @param baseRequest
     */
    protected void registerEvent(BaseRequest baseRequest) {
        synchronized (this) {
            if (!isRegisterEvent(baseRequest)) {
                EventBus.getDefault().register(baseRequest);
            }
        }
    }


    protected boolean isRegisterEvent(BaseRequest baseRequest) {
        if (baseRequest == null) {
            LogUtil.e(TAG, "isRegisterEvent()-request is null");
            return false;
        }
        synchronized (this) {
            return EventBus.getDefault().isRegistered(baseRequest);
        }
    }

    /**
     * 不建议注销事件，最好退出app时再注销。比如多个线程使用同一个event时，如果一个线程注销了event，那么其他线程接收不到event。
     *
     * @param baseRequest
     */
    protected void unregisterEvent(BaseRequest baseRequest) {
        synchronized (this) {
            if (isRegisterEvent(baseRequest)) {
                EventBus.getDefault().unregister(baseRequest);
            }
        }
    }

}
