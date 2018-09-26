package com.ffn.zerozeroseven.bean;

import java.util.List;

public class BitisDetilsInfo {
    /**
     * code : 0
     * data : {"images":["http://www.lingling7.com/lingling7-res/image/20180926/1537948712677.jpeg","http://www.lingling7.com/lingling7-res/image/20180926/1537948712722.png","http://www.lingling7.com/lingling7-res/image/20180926/1537948712792.png"],"userClazz":"噢噢噢哦哦","isLike":1,"userPhone":"17388933063","likeCount":2,"avatar":"http://www.lingling7.com/lingling7-res/image/20180830/1535606514187.png","userName":"汨罗刘德华","userId":90,"content":"听说你敲代码很厉害？","createTime":"2018-09-26 15:58:32","isAnonymity":0,"userCollege":"oooooo","messages":[{"toUid":90,"createTime":"2018-09-26 16:07:13","fromUid":92,"fromUname":"你们","id":24,"content":"Qqeeee"},{"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"fromUname":"是洋妹妹哦","id":29,"content":"嗯嗯"},{"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"fromUname":"是洋妹妹哦","id":31,"content":"看看"},{"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"fromUname":"是洋妹妹哦","id":32,"content":"看看"},{"toUid":356,"createTime":"2018-09-26 18:14:53","fromUid":90,"fromUname":"汨罗刘德华","id":21,"toUname":"是洋妹妹哦","content":"垃圾"},{"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"fromUname":"是洋妹妹哦","id":33,"content":"哈哈"}],"id":1385,"status":1}
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
         * images : ["http://www.lingling7.com/lingling7-res/image/20180926/1537948712677.jpeg","http://www.lingling7.com/lingling7-res/image/20180926/1537948712722.png","http://www.lingling7.com/lingling7-res/image/20180926/1537948712792.png"]
         * userClazz : 噢噢噢哦哦
         * isLike : 1
         * userPhone : 17388933063
         * likeCount : 2
         * avatar : http://www.lingling7.com/lingling7-res/image/20180830/1535606514187.png
         * userName : 汨罗刘德华
         * userId : 90
         * content : 听说你敲代码很厉害？
         * createTime : 2018-09-26 15:58:32
         * isAnonymity : 0
         * userCollege : oooooo
         * messages : [{"toUid":90,"createTime":"2018-09-26 16:07:13","fromUid":92,"fromUname":"你们","id":24,"content":"Qqeeee"},{"toUid":90,"createTime":"2018-09-26 17:43:59","fromUid":356,"fromUname":"是洋妹妹哦","id":29,"content":"嗯嗯"},{"toUid":90,"createTime":"2018-09-26 17:51:12","fromUid":356,"fromUname":"是洋妹妹哦","id":31,"content":"看看"},{"toUid":90,"createTime":"2018-09-26 17:58:49","fromUid":356,"fromUname":"是洋妹妹哦","id":32,"content":"看看"},{"toUid":356,"createTime":"2018-09-26 18:14:53","fromUid":90,"fromUname":"汨罗刘德华","id":21,"toUname":"是洋妹妹哦","content":"垃圾"},{"toUid":90,"createTime":"2018-09-26 18:15:01","fromUid":356,"fromUname":"是洋妹妹哦","id":33,"content":"哈哈"}]
         * id : 1385
         * status : 1
         */

        private String userClazz;
        private int isLike;
        private String userPhone;
        private int likeCount;
        private String avatar;
        private String userName;
        private int userId;
        private String content;
        private String createTime;
        private int isAnonymity;
        private String userCollege;
        private int id;
        private int status;
        private List<String> images;
        private List<MessagesBean> messages;

        public String getUserClazz() {
            return userClazz;
        }

        public void setUserClazz(String userClazz) {
            this.userClazz = userClazz;
        }

        public int getIsLike() {
            return isLike;
        }

        public void setIsLike(int isLike) {
            this.isLike = isLike;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIsAnonymity() {
            return isAnonymity;
        }

        public void setIsAnonymity(int isAnonymity) {
            this.isAnonymity = isAnonymity;
        }

        public String getUserCollege() {
            return userCollege;
        }

        public void setUserCollege(String userCollege) {
            this.userCollege = userCollege;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
             * toUid : 90
             * createTime : 2018-09-26 16:07:13
             * fromUid : 92
             * fromUname : 你们
             * id : 24
             * content : Qqeeee
             * toUname : 是洋妹妹哦
             */

            private int toUid;
            private String createTime;
            private int fromUid;
            private String fromUname;
            private int id;
            private String content;
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

            public String getToUname() {
                return toUname;
            }

            public void setToUname(String toUname) {
                this.toUname = toUname;
            }
        }
    }
}
