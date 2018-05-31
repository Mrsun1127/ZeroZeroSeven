package com.ffn.zerozeroseven.bean;

import java.util.List;

public class TongzhiInfo {
    /**
     * code : 0
     * data : {"list":[{"content":"dsfdgvfgbffffffffsdfscf","createTime":"2018-05-31 11:59:09","id":52,"link":"https://my.oschina.net/lizaizhong/blog/1814267","status":1,"title":"222222222222","type":"02"},{"content":"零零7温馨提示：童鞋们，最近下雨，天气冷，大家出行注意安全，注意保暖！！","createTime":"2018-05-31 11:45:14","id":51,"link":"1","status":1,"title":"零零7温馨提示：童鞋们，最近下雨，天气冷，大家出行注意安全，注意保暖！！","type":"02"}]}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content : dsfdgvfgbffffffffsdfscf
             * createTime : 2018-05-31 11:59:09
             * id : 52
             * link : https://my.oschina.net/lizaizhong/blog/1814267
             * status : 1
             * title : 222222222222
             * type : 02
             */

            private String content;
            private String createTime;
            private int id;
            private String link;
            private int status;
            private String title;
            private String type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
        }
    }
}
