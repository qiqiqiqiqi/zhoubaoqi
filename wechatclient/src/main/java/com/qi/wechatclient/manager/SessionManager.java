package com.qi.wechatclient.manager;

import com.qi.wechatclient.packet.PacketFromClient;
import com.qi.wechatclient.utils.LogUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

public class SessionManager {
    private static final String TAG = SessionManager.class.getSimpleName();
    private static SessionManager mInstance = null;
    private static final int WAIT_SERVER_RESPONSE_TIMES = 3;
    private IoSession mSession;

    public static SessionManager getInstance() {
        if (mInstance == null) {
            synchronized (SessionManager.class) {
                if (mInstance == null) {
                    mInstance = new SessionManager();
                }
            }
        }
        return mInstance;
    }

    private SessionManager() {
    }

    public void setSession(IoSession session) {
        this.mSession = session;
    }


    public void writeToServer(PacketFromClient packetFromClient) {
        writeToServer(packetFromClient, 0);
    }

    public void writeToServer(final PacketFromClient packetFromClient, final int repeatTimes) {
        LogUtil.d(TAG, "writeToServer():packetFromClient=" + packetFromClient + ",重发次数：repeatTimes=" + repeatTimes);
        if (mSession != null) {
            WriteFuture write = mSession.write(packetFromClient);
            write.addListener(new IoFutureListener<IoFuture>() {
                @Override
                public void operationComplete(IoFuture ioFuture) {
                    WriteFuture writeFuture = (WriteFuture) ioFuture;
                    if (writeFuture.isWritten()) {
                        return;
                    } else {//重发
                        if (repeatTimes < WAIT_SERVER_RESPONSE_TIMES) {
                            writeToServer(packetFromClient, repeatTimes + 1);
                        } else {
                            LogUtil.d(TAG, "writeToServer():repeatTimes=" + repeatTimes + ",结束重发");
                        }
                    }
                }
            });
        }

    }

    public void closeSession() {
        if (mSession != null) {
            mSession.closeOnFlush();
        }
    }

    public void removeSession() {
        this.mSession = null;
    }
}
