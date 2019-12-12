package com.xbin.frame.netty;

import com.xbin.frame.callback.EncoderCallBack;
import io.netty.channel.ChannelHandler;

/**
 * 自定义编码器规则
 * @author xiaobin
 */
public class EncoderHandler implements EncoderCallBack {

    private ChannelHandler channelHandler;

    public ChannelHandler getChannelHandler() {
        return encoderHandler();
    }

    @Override
    public ChannelHandler encoderHandler() {
        return null;
    }
}
