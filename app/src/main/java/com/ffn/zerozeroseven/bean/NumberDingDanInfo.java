package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class NumberDingDanInfo implements Serializable{
    /**
     * code : 0
     * data : {"total":1,"pageIndex":0,"totalPage":1,"pageSize":6,"list":[{"createTime":"2018-07-25 14:56:09","downPayment":60,"freightPrice":0,"goodsCount":1,"id":24,"isApplyRefund":true,"orderGoodsList":[{"costPrice":0,"goodsCount":1,"goodsId":3,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","goodsName":"111","id":7,"marketPrice":13999,"orderNo":"153250175965101","shopPrice":9999,"specKey":"9_11_15","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配"}],"orderNo":"153250175965101","orderPrice":9999,"orderStatus":1,"payStatus":1,"shippingStatus":0}]}
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

    public static class DataBean implements Serializable{
        /**
         * total : 1
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 6
         * list : [{"createTime":"2018-07-25 14:56:09","downPayment":60,"freightPrice":0,"goodsCount":1,"id":24,"isApplyRefund":true,"orderGoodsList":[{"costPrice":0,"goodsCount":1,"goodsId":3,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","goodsName":"111","id":7,"marketPrice":13999,"orderNo":"153250175965101","shopPrice":9999,"specKey":"9_11_15","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配"}],"orderNo":"153250175965101","orderPrice":9999,"orderStatus":1,"payStatus":1,"shippingStatus":0}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements  Serializable{
            /**
             * createTime : 2018-07-25 14:56:09
             * downPayment : 60
             * freightPrice : 0
             * goodsCount : 1
             * id : 24
             * isApplyRefund : true
             * orderGoodsList : [{"costPrice":0,"goodsCount":1,"goodsId":3,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","goodsName":"111","id":7,"marketPrice":13999,"orderNo":"153250175965101","shopPrice":9999,"specKey":"9_11_15","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配"}]
             * orderNo : 153250175965101
             * orderPrice : 9999
             * orderStatus : 1
             * payStatus : 1
             * shippingStatus : 0
             */

            private String createTime;
            private int downPayment;
            private Double freightPrice;
            private int goodsCount;
            private int id;
            private boolean isApplyRefund;
            private String orderNo;
            private Double orderPrice;
            private int orderStatus;
            private int payStatus;
            private int shippingStatus;
            private List<OrderGoodsListBean> orderGoodsList;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDownPayment() {
                return downPayment;
            }

            public void setDownPayment(int downPayment) {
                this.downPayment = downPayment;
            }

            public Double getFreightPrice() {
                return freightPrice;
            }

            public void setFreightPrice(Double freightPrice) {
                this.freightPrice = freightPrice;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIsApplyRefund() {
                return isApplyRefund;
            }

            public void setIsApplyRefund(boolean isApplyRefund) {
                this.isApplyRefund = isApplyRefund;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public Double getOrderPrice() {
                return orderPrice;
            }

            public void setOrderPrice(Double orderPrice) {
                this.orderPrice = orderPrice;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public int getShippingStatus() {
                return shippingStatus;
            }

            public void setShippingStatus(int shippingStatus) {
                this.shippingStatus = shippingStatus;
            }

            public List<OrderGoodsListBean> getOrderGoodsList() {
                return orderGoodsList;
            }

            public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
                this.orderGoodsList = orderGoodsList;
            }

            public static class OrderGoodsListBean implements Serializable{
                /**
                 * costPrice : 0
                 * goodsCount : 1
                 * goodsId : 3
                 * goodsImage : http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg
                 * goodsName : 111
                 * id : 7
                 * marketPrice : 13999
                 * orderNo : 153250175965101
                 * shopPrice : 9999
                 * specKey : 9_11_15
                 * specKeyName : 选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配
                 */

                private Double costPrice;
                private int goodsCount;
                private int goodsId;
                private String goodsImage;
                private String goodsName;
                private int id;
                private Double marketPrice;
                private String orderNo;
                private Double shopPrice;
                private String specKey;
                private String specKeyName;

                public Double getCostPrice() {
                    return costPrice;
                }

                public void setCostPrice(Double costPrice) {
                    this.costPrice = costPrice;
                }

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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Double getMarketPrice() {
                    return marketPrice;
                }

                public void setMarketPrice(Double marketPrice) {
                    this.marketPrice = marketPrice;
                }

                public String getOrderNo() {
                    return orderNo;
                }

                public void setOrderNo(String orderNo) {
                    this.orderNo = orderNo;
                }

                public Double getShopPrice() {
                    return shopPrice;
                }

                public void setShopPrice(Double shopPrice) {
                    this.shopPrice = shopPrice;
                }

                public String getSpecKey() {
                    return specKey;
                }

                public void setSpecKey(String specKey) {
                    this.specKey = specKey;
                }

                public String getSpecKeyName() {
                    return specKeyName;
                }

                public void setSpecKeyName(String specKeyName) {
                    this.specKeyName = specKeyName;
                }
            }
        }
    }
}
