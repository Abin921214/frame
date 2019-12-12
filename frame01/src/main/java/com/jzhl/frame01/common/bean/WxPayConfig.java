package com.jzhl.frame01.common.bean;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * 引入 com.github.wxpay 调起微信支付 统一下单
 * 参考： https://blog.csdn.net/xb626492/article/details/103516772
 * WXPay wxpay = new WXPay(wxPayConfig)
 * wxpay.unifiedOrder(requestMap) 统一下单
 *         <dependency>
 *             <groupId>com.github.wxpay</groupId>
 *             <artifactId>wxpay-sdk</artifactId>
 *             <version>0.0.3</version>
 *         </dependency>
 */
public class WxPayConfig implements WXPayConfig {

    /**
     * appID
     */
    private String appID;

    /**
     * 商户号
     */
    private String mchID;

    /**
     * API 密钥
     */
    private String key;

    /**
     * API证书绝对路径 (本项目放在了 resources/cert/wxpay/apiclient_cert.p12")
     */
    private String certPath;

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     */
    private int httpConnectTimeoutMs = 8000;

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     */
    private int httpReadTimeoutMs = 10000;

    /**
     * 微信支付异步通知地址
     */
    private String payNotifyUrl;

    /**
     * 微信退款异步通知地址
     */
    private String refundNotifyUrl;

    /**
     * 微信支付参数配置
     * @param appID    appID
     * @param mchID    商户号
     * @param key      API 密钥
     * @param certPath API证书绝对路径 (本项目放在了 resources/cert/wxpay/apiclient_cert.p12")
     * @param payNotifyUrl  微信支付异步通知地址
     * @param refundNotifyUrl  微信退款异步通知地址
     */
    public WxPayConfig(String appID, String mchID, String key, String certPath, String payNotifyUrl, String refundNotifyUrl){
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
        this.certPath = certPath;
        this.payNotifyUrl = payNotifyUrl;
        this.refundNotifyUrl = refundNotifyUrl;
    }

    @Override
    public String getAppID() {
        return appID;
    }

    @Override
    public String getMchID() {
        return mchID;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        InputStream certStream  =getClass().getClassLoader().getResourceAsStream(certPath);
        return certStream;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }
}
