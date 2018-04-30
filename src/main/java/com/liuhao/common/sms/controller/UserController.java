package com.liuhao.common.sms.controller;

import com.liuhao.common.sms.model.APIResponse;
import com.liuhao.common.sms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/send-code")
    public APIResponse sendCode(String mobile) {
        userService.sendCode(mobile);
        APIResponse response = new APIResponse();
        response.setStatusCode(200);
        response.setMessage("OK");
        return response;
    }

    @PostMapping("/register")
    public APIResponse register(String mobile, String password, String code) {
        APIResponse response = new APIResponse();
        boolean checkResult = userService.verifyCode(mobile,code);
        if (!checkResult) {
            response.setStatusCode(400);
            response.setMessage("注册失败，验证码错误");
            return response;
        }
        int result = userService.register(mobile,password);
        if (result > 0) {
            response.setStatusCode(200);
            response.setMessage("注册成功");
            return response;
        }
        response.setStatusCode(400);
        response.setMessage("注册失败");
        return response;
    }

}
