package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/11/29.
 */

public class CodeInfo implements Serializable {
    /**
     * code : 0
     * data : {"authcode":"255441"}
     * message :
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

    public static class DataBean implements Serializable{
        /**
         * authcode : 255441
         */

        private String authcode;

        public String getAuthcode() {
            return authcode;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }
    }
}
