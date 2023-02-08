package com.example.second.exception;

public class ValidateException extends RuntimeException{
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ValidateException(String code, String msg) {
        super(msg);
        this.code = code; //错误编码：-1或者其他
        this.msg = msg; //错误信息
    }
}
