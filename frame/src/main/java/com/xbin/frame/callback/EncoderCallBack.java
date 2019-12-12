package com.xbin.frame.callback;

import io.netty.channel.ChannelHandler;

/**
 * 自定义编码器规则
 * @author xiaobin
 */
public interface EncoderCallBack {

    public ChannelHandler encoderHandler();

}
