package com.ffn.zerozeroseven.bean;

import java.util.List;

public class LeaseTabInfo {
    /**
     * code : 0
     * data : {"cateList":[{"cateName":"台式电脑","cateThumb":"","id":1,"sortOrder":0},{"cateName":"笔记本电脑","cateThumb":"","id":2,"sortOrder":0},{"cateName":"手机","cateThumb":"","id":3,"sortOrder":0}]}
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
        private List<CateListBean> cateList;

        public List<CateListBean> getCateList() {
            return cateList;
        }

        public void setCateList(List<CateListBean> cateList) {
            this.cateList = cateList;
        }

        public static class CateListBean {
            /**
             * cateName : 台式电脑
             * cateThumb :
             * id : 1
             * sortOrder : 0
             */

            private String cateName;
            private String cateThumb;
            private int id;
            private int sortOrder;

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public String getCateThumb() {
                return cateThumb;
            }

            public void setCateThumb(String cateThumb) {
                this.cateThumb = cateThumb;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
