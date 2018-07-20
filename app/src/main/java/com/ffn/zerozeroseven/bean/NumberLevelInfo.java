package com.ffn.zerozeroseven.bean;

import java.util.List;

public class NumberLevelInfo {

    /**
     * code : 0
     * data : {"categories":[{"id":2,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"手机","name":"手机","parentId":0,"sortOrder":0},{"id":23,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"3333333333333333","name":"333333333333","parentId":0,"sortOrder":0},{"id":22,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"平板","name":"平板","parentId":0,"sortOrder":0},{"id":21,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"小锅铲","name":"小锅铲","parentId":20,"sortOrder":0},{"id":19,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"家居","name":"家具","parentId":0,"sortOrder":0},{"id":16,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"服饰","name":"服饰","parentId":0,"sortOrder":0},{"id":1,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":1,"mobileName":"电脑","name":"电脑","parentId":0,"sortOrder":0},{"id":7,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"游戏手机","name":"游戏手机","parentId":2,"sortOrder":0},{"id":3,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"台式机","name":"台式机","parentId":1,"sortOrder":0},{"id":4,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"组装电脑","name":"组装电脑","parentId":1,"sortOrder":0},{"id":5,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"游戏本","name":"游戏本","parentId":1,"sortOrder":0},{"id":20,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"生活日用","name":"生活日用","parentId":19,"sortOrder":0},{"id":6,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"一体机","name":"一体机","parentId":1,"sortOrder":0},{"id":17,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"女装","name":"女装","parentId":16,"sortOrder":0},{"id":8,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"拍照手机","name":"拍照手机","parentId":2,"sortOrder":0},{"id":13,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"手机配件","name":"手机配件","parentId":2,"sortOrder":0},{"id":12,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"商务本","name":"商务本","parentId":1,"sortOrder":0},{"id":11,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"老人机","name":"老人机","parentId":2,"sortOrder":0},{"id":10,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"女性手机","name":"女性手机","parentId":2,"sortOrder":0},{"id":9,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":2,"mobileName":"全面屏手机","name":"全面屏手机","parentId":2,"sortOrder":0},{"id":15,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":3,"mobileName":"苹果数据线","name":"苹果数据线","parentId":13,"sortOrder":0},{"id":18,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":3,"mobileName":"套装","name":"套装","parentId":17,"sortOrder":0},{"id":14,"isHot":0,"isNew":0,"isRecommend":0,"isShow":1,"level":3,"mobileName":"手机壳","name":"手机壳","parentId":13,"sortOrder":0}]}
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
        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            /**
             * id : 2
             * isHot : 0
             * isNew : 0
             * isRecommend : 0
             * isShow : 1
             * level : 1
             * mobileName : 手机
             * name : 手机
             * parentId : 0
             * sortOrder : 0
             */

            private int id;
            private int isHot;
            private int isNew;
            private int isRecommend;
            private int isShow;
            private int level;
            private String mobileName;
            private String name;
            private int parentId;
            private int sortOrder;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsHot() {
                return isHot;
            }

            public void setIsHot(int isHot) {
                this.isHot = isHot;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public int getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getMobileName() {
                return mobileName;
            }

            public void setMobileName(String mobileName) {
                this.mobileName = mobileName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(int sortOrder) {
                this.sortOrder = sortOrder;
            }
        }
    }
}
