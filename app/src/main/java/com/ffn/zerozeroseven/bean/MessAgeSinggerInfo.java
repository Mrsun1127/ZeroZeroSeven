package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2018/2/3.
 */

public class MessAgeSinggerInfo implements Serializable {
    /**
     * code : 0
     * data : {"summary":"龙哥尿床了","link":"龙哥尿床了fdsafd","id":4,"title":"龙哥尿床了","type":"01","content":"龙哥尿床了fdsafdsa","status":1}
     * message : 请求成功
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
         * summary : 龙哥尿床了
         * link : 龙哥尿床了fdsafd
         * id : 4
         * title : 龙哥尿床了
         * type : 01
         * content : 龙哥尿床了fdsafdsa
         * status : 1
         */

        private String summary;
        private String link;
        private int id;
        private String title;
        private String type;
        private String content;
        private int status;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
