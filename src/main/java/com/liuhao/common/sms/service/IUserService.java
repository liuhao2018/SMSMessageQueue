package com.liuhao.common.sms.service;

public interface IUserService {

    void sendCode(String mobile);

    boolean verifyCode(String mobile,String code);

    int register(String mobile,String password);

}
