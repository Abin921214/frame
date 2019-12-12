package com.jzhl.frame01.common.callback;

import io.netty.channel.ChannelHandler;

import java.nio.channels.Channel;

/**
 * 自定义解码器规则
 * @author xiaobin
 */
public interface DecoderCallBack {

    public ChannelHandler decoderHandler();

}
