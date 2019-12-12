package com.jzhl.frame01.common.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;

public class NettyServerInitializer<Handler extends NettyServerHandler, Decoder extends DecoderHandler, Encoder extends EncoderHandler> extends ChannelInitializer<SocketChannel> {

    private Handler handler;
    private Decoder decoder;
    private Encoder encoder;

    public  NettyServerInitializer(Handler handler, Decoder decoder, Encoder encoder){
        this.handler = handler;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    /**
     * 初始化channel
     */
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if(decoder != null && decoder.getChannelHandler() != null){
            pipeline.addLast("decoder", decoder.getChannelHandler());
        }

        if(encoder != null && encoder.getChannelHandler() != null){
            pipeline.addLast("encoder",encoder.getChannelHandler());
        }

        pipeline.addLast(handler);
    }
}
