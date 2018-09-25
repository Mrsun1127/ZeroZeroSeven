package com.ffn.zerozeroseven.bean;

import java.util.List;

public class UrlInfo {
    /**
     * code : 0
     * data : {"urls":["http://www.lingling7.com/lingling7-res/image/20180925/1537861057499.jpeg","http://www.lingling7.com/lingling7-res/image/20180925/1537861057628.jpeg","http://www.lingling7.com/lingling7-res/image/20180925/1537861057738.jpeg"]}
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

    public static class DataBean {
        private List<String> urls;

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }
    }
}
