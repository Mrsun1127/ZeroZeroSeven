package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/12/25.
 */

public class UpdateIconInfo implements Serializable {
    /**
     * code : 0
     * data : {"url":"http://218.76.7.150:8080/middle-res/007/image/20171225/1514169774034.jpeg"}
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
         * url : http://218.76.7.150:8080/middle-res/007/image/20171225/1514169774034.jpeg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
