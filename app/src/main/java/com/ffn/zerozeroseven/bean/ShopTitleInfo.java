package com.ffn.zerozeroseven.bean;

import java.util.List;

public class ShopTitleInfo {
    /**
     * code : 0
     * data : {"goodsTypes":[{"id":10,"name":"root","sortOrder":0},{"id":11,"name":"服饰","sortOrder":0},{"id":12,"name":"郑迪","sortOrder":0}]}
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
        private List<GoodsTypesBean> goodsTypes;

        public List<GoodsTypesBean> getGoodsTypes() {
            return goodsTypes;
        }

        public void setGoodsTypes(List<GoodsTypesBean> goodsTypes) {
            this.goodsTypes = goodsTypes;
        }

        public static class GoodsTypesBean {
            /**
             * id : 10
             * name : root
             * sortOrder : 0
             */

            private int id;
            private String name;
            private String icon;
            private int sortOrder;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
