package com.ffn.zerozeroseven.bean;

import java.util.List;

public class NumberDetlsInfo {

    /**
     * code : 0
     * data : {"orderGoodsList":[{"goodsCount":1,"marketPrice":13999,"orderNo":"153250175965101","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配","shopPrice":9999,"goodsId":3,"costPrice":0,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","id":7,"specKey":"9_11_15","goodsName":"苹果台式机"}],"orderNo":"153250175965101","postscript":"订单附言,由用户提交订单前填写","receiverName":"收获人姓名","orderStatus":1,"userId":93,"receiverAddress":"收获地址","shippingStatus":0,"goodsCount":1,"receiverPhone":"收获人手机号码","createTime":"2018-07-25 14:56:09","downPayment":60,"orderPrice":9999,"payment":"WechatPay","id":24,"freightPrice":0,"payStatus":1}
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
         * orderGoodsList : [{"goodsCount":1,"marketPrice":13999,"orderNo":"153250175965101","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配","shopPrice":9999,"goodsId":3,"costPrice":0,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","id":7,"specKey":"9_11_15","goodsName":"苹果台式机"}]
         * orderNo : 153250175965101
         * postscript : 订单附言,由用户提交订单前填写
         * receiverName : 收获人姓名
         * orderStatus : 1
         * userId : 93
         * receiverAddress : 收获地址
         * shippingStatus : 0
         * goodsCount : 1
         * receiverPhone : 收获人手机号码
         * createTime : 2018-07-25 14:56:09
         * downPayment : 60
         * orderPrice : 9999
         * payment : WechatPay
         * id : 24
         * freightPrice : 0
         * payStatus : 1
         */

        private String orderNo;
        private String postscript;
        private String receiverName;
        private int orderStatus;
        private int userId;
        private String receiverAddress;
        private int shippingStatus;
        private int goodsCount;
        private String receiverPhone;
        private String createTime;
        private int downPayment;
        private int orderPrice;
        private String payment;
        private String deliveryName;
        private String deliveryPhone;
        private String confirmTime;

        public String getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            this.confirmTime = confirmTime;
        }

        private int id;
        private int freightPrice;
        private int payStatus;
        private List<OrderGoodsListBean> orderGoodsList;

        public String getDeliveryName() {
            return deliveryName;
        }

        public void setDeliveryName(String deliveryName) {
            this.deliveryName = deliveryName;
        }

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPostscript() {
            return postscript;
        }

        public void setPostscript(String postscript) {
            this.postscript = postscript;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public int getShippingStatus() {
            return shippingStatus;
        }

        public void setShippingStatus(int shippingStatus) {
            this.shippingStatus = shippingStatus;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }

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

        public int getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(int orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFreightPrice() {
            return freightPrice;
        }

        public void setFreightPrice(int freightPrice) {
            this.freightPrice = freightPrice;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public List<OrderGoodsListBean> getOrderGoodsList() {
            return orderGoodsList;
        }

        public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
            this.orderGoodsList = orderGoodsList;
        }

        public static class OrderGoodsListBean {
            /**
             * goodsCount : 1
             * marketPrice : 13999
             * orderNo : 153250175965101
             * specKeyName : 选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配
             * shopPrice : 9999
             * goodsId : 3
             * costPrice : 0
             * goodsImage : http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg
             * id : 7
             * specKey : 9_11_15
             * goodsName : 苹果台式机
             */

            private int goodsCount;
            private Double marketPrice;
            private String orderNo;
            private String specKeyName;
            private Double shopPrice;
            private int goodsId;
            private Double costPrice;
            private String goodsImage;
            private int id;
            private String specKey;
            private String goodsName;

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
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

            public String getSpecKeyName() {
                return specKeyName;
            }

            public void setSpecKeyName(String specKeyName) {
                this.specKeyName = specKeyName;
            }

            public Double getShopPrice() {
                return shopPrice;
            }

            public void setShopPrice(Double shopPrice) {
                this.shopPrice = shopPrice;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public Double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(Double costPrice) {
                this.costPrice = costPrice;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSpecKey() {
                return specKey;
            }

            public void setSpecKey(String specKey) {
                this.specKey = specKey;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }
        }
    }
}
