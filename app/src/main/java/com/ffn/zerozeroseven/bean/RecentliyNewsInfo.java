package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class RecentliyNewsInfo implements Serializable{
    /**
     * code : 0
     * data : {"messages":[{"toUid":90,"createTime":"2018-09-26 16:07:13","fromUid":92,"isView":0,"fromUname":"你们","id":24,"postId":1385,"content":"Qqeeee"},{"toUid":90,"createTime":"2018-09-26 15:26:41","fromUid":90,"isView":0,"fromUname":"汨罗刘德华","id":21,"postId":1382,"content":"嘻嘻"},{"toUid":90,"createTime":"2018-09-26 14:55:56","fromUid":90,"isView":0,"fromUname":"汨罗刘德华","id":18,"postId":1382,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 14:49:22","fromUid":356,"isView":0,"fromUname":"是洋妹妹哦","id":16,"postId":1381,"content":"你为什么一天评论都没有"},{"toUid":90,"createTime":"2018-09-26 14:49:08","fromUid":356,"isView":0,"fromUname":"是洋妹妹哦","id":15,"postId":1382,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 14:47:41","fromUid":356,"isView":0,"fromUname":"是洋妹妹哦","id":13,"postId":1382,"content":"大神"},{"toUid":90,"createTime":"2018-09-25 16:25:46","fromUid":92,"isView":0,"fromUname":"你们","id":8,"postId":1381,"content":"全部"},{"toUid":90,"createTime":"2018-09-25 16:08:26","fromUid":356,"isView":0,"fromUname":"是洋妹妹哦","id":7,"postId":1382,"content":"您的帖子写的很有水平大师"}]}
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
        private List<MessagesBean> messages;

        public List<MessagesBean> getMessages() {
            return messages;
        }

        public void setMessages(List<MessagesBean> messages) {
            this.messages = messages;
        }

        public static class MessagesBean implements Serializable{
            /**
             * toUid : 90
             * createTime : 2018-09-26 16:07:13
             * fromUid : 92
             * isView : 0
             * fromUname : 你们
             * id : 24
             * postId : 1385
             * content : Qqeeee
             */

            private int toUid;
            private String createTime;
            private int fromUid;
            private int isView;
            private String fromUname;
            private int id;
            private int postId;
            private String content;

            public int getToUid() {
                return toUid;
            }

            public void setToUid(int toUid) {
                this.toUid = toUid;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getFromUid() {
                return fromUid;
            }

            public void setFromUid(int fromUid) {
                this.fromUid = fromUid;
            }

            public int getIsView() {
                return isView;
            }

            public void setIsView(int isView) {
                this.isView = isView;
            }

            public String getFromUname() {
                return fromUname;
            }

            public void setFromUname(String fromUname) {
                this.fromUname = fromUname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
