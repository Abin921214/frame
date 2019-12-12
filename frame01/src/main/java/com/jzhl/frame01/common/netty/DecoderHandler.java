package com.jzhl.frame01.common.netty;

import com.jzhl.frame01.common.callback.DecoderCallBack;
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
