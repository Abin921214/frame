package com.xbin.frame.netty;

import com.xbin.frame.callback.NettyCallBack;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.net.InetSocketAddress;

/**
 * 实现netty handler 处理，让外部集成该类
 * @param <T> 泛型
 */
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
        try {
            read(ctx, msg, getContext());
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        try {
            readComplete(ctx, getContext());
            ctx.flush();
        }catch (Exception e){
            ctx.flush();
            throw e;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try {
            active(ctx, getContext());
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        try {
            exc(ctx, cause, getContext());
            ctx.close();
        }catch (Exception e){
            ctx.close();
        }
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
