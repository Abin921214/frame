package com.jzhl.frame01.config;


import com.jzhl.frame01.common.netty.DecoderHandler;
import com.jzhl.frame01.common.netty.EncoderHandler;
import com.jzhl.frame01.common.netty.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * netty服务监听器 . <br>
 * @author hkb
 */
@WebListener
public class NettyServerListener implements ServletContextListener {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Map<String, ChannelHandlerContext> map = new HashMap<>();

    ServletContext context;


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //获取上下文
        context = sce.getServletContext();

        context.setAttribute("ctx", map);

        log.info("开始启动netty服务");
        Thread thread = new Thread(new NettyServerThread());
        // 启动netty服务
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /**
     * netty服务启动线程 . <br>
     *
     * @author hkb
     */
    private class NettyServerThread implements Runnable {

        @Override
        public void run() {
            NettyServer nettyServer = new NettyServer<YunJiaHandler, DecoderHandler, EncoderHandler>(new YunJiaHandler(), new DecoderHandler(), new EncoderHandler(), context);
            nettyServer.run();
            log.info("成功启动netty服务");
        }
    }
}
