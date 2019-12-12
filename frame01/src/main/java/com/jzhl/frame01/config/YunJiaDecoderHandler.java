package com.jzhl.frame01.config;

import com.jzhl.frame01.common.callback.DecoderCallBack;
import com.jzhl.frame01.common.netty.DecoderHandler;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * yun + 编码规则
 */
public class YunJiaDecoderHandler extends DecoderHandler implements DecoderCallBack {

    @Override
    public ChannelHandler decoderHandler() {
        ChannelHandler channelHandler = new StringDecoder(CharsetUtil.UTF_8);
        return channelHandler;
    }
}
