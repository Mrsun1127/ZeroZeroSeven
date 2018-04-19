package com.ffn.zerozeroseven.bean;

/**
 * Created by GT on 2017/11/30.
 */

public class ErrorCodeInfo {
    /**
     * code : -1
     * data : {}
     * message : 手机号13187654321的验证码处于有效状态，无需重新发送
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
    }
}
