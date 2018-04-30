package com.liuhao.common.sms.queue;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.liuhao.common.sms.model.SMS;
import com.liuhao.common.sms.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 为了方便演示，将业务工程和消息队列工程放在一起
 *
 */

@Component
public class SMSListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @JmsListener(destination = "sms")
    public void receiveMessage(String mobile) {
        String code = (int)((Math.random()*9+1)*100000) + "";
        try {
            SendSmsResponse sendSmsResponse = SMSUtil.sendSms(mobile,new SMS(code));
            if (sendSmsResponse != null) {
                if (sendSmsResponse.getCode().equals("OK")) {
                    redisTemplate.boundValueOps(mobile).set(code);
                    redisTemplate.expire(mobile,60 * 30, TimeUnit.SECONDS);
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
