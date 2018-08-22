package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class MyDingDanShowInfo implements Serializable {

    /**
     * code : 0
     * data : {"total":1,"pageIndex":0,"totalPage":1,"pageSize":6,"orders":[{"orderNo":"151427291625107","createTime":"2017-12-26 15:22:13","totalPrice":0.03,"extraPrice":1,"details":[{"goodsCount":3,"goodsId":3,"goodsPrice":0.01,"goodsImage":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","goodsName":"苹果","goodsType":"01"}],"storeName":"湖南农业大学","id":291,"storeId":1,"totalCount":3,"status":1}]}
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
         * total : 1
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 6
         * orders : [{"orderNo":"151427291625107","createTime":"2017-12-26 15:22:13","totalPrice":0.03,"extraPrice":1,"details":[{"goodsCount":3,"goodsId":3,"goodsPrice":0.01,"goodsImage":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","goodsName":"苹果","goodsType":"01"}],"storeName":"湖南农业大学","id":291,"storeId":1,"totalCount":3,"status":1}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<OrdersBean> orders;

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

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean implements Serializable{
            /**
             * orderNo : 151427291625107
             * createTime : 2017-12-26 15:22:13
             * totalPrice : 0.03
             * extraPrice : 1
             * details : [{"goodsCount":3,"goodsId":3,"goodsPrice":0.01,"goodsImage":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","goodsName":"苹果","goodsType":"01"}]
             * storeName : 湖南农业大学
             * id : 291
             * storeId : 1
             * totalCount : 3
             * status : 1
             */

            private String orderNo;
            private String createTime;
            private Double totalPrice;
            private Double extraPrice;
            private String storeName;
            private int id;
            private int storeId;
            private int totalCount;
            private int status;
            private String orderCate;

            public String getOrderCate() {
                return orderCate;
            }

            public void setOrderCate(String orderCate) {
                this.orderCate = orderCate;
            }

            private List<DetailsBean> details;

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public Double getExtraPrice() {
                return extraPrice;
            }

            public void setExtraPrice(Double extraPrice) {
                this.extraPrice = extraPrice;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
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

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class DetailsBean implements Serializable{
                /**
                 * goodsCount : 3
                 * goodsId : 3
                 * goodsPrice : 0.01
                 * goodsImage : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg
                 * goodsName : 苹果
                 * goodsType : 01
                 */

                private int goodsCount;
                private int goodsId;
                private Double goodsPrice;
                private String goodsImage;
                private String goodsName;
                private String goodsType;

                public int getGoodsCount() {
                    return goodsCount;
                }

                public void setGoodsCount(int goodsCount) {
                    this.goodsCount = goodsCount;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public Double getGoodsPrice() {
                    return goodsPrice;
                }

                public void setGoodsPrice(Double goodsPrice) {
                    this.goodsPrice = goodsPrice;
                }

                public String getGoodsImage() {
                    return goodsImage;
                }

                public void setGoodsImage(String goodsImage) {
                    this.goodsImage = goodsImage;
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
}
