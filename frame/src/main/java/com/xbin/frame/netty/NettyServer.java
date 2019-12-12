package com.xbin.frame.netty;

import com.xbin.frame.base.OperLog;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;

public class NettyServer<Handler extends NettyServerHandler, Decoder extends DecoderHandler, Encoder extends EncoderHandler>{

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2; //默认

    protected static final int BIZTHREADSIZE = 4;

    private ServletContext context;

    private Handler handler;
    private Decoder decoder;
    private Encoder encoder;
    /**
     * 端口号
     */
    private int port;

    public NettyServer(Handler handler, Decoder decoder, Encoder encoder, ServletContext context, Integer port){
        handler.setContext(context);
        this.handler = handler;
        this.decoder = decoder;
        this.encoder = encoder;
        this.port = port;
    }

    /**
     * 启动服务器方法
     */
    @OperLog(operModul = "启动Netty服务器方法", operType = "run", operDesc = "启动Netty服务器方法")
    public void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
        EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.childHandler(new NettyServerInitializer<Handler, Decoder, Encoder>(handler, decoder, encoder));

            // 绑定端口,开始接收进来的连接
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 等待服务器socket关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
