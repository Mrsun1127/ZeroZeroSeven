package com.ffn.zerozeroseven.bean;

import java.util.List;

public class HotInfo {
    /**
     * code : 0
     * data : {"total":3,"pageIndex":0,"totalPage":1,"pageSize":6,"products":[{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180307/1520411393939.png","price":7.88,"id":793,"storeId":14,"goodsName":"叼嘴巴槟榔（有票无奖）","goodsType":"06"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180324/thumbnail1521877922423.jpg","price":3.78,"id":783,"storeId":14,"goodsName":"银鹭花生牛奶瓶装","goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180320/thumbnail1521527296253.jpg","price":4.98,"id":774,"storeId":14,"goodsName":"香飘飘奶茶原味80g装","goodsType":"05"}]}
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
         * total : 3
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 6
         * products : [{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180307/1520411393939.png","price":7.88,"id":793,"storeId":14,"goodsName":"叼嘴巴槟榔（有票无奖）","goodsType":"06"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180324/thumbnail1521877922423.jpg","price":3.78,"id":783,"storeId":14,"goodsName":"银鹭花生牛奶瓶装","goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180320/thumbnail1521527296253.jpg","price":4.98,"id":774,"storeId":14,"goodsName":"香飘飘奶茶原味80g装","goodsType":"05"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ProductsBean> products;

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

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * thumbnail : http://www.lingling7.com/lingling7-res/image/20180307/1520411393939.png
             * price : 7.88
             * id : 793
             * storeId : 14
             * goodsName : 叼嘴巴槟榔（有票无奖）
             * goodsType : 06
             */

            private String thumbnail;
            private Double price;
            private int id;
            private int storeId;
            private String goodsName;
            private String goodsType;
            private Double extraFee;

            public Double getExtraFee() {
                return extraFee;
            }

            public void setExtraFee(Double extraFee) {
                this.extraFee = extraFee;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }
        }
    }
}
