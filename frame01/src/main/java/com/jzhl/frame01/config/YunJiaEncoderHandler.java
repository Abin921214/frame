package com.jzhl.frame01.config;

import com.jzhl.frame01.common.callback.DecoderCallBack;
import com.jzhl.frame01.common.callback.EncoderCallBack;
import com.jzhl.frame01.common.netty.DecoderHandler;
import com.jzhl.frame01.common.netty.EncoderHandler;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * yun + 解码规则
 */
public class YunJiaEncoderHandler extends EncoderHandler implements EncoderCallBack {

    @Override
    public ChannelHandler encoderHandler() {
        ChannelHandler channelHandler = new StringEncoder(CharsetUtil.UTF_8);
        return channelHandler;
    }
}
