package com.liuhao.common.sms.service.impl;

import com.liuhao.common.sms.mapper.UserMapper;
import com.liuhao.common.sms.service.IUserService;
import com.liuhao.common.sms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void sendCode(String mobile) {
        jmsMessagingTemplate.convertAndSend("sms",mobile);
    }

    @Override
    public boolean verifyCode(String mobile, String code) {
        String resultCode = (String) redisTemplate.boundValueOps(mobile).get();
        if (resultCode == null || resultCode.isEmpty()) {
            return false;
        }
        if (resultCode.equals(code)) {
            return true;
        }
        return false;
    }

    @Override
    public int register(String mobile, String password) {
        return userMapper.addOne(mobile, MD5Util.MD5Encode(password,"UTF-8"));
    }
}
