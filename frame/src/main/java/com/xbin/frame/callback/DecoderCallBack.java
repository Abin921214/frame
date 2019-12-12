package com.xbin.frame.callback;

import io.netty.channel.ChannelHandler;

/**
 * 自定义解码器规则
 * @author xiaobin
 */
public interface DecoderCallBack {

    public ChannelHandler decoderHandler();

}
