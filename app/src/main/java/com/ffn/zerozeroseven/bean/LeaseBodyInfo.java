package com.ffn.zerozeroseven.bean;

import java.util.List;

public class LeaseBodyInfo {
    /**
     * code : 0
     * data : {"deliveryPhone":"7777777777777","receivingName":"杨洲","orderGoodsList":[{"goodsCount":1,"goodsId":1,"goodsPrice":300,"isSend":0,"goodsName":"电脑"}],"payTime":"2018-08-23 14:51:23","isDelete":0,"postscript":"白色","receivingAddress":"火车站","orderStatus":4,"isPrint":0,"userId":90,"shippingStatus":1,"goodsCount":1,"createTime":"2018-08-23 14:52:02","goodsPrice":200,"schoolId":1719,"payment":"ALIPAY","id":1,"shippingTime":"2018-08-24 17:18:39","deliveryName":"77777777777777","payStatus":1,"receivingPhone":"17388933063"}
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
         * deliveryPhone : 7777777777777
         * receivingName : 杨洲
         * orderGoodsList : [{"goodsCount":1,"goodsId":1,"goodsPrice":300,"isSend":0,"goodsName":"电脑"}]
         * payTime : 2018-08-23 14:51:23
         * isDelete : 0
         * postscript : 白色
         * receivingAddress : 火车站
         * orderStatus : 4
         * isPrint : 0
         * userId : 90
         * shippingStatus : 1
         * goodsCount : 1
         * createTime : 2018-08-23 14:52:02
         * goodsPrice : 200
         * schoolId : 1719
         * payment : ALIPAY
         * id : 1
         * shippingTime : 2018-08-24 17:18:39
         * deliveryName : 77777777777777
         * payStatus : 1
         * receivingPhone : 17388933063
         */

        private String deliveryPhone;
        private String receivingName;
        private String payTime;
        private int isDelete;
        private String postscript;
        private String receivingAddress;
        private int orderStatus;
        private int isPrint;
        private int userId;
        private int shippingStatus;
        private int goodsCount;
        private String createTime;
        private Double goodsPrice;
        private int schoolId;
        private String payment;
        private int id;
        private String shippingTime;
        private String deliveryName;
        private int payStatus;
        private String receivingPhone;
        private List<OrderGoodsListBean> orderGoodsList;

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public String getReceivingName() {
            return receivingName;
        }

        public void setReceivingName(String receivingName) {
            this.receivingName = receivingName;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getPostscript() {
            return postscript;
        }

        public void setPostscript(String postscript) {
            this.postscript = postscript;
        }

        public String getReceivingAddress() {
            return receivingAddress;
        }

        public void setReceivingAddress(String receivingAddress) {
            this.receivingAddress = receivingAddress;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getIsPrint() {
            return isPrint;
        }

        public void setIsPrint(int isPrint) {
            this.isPrint = isPrint;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(Double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
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

        public String getShippingTime() {
            return shippingTime;
        }

        public void setShippingTime(String shippingTime) {
            this.shippingTime = shippingTime;
        }

        public String getDeliveryName() {
            return deliveryName;
        }

        public void setDeliveryName(String deliveryName) {
            this.deliveryName = deliveryName;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getReceivingPhone() {
            return receivingPhone;
        }

        public void setReceivingPhone(String receivingPhone) {
            this.receivingPhone = receivingPhone;
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
             * goodsId : 1
             * goodsPrice : 300
             * isSend : 0
             * goodsName : 电脑
             */

            private int goodsCount;
            private int goodsId;
            private Double goodsPrice;
            private int isSend;
            private String goodsName;
            private String goodsThumb;

            public String getGoodsThumb() {
                return goodsThumb;
            }

            public void setGoodsThumb(String goodsThumb) {
                this.goodsThumb = goodsThumb;
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

            public Double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(Double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getIsSend() {
                return isSend;
            }

            public void setIsSend(int isSend) {
                this.isSend = isSend;
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
