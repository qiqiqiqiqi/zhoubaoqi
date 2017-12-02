package com.qi.wechatclient.minacoder;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by feng on 2017/6/20.
 */

public class MinaCodecFactory implements ProtocolCodecFactory {
    MinaEncoder mMinaEncoder;
    MinaDecoder mMinaDecoder;
    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        mMinaEncoder=new MinaEncoder();
        return mMinaEncoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        mMinaDecoder=new MinaDecoder();
        return mMinaDecoder;
    }
}
