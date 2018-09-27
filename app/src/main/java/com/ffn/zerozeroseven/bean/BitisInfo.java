package com.ffn.zerozeroseven.bean;

import java.util.ArrayList;
import java.util.List;

public class BitisInfo {
    /**
     * code : 0
     * data : {"total":354,"pageIndex":0,"totalPage":354,"pageSize":1,"items":[{"images":[],"userClazz":"噢噢噢哦哦","isLike":0,"userPhone":"17388933063","likeCount":0,"avatar":"http://www.lingling7.com/lingling7-res/image/20180830/1535606514187.png","userName":"汨罗刘德华","userId":90,"content":"哈哈考虑考虑","createTime":"2018-09-25 16:02:04","isAnonymity":0,"userCollege":"oooooo","messages":[{"toUid":90,"createTime":"2018-09-25 16:08:26","fromUid":356,"fromUname":"是洋妹妹哦","id":7,"content":"您的帖子写的很有水平大师"},{"toUid":356,"createTime":"2018-09-25 16:10:22","fromUid":90,"fromUname":"汨罗刘德华","id":3,"toUname":"是洋妹妹哦","content":"哈哈 你可以给我嘿嘿一下吗"},{"toUid":356,"createTime":"2018-09-25 16:10:42","fromUid":90,"fromUname":"汨罗刘德华","id":4,"toUname":"是洋妹妹哦","content":"哈哈 或者睡一下也可以"}],"id":1382}]}
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
         * total : 354
         * pageIndex : 0
         * totalPage : 354
         * pageSize : 1
         * items : [{"images":[],"userClazz":"噢噢噢哦哦","isLike":0,"userPhone":"17388933063","likeCount":0,"avatar":"http://www.lingling7.com/lingling7-res/image/20180830/1535606514187.png","userName":"汨罗刘德华","userId":90,"content":"哈哈考虑考虑","createTime":"2018-09-25 16:02:04","isAnonymity":0,"userCollege":"oooooo","messages":[{"toUid":90,"createTime":"2018-09-25 16:08:26","fromUid":356,"fromUname":"是洋妹妹哦","id":7,"content":"您的帖子写的很有水平大师"},{"toUid":356,"createTime":"2018-09-25 16:10:22","fromUid":90,"fromUname":"汨罗刘德华","id":3,"toUname":"是洋妹妹哦","content":"哈哈 你可以给我嘿嘿一下吗"},{"toUid":356,"createTime":"2018-09-25 16:10:42","fromUid":90,"fromUname":"汨罗刘德华","id":4,"toUname":"是洋妹妹哦","content":"哈哈 或者睡一下也可以"}],"id":1382}]
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

        public static class ItemsBean {
            /**
             * images : []
             * userClazz : 噢噢噢哦哦
             * isLike : 0
             * userPhone : 17388933063
             * likeCount : 0
             * avatar : http://www.lingling7.com/lingling7-res/image/20180830/1535606514187.png
             * userName : 汨罗刘德华
             * userId : 90
             * content : 哈哈考虑考虑
             * createTime : 2018-09-25 16:02:04
             * isAnonymity : 0
             * userCollege : oooooo
             * messages : [{"toUid":90,"createTime":"2018-09-25 16:08:26","fromUid":356,"fromUname":"是洋妹妹哦","id":7,"content":"您的帖子写的很有水平大师"},{"toUid":356,"createTime":"2018-09-25 16:10:22","fromUid":90,"fromUname":"汨罗刘德华","id":3,"toUname":"是洋妹妹哦","content":"哈哈 你可以给我嘿嘿一下吗"},{"toUid":356,"createTime":"2018-09-25 16:10:42","fromUid":90,"fromUname":"汨罗刘德华","id":4,"toUname":"是洋妹妹哦","content":"哈哈 或者睡一下也可以"}]
             * id : 1382
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
            private ArrayList<String> images;
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

            public ArrayList<String> getImages() {
                return images;
            }

            public void setImages(ArrayList<String> images) {
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
                 * createTime : 2018-09-25 16:08:26
                 * fromUid : 356
                 * fromUname : 是洋妹妹哦
                 * id : 7
                 * content : 您的帖子写的很有水平大师
                 * toUname : 是洋妹妹哦
                 */

                private int toUid;
                private String createTime;
                private int fromUid;
                private String fromUname;
                private int id;
                private String content;
                private String toUname;

                public boolean isMessage() {
                    return isMessage;
                }

                public void setMessage(boolean message) {
                    isMessage = message;
                }

                private boolean isMessage;

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
}
