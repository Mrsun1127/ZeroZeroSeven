package com.ffn.zerozeroseven.bean;

import java.util.List;

public class BitisHistoryInfo {
    /**
     * code : 0
     * data : {"total":10,"pageIndex":0,"totalPage":2,"pageSize":6,"posts":[{"images":["http://www.lingling7.com/lingling7-res/image/20180926/1537948712677.jpeg","http://www.lingling7.com/lingling7-res/image/20180926/1537948712722.png","http://www.lingling7.com/lingling7-res/image/20180926/1537948712792.png"],"isLike":0,"createTime":"2018-09-26 15:58:32","messages":[{"isMessage":true,"toUid":90,"createTime":"2018-09-27 10:22:32","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":47,"content":"哦"},{"isMessage":false,"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"toUname":"汨罗刘德华","content":"牛逼"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:55:39","fromUid":90,"fromUname":"汨罗刘德华","replyList":[{"isMessage":false,"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"postId":1385,"toUname":"汨罗刘德华","content":"牛逼"}],"id":37,"content":"哈哈嘻嘻"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:55:14","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":35,"content":"哈哈"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:24:09","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":34,"content":"爱你"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":33,"content":"哈哈"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":32,"content":"看看"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":31,"content":"看看"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":29,"content":"嗯嗯"}],"likeCount":3,"id":1385,"userId":90,"content":"听说你敲代码很厉害？","status":0},{"images":[],"isLike":0,"createTime":"2018-07-17 15:45:16","postType":"表白贴","messages":[],"likeCount":1,"id":1293,"userId":90,"content":"聊了几句","status":1},{"images":[],"isLike":0,"createTime":"2018-07-17 15:44:51","postType":"表白贴","messages":[],"likeCount":0,"id":1292,"userId":90,"content":"hhh","status":0},{"images":[],"isLike":0,"createTime":"2018-07-06 15:03:13","postType":"表白贴","messages":[],"likeCount":4,"id":1274,"userId":90,"content":"才不会和人解释为什么今天喜欢这个明天喜欢那个。人是一堆无用的热情，盛放爱意的容器。风吹过去，在那头激起万物，荷尔蒙芳泽了大地。风没有原因，只有动心。就像我没有初心，我初心就是一时兴起见色起意。我们年轻人，永远年轻，永远色咪咪。","status":1},{"images":[],"isLike":0,"createTime":"2018-07-05 23:28:09","postType":"表白贴","messages":[],"likeCount":2,"id":1267,"userId":90,"content":"我是大哥","status":1},{"images":[],"isLike":0,"createTime":"2018-06-28 10:44:56","postType":"表白贴","messages":[],"likeCount":4,"id":1244,"title":"哦哦哦","userId":90,"content":"鲁迅写过\u201c我家门前有两棵树，一棵是枣树，另外一棵也是枣树。\u201d我在初中的时候\r\n，做过对这句话的阅读理解，要回答为什么不说两棵枣树而要分开说。有各种各样的答案，\r\n等我开始写作，认识了一堆作家，终于明白，即便是鲁迅本人回答这个问题，答案也是一\r\n样的：因为老子开心","status":1}]}
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

    public static class DataBean {
        /**
         * total : 10
         * pageIndex : 0
         * totalPage : 2
         * pageSize : 6
         * posts : [{"images":["http://www.lingling7.com/lingling7-res/image/20180926/1537948712677.jpeg","http://www.lingling7.com/lingling7-res/image/20180926/1537948712722.png","http://www.lingling7.com/lingling7-res/image/20180926/1537948712792.png"],"isLike":0,"createTime":"2018-09-26 15:58:32","messages":[{"isMessage":true,"toUid":90,"createTime":"2018-09-27 10:22:32","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":47,"content":"哦"},{"isMessage":false,"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"toUname":"汨罗刘德华","content":"牛逼"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:55:39","fromUid":90,"fromUname":"汨罗刘德华","replyList":[{"isMessage":false,"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"postId":1385,"toUname":"汨罗刘德华","content":"牛逼"}],"id":37,"content":"哈哈嘻嘻"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:55:14","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":35,"content":"哈哈"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:24:09","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":34,"content":"爱你"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":33,"content":"哈哈"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":32,"content":"看看"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":31,"content":"看看"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":29,"content":"嗯嗯"}],"likeCount":3,"id":1385,"userId":90,"content":"听说你敲代码很厉害？","status":0},{"images":[],"isLike":0,"createTime":"2018-07-17 15:45:16","postType":"表白贴","messages":[],"likeCount":1,"id":1293,"userId":90,"content":"聊了几句","status":1},{"images":[],"isLike":0,"createTime":"2018-07-17 15:44:51","postType":"表白贴","messages":[],"likeCount":0,"id":1292,"userId":90,"content":"hhh","status":0},{"images":[],"isLike":0,"createTime":"2018-07-06 15:03:13","postType":"表白贴","messages":[],"likeCount":4,"id":1274,"userId":90,"content":"才不会和人解释为什么今天喜欢这个明天喜欢那个。人是一堆无用的热情，盛放爱意的容器。风吹过去，在那头激起万物，荷尔蒙芳泽了大地。风没有原因，只有动心。就像我没有初心，我初心就是一时兴起见色起意。我们年轻人，永远年轻，永远色咪咪。","status":1},{"images":[],"isLike":0,"createTime":"2018-07-05 23:28:09","postType":"表白贴","messages":[],"likeCount":2,"id":1267,"userId":90,"content":"我是大哥","status":1},{"images":[],"isLike":0,"createTime":"2018-06-28 10:44:56","postType":"表白贴","messages":[],"likeCount":4,"id":1244,"title":"哦哦哦","userId":90,"content":"鲁迅写过\u201c我家门前有两棵树，一棵是枣树，另外一棵也是枣树。\u201d我在初中的时候\r\n，做过对这句话的阅读理解，要回答为什么不说两棵枣树而要分开说。有各种各样的答案，\r\n等我开始写作，认识了一堆作家，终于明白，即便是鲁迅本人回答这个问题，答案也是一\r\n样的：因为老子开心","status":1}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<PostsBean> posts;

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

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PostsBean {
            /**
             * images : ["http://www.lingling7.com/lingling7-res/image/20180926/1537948712677.jpeg","http://www.lingling7.com/lingling7-res/image/20180926/1537948712722.png","http://www.lingling7.com/lingling7-res/image/20180926/1537948712792.png"]
             * isLike : 0
             * createTime : 2018-09-26 15:58:32
             * messages : [{"isMessage":true,"toUid":90,"createTime":"2018-09-27 10:22:32","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":47,"content":"哦"},{"isMessage":false,"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"toUname":"汨罗刘德华","content":"牛逼"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:55:39","fromUid":90,"fromUname":"汨罗刘德华","replyList":[{"isMessage":false,"toUid":90,"createTime":"2018-09-27 10:19:02","fromUid":356,"isView":1,"fromUname":"是洋妹妹哦","messageId":37,"id":26,"postId":1385,"toUname":"汨罗刘德华","content":"牛逼"}],"id":37,"content":"哈哈嘻嘻"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:55:14","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":35,"content":"哈哈"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:24:09","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":34,"content":"爱你"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":33,"content":"哈哈"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":32,"content":"看看"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":31,"content":"看看"},{"isMessage":true,"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"fromUname":"是洋妹妹哦","replyList":[],"id":29,"content":"嗯嗯"}]
             * likeCount : 3
             * id : 1385
             * userId : 90
             * content : 听说你敲代码很厉害？
             * status : 0
             * postType : 表白贴
             * title : 哦哦哦
             */

