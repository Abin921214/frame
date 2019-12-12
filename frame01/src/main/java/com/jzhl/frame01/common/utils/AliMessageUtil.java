package com.jzhl.frame01.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 *
 */
public class AliMessageUtil {
    // 产品名称:云通信短信API产品,开发者无需替换

    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";


    /**
     * 短信发送
     * @param accessKeyId       阿里云短信 accessKey
     * @param accessKeySecret   阿里云短信 accessKeySecret
     * @param mobile            手机号码
     * @param signName          阿里云短信签名
     * @param templeteCode      短信模板编码【阿里云那边创建对应的模板类型】
     * @param jsonParam         阿里云模板变量 JSONObject
     * @return SendSmsResponse
     * @throws Exception
     */
    public static SendSmsResponse sendSms(String accessKeyId, String accessKeySecret, String signName, String mobile, String templeteCode, JSONObject jsonParam) throws Exception{

        try {
            // 可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            // 初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();

            // 必填:待发送手机号
            request.setPhoneNumbers(mobile);
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            // 必填:短信模板-可在短信控制台中找到

            request.setTemplateCode(templeteCode);

            // 可选:模板中的变量替换JSON串,如模板内容为"尊敬的用户,您的验证码为${code}"时,此处的值为

            /*String jsonParam = "{\"code\":"+ code +",\"param1\":\""+ param1 +"\",\"param2\":\""+ param2 +"\",\"param3\":\""+ param3 +"\",\"param4\":\""+ param4 +"\",\"param5\":\""+ param5 +"\"}";*/

            request.setTemplateParam(jsonParam.toJSONString());

            // hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            return sendSmsResponse;
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 短信批量发送
     * @param templateCode 短信模板类型
     * @return {"Message":"OK","RequestId":"BDE08634-F15C-4EAC-B0C1-B6905A38E931","BizId":"725319161627967024^0","Code":"OK"}
     */

    /**
     * 群发短信
     * @param accessKeyId       阿里云短信 accessKey
     * @param accessKeySecret   阿里云短信 accessKeySecret
     * @param phoneList         手机号码 List<String>
     * @param signNameList      短信签名 List<String>
     * @param paramArray        每天短信的变量  JSONArray
     * @param templateCode      短信模板编码【阿里云那边创建对应的模板类型】
     * @return SendSmsResponse  注：短信签名  每天短信的变量 数量要和手机号码个数一致
     * @throws Exception
     */
    public static String  sendBatchSms(String accessKeyId, String accessKeySecret, List<String> phoneList,List<String> signNameList, JSONArray paramArray, String templateCode) throws Exception{

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        /*final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）*/

        //替换成你的AK
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setProduct(product);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendBatchSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumberJson",  ArrayUtils.toString(phoneList, ","));
        request.putQueryParameter("SignNameJson", ArrayUtils.toString(signNameList, ","));
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParamJson", paramArray.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            return null;
        }
    }

    /**
     * 获取某个号码在某个时间内的发送记录
     * @param accessKeyId       阿里云短信 accessKey
     * @param accessKeySecret   阿里云短信 accessKeySecret
     * @param phone             手机号码
     * @param bizId             发送回执ID
     * @param sendDate          发送时间-时间格式【yyyyMMdd】
     * @return  QuerySendDetailsResponse
     * @throws Exception
     */
    public static QuerySendDetailsResponse querySendDetails(String accessKeyId, String accessKeySecret, String phone, String bizId, String sendDate) throws Exception{

        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            /*//云通信产品-短信API服务产品名称（短信产品名固定，无需修改）
            final String product = "Dysmsapi";
            //云通信产品-短信API服务产品域名（接口地址固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";*/
            //初始化ascClient
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            QuerySendDetailsRequest request = new QuerySendDetailsRequest();
            //必填-号码
            request.setPhoneNumber(phone);
            //可选-调用发送短信接口时返回的BizId
            request.setBizId(bizId);
            //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
            request.setSendDate(sendDate);
            //必填-页大小
            request.setPageSize(10L);
            //必填-当前页码从1开始计数
            request.setCurrentPage(1L);
            //hint 此处可能会抛出异常，注意catch
            QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
            //获取返回结果
            return querySendDetailsResponse;
        }catch (ClientException e){
            throw e;
        }

    }

}
