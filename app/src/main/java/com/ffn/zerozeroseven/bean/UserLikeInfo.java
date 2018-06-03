package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/11/30.
 */

public class UserLikeInfo implements Serializable {
    public UserLikeInfo() {

    }

    public UserLikeInfo(int code, DataBean data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * code : 0
     * data : {"posts":[{"userClazz":"1","isAnonymity":0,"like":false,"userCollege":"1","title":"1"}]}
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
        public DataBean() {

        }

        public DataBean(List<PostsBean> posts) {
            this.posts = posts;
        }

        private List<PostsBean> posts;

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PostsBean implements Serializable{
            public PostsBean() {

            }

            public PostsBean(String userClazz, int isAnonymity, boolean like, String userCollege, String title,String content) {
                this.userClazz = userClazz;
                this.isAnonymity = isAnonymity;
                this.like = like;
                this.userCollege = userCollege;
                this.title = title;
                this.content = content;
            }

            /**
             * userClazz : 1
             * isAnonymity : 0
             * like : false
             * userCollege : 1
             * title : 1
             */

            private String userClazz;
            private int isAnonymity;
            private boolean like;
            private String userCollege;
            private String title;
            private int id;
            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserClazz() {
                return userClazz;
            }

            public void setUserClazz(String userClazz) {
                this.userClazz = userClazz;
            }

            public int getIsAnonymity() {
                return isAnonymity;
            }

            public void setIsAnonymity(int isAnonymity) {
                this.isAnonymity = isAnonymity;
            }

            public boolean isLike() {
                return like;
            }

            public void setLike(boolean like) {
                this.like = like;
            }

            public String getUserCollege() {
                return userCollege;
            }

            public void setUserCollege(String userCollege) {
                this.userCollege = userCollege;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
