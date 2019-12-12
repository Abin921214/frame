package com.jzhl.frame01.common.callback;

import io.netty.channel.ChannelHandlerContext;

import javax.servlet.ServletContext;

/**
 * netty 读取到设备的信息进行处理的接口
 * @author xiaobin
 */
public interface NettyCallBack {

    /**
     * 读取设备发送过来的信息
     * @param ctx
     * @param msg
     */
    public void read(ChannelHandlerContext ctx, Object msg, ServletContext context) throws Exception;

    /**
     * 通道[channel] 读取完成之后的操作
     * @param ctx
     */
    public void readComplete(ChannelHandlerContext ctx, ServletContext context)  throws Exception;

    /**
     * 设备连接上服务器是的反馈
     * @param ctx
     */
    public void active(ChannelHandlerContext ctx, ServletContext context)  throws Exception;

    /**
     * 连接异常时候的操作
     * @param ctx
     * @param cause
     */
    public void exc(ChannelHandlerContext ctx, Throwable cause, ServletContext context)  throws Exception;
}
