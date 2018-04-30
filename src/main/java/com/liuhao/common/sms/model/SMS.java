package com.liuhao.common.sms.model;

public class SMS {
    private String code;

    public SMS() {
    }

    public SMS(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "code='" + code + '\'' +
                '}';
    }
}
