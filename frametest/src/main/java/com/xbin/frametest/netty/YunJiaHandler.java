package com.xbin.frametest.netty;

import com.xbin.frame.callback.NettyCallBack;
import com.xbin.frame.netty.NettyServerHandler;
import com.xbin.frame.utils.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import javax.servlet.ServletContext;

public class YunJiaHandler extends NettyServerHandler implements NettyCallBack {
    @Override
    public void read(ChannelHandlerContext ctx, Object msg, ServletContext context) throws Exception {
        try {

            ByteBuf msg1 = (ByteBuf) msg;

            byte[] bytes = new byte[msg1.readableBytes()];
            msg1.readBytes(bytes);
            String date = StringUtil.byteToHexStr(bytes);
//
            System.out.println("date:" + date);
//
            String deviceName = StringUtil.hexStrToStr(date.substring(56,68));
            System.out.println("deviceName: " + deviceName);

            String data = "020056ff008a005ba000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000da03";

            //回复心跳
            StringUtil.writeToClient(data, ctx, "心跳");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readComplete(ChannelHandlerContext ctx, ServletContext context) throws Exception {

    }

    @Override
    public void active(ChannelHandlerContext ctx, ServletContext context) throws Exception {

    }

    @Override
    public void exc(ChannelHandlerContext ctx, Throwable cause, ServletContext context) throws Exception {

    }
}
