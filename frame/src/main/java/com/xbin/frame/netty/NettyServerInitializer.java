package com.xbin.frame.netty;

import com.xbin.frame.base.OperLog;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

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
    @OperLog(operModul = "netty编码解码处理以及添加handler处理", operType = "handler", operDesc = "netty编码解码处理以及添加handler处理")
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