            private int isLike;
            private String createTime;
            private int likeCount;
            private int id;
            private int userId;
            private String content;
            private int status;
            private String postType;
            private String title;
            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            private String userName;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            private String avatar;
            private List<String> images;
            private List<MessagesBean> messages;

            public int getIsLike() {
                return isLike;
            }

            public void setIsLike(int isLike) {
                this.isLike = isLike;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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

            public String getPostType() {
                return postType;
            }

            public void setPostType(String postType) {
                this.postType = postType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<MessagesBean> getMessages() {
                return messages;
            }

            public void setMessages(List<MessagesBean> messages) {
                this.messages = messages;
            }

            public static class MessagesBean {
                /**
                 * isMessage : true
                 * toUid : 90
                 * createTime : 2018-09-27 10:22:32
                 * fromUid : 356
                 * fromUname : 是洋妹妹哦
                 * replyList : []
                 * id : 47
                 * content : 哦
                 * messageId : 37
                 * toUname : 汨罗刘德华
                 */

                private boolean isMessage;
                private int toUid;
                private String createTime;
                private int fromUid;
                private String fromUname;
                private int id;
                private String content;
                private int messageId;
                private String toUname;
                private List<?> replyList;

                public boolean isIsMessage() {
                    return isMessage;
                }

                public void setIsMessage(boolean isMessage) {
                    this.isMessage = isMessage;
                }

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

                public List<?> getReplyList() {
                    return replyList;
                }

                public void setReplyList(List<?> replyList) {
                    this.replyList = replyList;
                }
            }
        }
    }
}
