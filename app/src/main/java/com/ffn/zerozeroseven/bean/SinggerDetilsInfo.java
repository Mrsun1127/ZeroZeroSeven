package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2018/3/3.
 */

public class SinggerDetilsInfo implements Serializable {
    /**
     * code : 0
     * data : {"userClazz":"17级5班","isLike":0,"postType":"01","userPhone":"15616460898","likeCount":1,"title":"继续继续继续好好想结婚的","userName":"陈魄","userId":33,"content":"喜欢你","createTime":"2018-03-03 17:21:07","isAnonymity":0,"userCollege":"软件","id":268,"status":0}
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
         * userClazz : 17级5班
         * isLike : 0
         * postType : 01
         * userPhone : 15616460898
         * likeCount : 1
         * title : 继续继续继续好好想结婚的
         * userName : 陈魄
         * userId : 33
         * content : 喜欢你
         * createTime : 2018-03-03 17:21:07
         * isAnonymity : 0
         * userCollege : 软件
         * id : 268
         * status : 0
         */

        private String userClazz;
        private int isLike;
        private String postType;
        private String userPhone;
        private int likeCount;
        private String title;
        private String userName;
        private int userId;
        private String content;
        private String createTime;
        private int isAnonymity;
        private String userCollege;
        private int id;
        private int status;

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

        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
    }
}
