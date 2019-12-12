package com.xbin.frame.netty;

import com.xbin.frame.callback.DecoderCallBack;
import io.netty.channel.ChannelHandler;

/**
 * 自定义解码器规则
 * @author xiaobin
 */
public class DecoderHandler implements DecoderCallBack {

    private ChannelHandler channelHandler;

    public ChannelHandler getChannelHandler() {
        return decoderHandler();
    }

    @Override
    public ChannelHandler decoderHandler() {
        return null;
    }
}
