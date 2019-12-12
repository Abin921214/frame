package com.xbin.frame.service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.xbin.frame.bean.BaseResult;
import io.netty.handler.codec.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 极光推送 service
 * @author xiaobin
 */
public class JpushService {

    /**
     * 发送自定义推送，由APP端拦截信息后再决定是否创建通知(目前APP用此种方式)
     * @param appkey        极光配置信息中的 appKey
     * @param masterSecret  极光配置信息中的 appSecret
     * @param title      App通知栏标题
     * @param content    App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap  额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias      别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     * @return BaseResult
     * @throws Exception
     */
    public BaseResult sendCustomPush(String appkey, String masterSecret, String title, String content, Map<String, String> extrasMap, String... alias) throws Exception {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf("300000"));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient = new JPushClient(masterSecret, appkey, null, clientConfig);
        // 设置为消息推送方式为仅推送消息，不创建通知栏提醒
        PushPayload payload = buildCustomPushPayload(title, content, extrasMap, alias);
        /*PushResult result = null;*/
        try {
            /*result = jpushClient.sendPush(payload);*/
            jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            return new BaseResult(0,"连接错误, 稍后重试"  + "【" +  e.getMessage() + "】");
        } catch (APIRequestException e) {
            return new BaseResult(0,"极光服务器响应出错, 请修复" + "【" +  e.getErrorMessage() + "】");
        }
        return new BaseResult(1, "推送成功");
    }


    /**
     * 原生方式推送
     * @param appkey        极光配置信息中的 appKey
     * @param masterSecret  极光配置信息中的 appSecret
     * @param title         App通知栏标题
     * @param content       App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap     额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias         别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     * @return BaseResult
     * @throws Exception
     */
    public BaseResult sendPush(String appkey, String masterSecret, String title, String content, Map<String, String> extrasMap, String... alias) throws Exception{
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf("300000"));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient = new JPushClient(masterSecret, appkey, null, clientConfig);
        // 设置推送方式
        PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
        /*PushResult result = null;*/
        try {
            /*result = jpushClient.sendPush(payload);*/
            jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            return new BaseResult(0,"连接错误, 稍后重试"  + "【" +  e.getMessage() + "】");
        } catch (APIRequestException e) {
            return new BaseResult(0,"极光服务器响应出错, 请修复" + "【" +  e.getErrorMessage() + "】");
        }

        return new BaseResult(1, "推送成功");
    }


    /**
     * 异步请求推送方式
     * @param appkey        极光配置信息中的 appKey
     * @param masterSecret  极光配置信息中的 appSecret
     * @param title         通知栏标题
     * @param content       通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap     额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias         需接收的用户别名数组（为空则所有用户都推送）
     * @see **使用NettyHttpClient,异步接口发送请求，通过回调函数可以获取推送成功与否情况
     */

    public void sendPushWithCallback(String appkey, String masterSecret, String title, String content, Map<String, String> extrasMap, String... alias) {

        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf("300000"));
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(appkey, masterSecret), null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {

                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            // 需要手动关闭Netty请求进程,否则会一直保留
            client.close();
        }
    }



    /**
     * 构建Android和IOS的推送通知对象
     *
     * @return PushPayload
     */
    private PushPayload buildPushPayload(String title, String content, Map<String, String> extrasMap, String... alias) {
        if (extrasMap == null || extrasMap.isEmpty()) {
            extrasMap = new HashMap<String, String>();
        }
        // 批量删除数组中空元素
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                // 别名为空，全员推送；别名不为空，按别名推送
                .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
                .setNotification(Notification.newBuilder().setAlert(content)
                        .addPlatformNotification(
                                AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                        .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build())
                        .build())
                .build();
    }

    /**
     * 构建Android和IOS的自定义消息的推送通知对象
     * @return PushPayload
     */
    private PushPayload buildCustomPushPayload(String title, String content, Map<String, String> extrasMap,
                                               String... alias) {
        // 批量删除数组中空元素
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
                .setMessage(Message.newBuilder().setTitle(title).setMsgContent(content).addExtras(extrasMap).build())
                .build();
    }

    /**
     * 删除别名中的空元素（需删除如：null,""," "）
     *
     * @param strArray
     * @return String[]
     */
    private String[] removeArrayEmptyElement(String... strArray) {
        if (null == strArray || strArray.length == 0) {
            return null;
        }
        List<String> tempList = Arrays.asList(strArray);
        List<String> strList = new ArrayList<String>();
        Iterator<String> iterator = tempList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            // 消除空格后再做比较
            if (null != str && !"".equals(str.trim())) {
                strList.add(str);
            }
        }
        // 若仅输入"",则会将数组长度置为0
        String[] newStrArray = strList.toArray(new String[strList.size()]);
        return newStrArray;
    }
}
