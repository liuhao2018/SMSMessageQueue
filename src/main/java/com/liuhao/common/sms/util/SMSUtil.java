package com.liuhao.common.sms.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import com.liuhao.common.sms.model.SMS;

public class SMSUtil {

    static final String product = "Dysmsapi";

    static final String domain = "dysmsapi.aliyuncs.com";

    public static SendSmsResponse sendSms(String targetMobile,SMS sms) throws ClientException {


        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");


        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAIvE9mhgD7umks", "Myxl3uDSvppPnovsQcMhPLsneG1KfA");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();

        request.setPhoneNumbers(targetMobile);

        request.setSignName("微服务调试");

        request.setTemplateCode("SMS_133966834");

        request.setTemplateParam(new Gson().toJson(sms));

        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sendSmsResponse;
    }

}
