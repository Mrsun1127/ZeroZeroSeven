package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class RecentliyNewsInfo implements Serializable{
    /**
     * code : 0
     * data : {"total":19,"pageIndex":0,"totalPage":1,"pageSize":20,"list":[{"toUid":90,"createTime":"2018-09-25 16:08:26","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":7,"postId":1382,"content":"您的帖子写的很有水平大师"},{"toUid":90,"createTime":"2018-09-25 16:25:46","fromUid":92,"isView":1,"fromUname":"你们","id":8,"postId":1381,"content":"全部"},{"toUid":90,"createTime":"2018-09-26 14:47:41","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":13,"postId":1382,"content":"大神"},{"toUid":90,"createTime":"2018-09-26 14:49:08","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":15,"postId":1382,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 14:49:22","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":16,"postId":1381,"content":"你为什么一天评论都没有"},{"toUid":90,"createTime":"2018-09-26 14:55:56","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":18,"postId":1382,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 15:26:41","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":21,"postId":1382,"content":"嘻嘻"},{"toUid":90,"createTime":"2018-09-26 16:07:13","fromUid":92,"isView":1,"fromUname":"你们","id":24,"postId":1385,"content":"Qqeeee"},{"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":29,"postId":1385,"content":"嗯嗯"},{"toUid":90,"createTime":"2018-09-26 17:49:34","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":30,"postId":1381,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":31,"postId":1385,"content":"看看"},{"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":32,"postId":1385,"content":"看看"},{"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":33,"postId":1385,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 18:24:09","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":34,"postId":1385,"content":"爱你"},{"toUid":90,"createTime":"2018-09-26 18:55:14","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":35,"postId":1385,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 18:55:31","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":36,"postId":1385,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 18:55:39","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":37,"postId":1385,"content":"哈哈嘻嘻"},{"toUid":90,"createTime":"2018-09-27 10:15:38","fromUid":92,"isView":1,"fromUname":"你们","messageId":37,"id":25,"postId":1385,"toUname":"汨罗刘德华","content":"我的"},{"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"isView":0,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"postId":1385,"toUname":"汨罗刘德华","content":"牛逼"}]}
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

    public static class DataBean  implements Serializable{
        /**
         * total : 19
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 20
         * list : [{"toUid":90,"createTime":"2018-09-25 16:08:26","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":7,"postId":1382,"content":"您的帖子写的很有水平大师"},{"toUid":90,"createTime":"2018-09-25 16:25:46","fromUid":92,"isView":1,"fromUname":"你们","id":8,"postId":1381,"content":"全部"},{"toUid":90,"createTime":"2018-09-26 14:47:41","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":13,"postId":1382,"content":"大神"},{"toUid":90,"createTime":"2018-09-26 14:49:08","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":15,"postId":1382,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 14:49:22","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":16,"postId":1381,"content":"你为什么一天评论都没有"},{"toUid":90,"createTime":"2018-09-26 14:55:56","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":18,"postId":1382,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 15:26:41","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":21,"postId":1382,"content":"嘻嘻"},{"toUid":90,"createTime":"2018-09-26 16:07:13","fromUid":92,"isView":1,"fromUname":"你们","id":24,"postId":1385,"content":"Qqeeee"},{"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":29,"postId":1385,"content":"嗯嗯"},{"toUid":90,"createTime":"2018-09-26 17:49:34","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":30,"postId":1381,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":31,"postId":1385,"content":"看看"},{"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":32,"postId":1385,"content":"看看"},{"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":33,"postId":1385,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 18:24:09","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":34,"postId":1385,"content":"爱你"},{"toUid":90,"createTime":"2018-09-26 18:55:14","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","id":35,"postId":1385,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 18:55:31","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":36,"postId":1385,"content":"哈哈"},{"toUid":90,"createTime":"2018-09-26 18:55:39","fromUid":90,"isView":1,"fromUname":"汨罗刘德华","id":37,"postId":1385,"content":"哈哈嘻嘻"},{"toUid":90,"createTime":"2018-09-27 10:15:38","fromUid":92,"isView":1,"fromUname":"你们","messageId":37,"id":25,"postId":1385,"toUname":"汨罗刘德华","content":"我的"},{"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"isView":0,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"postId":1385,"toUname":"汨罗刘德华","content":"牛逼"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean  implements Serializable{
            /**
             * toUid : 90
             * createTime : 2018-09-25 16:08:26
             * fromUid : 356
             * isView : 1
             * fromUname : 是洋妹妹哦
             * id : 7
             * postId : 1382
             * content : 您的帖子写的很有水平大师
             * messageId : 37
             * toUname : 汨罗刘德华
             */

            private int toUid;
            private String createTime;
            private int fromUid;
            private int isView;
            private String fromUname;
            private int id;
            private int postId;
            private String content;
            private int messageId;
            private String toUname;

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

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public String getToUname() {
                return toUname;
            }

            public void setToUname(String toUname) {
                this.toUname = toUname;
            }
        }
    }
}
