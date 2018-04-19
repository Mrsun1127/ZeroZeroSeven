package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/5.
 */

public class HistoryInfo implements Serializable {

    /**
     * code : 0
     * data : {"posts":[{"createTime":"2017-12-05 14:20:48","id":17,"title":"哈哈","content":"我爱你","enabled":0},{"createTime":"2017-12-03 14:45:12","postType":"表白贴","id":15,"title":"哈哈","content":"摸摸哦","enabled":1},{"createTime":"2017-12-02 19:55:53","postType":"表白贴","id":14,"title":"交朋友吧","content":"哈哈","enabled":0},{"createTime":"2017-12-01 12:24:41","postType":"表白贴","id":13,"title":"哈哈","content":"兔子","enabled":1},{"createTime":"2017-12-01 12:23:01","postType":"表白贴","id":12,"title":"我丢了一个女朋友","content":"挺漂亮的身材好","enabled":1},{"createTime":"2017-12-01 12:22:18","postType":"表白贴","id":11,"title":"八局","content":"咯莫","enabled":1},{"createTime":"2017-12-01 12:10:11","postType":"表白贴","id":10,"title":"额额额","content":"阿里","enabled":1},{"createTime":"2017-12-01 12:07:42","postType":"表白贴","id":9,"title":"垃龙","content":"通用录取","enabled":0},{"createTime":"2017-12-01 11:35:36","postType":"表白贴","id":8,"title":"标题","content":"内容","enabled":0},{"createTime":"2017-12-01 00:59:44","postType":"表白贴","id":7,"title":"标题","content":"内容","enabled":0}]}
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
        private List<PostsBean> posts;

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PostsBean implements Serializable{
            /**
             * createTime : 2017-12-05 14:20:48
             * id : 17
             * title : 哈哈
             * content : 我爱你
             * enabled : 0
             * postType : 表白贴
             */

            private String createTime;
            private int id;
            private String title;
            private String content;
            private String enabled;
            private String postType;
            private int status;
            public String getCreateTime() {
                return createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public String getPostType() {
                return postType;
            }

            public void setPostType(String postType) {
                this.postType = postType;
            }
        }
    }
}
