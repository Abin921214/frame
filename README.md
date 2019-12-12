# frame
从今天开始整合自己用过的框架，无论是java, 小程序，android 等其他的技术，后期项目做到了或者学习到了都会记录在这里

## frame 框架简介
   结合springMVC + spring + mybaites + netty + (springColud可以根据项目需求再定是否使用 springColud服务端我用的是nacos) 用idea搭建的技术,主要是    基于gui 自动生成来实现的，基础的增删改查可以直接导入自动生成的就可以实现，不用自己去写相应的代码。自定义的就需要自己去书写了。大大提高了效率和节省时    间【同时这个框架实现了netty tcp 开发】

   frist: 从项目中下载 frame-xbin-1.0.jar 包导入到你的项目中
   
   second: 通过 mybatis-generator-gui-master 代码生成工具生成对用的 mapper model 以及对应的  mapper xml 文件
   
   third: 实现 controller 时，
          public class TestController extends ApiBaseControlller<SysUser, SysUserExample, SysUserService>  implements                               BaseOperation<SysUser, SysUserExample>
          【BaseOperation 里面包含了在实现对应接口是需要对数据的处理操作功能[根据自己项目的需求自定义验证或者操作数据]】
          注：此时实现了对某数据进行增删改查操作[接口风格 restful] ， 如果需要实现自定义api 的时候根据具体情况自定义操作
   four: 需要实现对应日志【已注解标记  @OperLog 通过切面处理类，操作日志异常日志记录处理@OperLog】
         


    对接netty tcp 操作【StringUtil 中包含了基本上对应设备需要的方法】：
    根据 frametest中的实例 --- 通过 @WebListener 实现对应某一端口进行监听同时 调用 NettyServer服务启动对应的netty服务
    启动netty 服务前需要做几个操作：
    
    frist: 实现 netty 需要操作的 Handler.
           例如： public class YunJiaHandler extends NettyServerHandler  implements NettyCallBack {}
           
    second: 实现编解码操作 -- 自定义编解码
            例如： 
            字符串编码规则
            public class YunJiaDecoderHandler extends DecoderHandler implements DecoderCallBack {

              @Override
              public ChannelHandler decoderHandler() {
                     ChannelHandler channelHandler = new StringDecoder(CharsetUtil.UTF_8);
                     return channelHandler;
               }
             }
             
             字符串解码规则
             public class YunJiaEncoderHandler extends EncoderHandler implements EncoderCallBack {

                   @Override
                   public ChannelHandler encoderHandler() {
                       ChannelHandler channelHandler = new StringEncoder(CharsetUtil.UTF_8);
                       return channelHandler;
                   }
               }
               
               
       third：监听上下文
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
                     NettyServer nettyServer = new NettyServer<YunJiaHandler, DecoderHandler, EncoderHandler>(new YunJiaHandler(), new                        DecoderHandler(), new EncoderHandler(), context);
                     nettyServer.run();
                     log.info("成功启动netty服务");
                 }
             }
         }
             
             
             
             
             
             
