package com.jzhl.frame01.common.netty;

import com.jzhl.frame01.common.callback.NettyCallBack;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.net.InetSocketAddress;

public class NettyServerHandler<T extends NettyServerHandler>  extends SimpleChannelInboundHandler<Object> implements NettyCallBack {
    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    private ServletContext context;

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception{
        read(ctx, msg, getContext());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

//        System.out.println("channel 通道 Read 读取 Complete 完成");
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        log.info("cliet -- [ip:" + clientIp + "]-- online");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        System.out.println("------------------error-----------------------" + cause);
        ctx.close();
    }

    @Override
    public void read(ChannelHandlerContext ctx, Object msg, ServletContext context) throws Exception {

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
