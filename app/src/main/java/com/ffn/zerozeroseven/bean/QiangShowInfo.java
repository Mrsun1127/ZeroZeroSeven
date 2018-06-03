package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/1.
 */

public class QiangShowInfo implements Serializable {
    /**
     * code : 0
     * data : {"total":3,"pageIndex":0,"totalPage":1,"pageSize":10,"items":[{"userClazz":"","isLike":0,"createTime":"2017-12-03 17:08:52","isAnonymity":1,"postType":"01","userCollege":"","likeCount":8,"id":16,"title":"更广泛更好刚刚 v","userName":"4444","content":"估计很不错的"},{"userClazz":"1","isLike":0,"createTime":"2017-12-01 00:59:43","isAnonymity":1,"postType":"01","userCollege":"1","likeCount":9,"id":6,"title":"标题","userName":"1","content":"内容"},{"userClazz":"1","isLike":0,"createTime":"2017-11-29 15:06:34","isAnonymity":1,"postType":"01","userCollege":"1","likeCount":11,"id":1,"title":"1","content":"1"}]}
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
         * total : 3
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * items : [{"userClazz":"","isLike":0,"createTime":"2017-12-03 17:08:52","isAnonymity":1,"postType":"01","userCollege":"","likeCount":8,"id":16,"title":"更广泛更好刚刚 v","userName":"4444","content":"估计很不错的"},{"userClazz":"1","isLike":0,"createTime":"2017-12-01 00:59:43","isAnonymity":1,"postType":"01","userCollege":"1","likeCount":9,"id":6,"title":"标题","userName":"1","content":"内容"},{"userClazz":"1","isLike":0,"createTime":"2017-11-29 15:06:34","isAnonymity":1,"postType":"01","userCollege":"1","likeCount":11,"id":1,"title":"1","content":"1"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ItemsBean> items;

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

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean implements Serializable{
            /**
             * userClazz :
             * isLike : 0
             * createTime : 2017-12-03 17:08:52
             * isAnonymity : 1
             * postType : 01
             * userCollege :
             * likeCount : 8
             * id : 16
             * title : 更广泛更好刚刚 v
             * userName : 4444
             * content : 估计很不错的
             */

            private String userClazz;
            private int isLike;
            private String createTime;
            private int isAnonymity;
            private String postType;
            private String userCollege;
            private String likeCount;
            private int id;
            private String title;
            private String userName;
            private String content;
            private String avatar;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

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

            public String getPostType() {
                return postType;
            }

            public void setPostType(String postType) {
                this.postType = postType;
            }

            public String getUserCollege() {
                return userCollege;
            }

            public void setUserCollege(String userCollege) {
                this.userCollege = userCollege;
            }

            public String getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(String likeCount) {
                this.likeCount = likeCount;
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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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
