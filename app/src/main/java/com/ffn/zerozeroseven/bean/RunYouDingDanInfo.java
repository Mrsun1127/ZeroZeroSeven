package com.ffn.zerozeroseven.bean;

import java.util.List;

public class RunYouDingDanInfo {
    /**
     * code : 0
     * data : {"errandOrders":[{"createTime":"2018-08-10 17:11:19","deliveryAddress":"五一新村","deliveryName":"二号","deliveryPhone":"166666666666","errandIncome":18,"errandLevel":0,"evaluation":5,"goodsType":"抗摔","goodsWeight":"4","id":2,"isComment":0,"orderNo":"2222222222","orderStatus":1,"payStatus":1,"payment":"AliPay","pickupTime":"2018-08-06 17:11:07","publisherId":96,"receiverAddress":"涉外","receiverId":90,"receiverName":"涉外","receiverPhone":"17777777777","schoolId":1729,"shippingFee":20},{"createTime":"2018-08-10 17:13:29","deliveryAddress":"火车站","deliveryName":"三号","deliveryPhone":"17888888888888","errandIncome":18,"errandLevel":0,"evaluation":5,"goodsType":"抗摔","goodsWeight":"1","id":3,"isComment":0,"orderNo":"333333333333333","orderStatus":1,"payStatus":1,"payment":"AliPay","pickupTime":"2018-08-06 17:13:17","publisherId":96,"receiverAddress":"东湖","receiverId":90,"receiverName":"东湖","receiverPhone":"18888888888","schoolId":2613,"shippingFee":20}]}
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
        private List<ErrandOrdersBean> errandOrders;

        public List<ErrandOrdersBean> getErrandOrders() {
            return errandOrders;
        }

        public void setErrandOrders(List<ErrandOrdersBean> errandOrders) {
            this.errandOrders = errandOrders;
        }

        public static class ErrandOrdersBean {
            /**
             * createTime : 2018-08-10 17:11:19
             * deliveryAddress : 五一新村
             * deliveryName : 二号
             * deliveryPhone : 166666666666
             * errandIncome : 18.0
             * errandLevel : 0
             * evaluation : 5
             * goodsType : 抗摔
             * goodsWeight : 4
             * id : 2
             * isComment : 0
             * orderNo : 2222222222
             * orderStatus : 1
             * payStatus : 1
             * payment : AliPay
             * pickupTime : 2018-08-06 17:11:07
             * publisherId : 96
             * receiverAddress : 涉外
             * receiverId : 90
             * receiverName : 涉外
             * receiverPhone : 17777777777
             * schoolId : 1729
             * shippingFee : 20.0
             */

            private String createTime;
            private String deliveryAddress;
            private String deliveryName;
            private String deliveryPhone;
            private double errandIncome;
            private int errandLevel;
            private int evaluation;
            private String goodsType;
            private String goodsWeight;
            private int id;
            private int isComment;
            private String orderNo;
            private int orderStatus;
            private int payStatus;
            private String payment;
            private String pickupTime;
            private int publisherId;
            private String receiverAddress;
            private int receiverId;
            private String receiverName;
            private String receiverPhone;
            private int schoolId;
            private double shippingFee;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDeliveryAddress() {
                return deliveryAddress;
            }

            public void setDeliveryAddress(String deliveryAddress) {
                this.deliveryAddress = deliveryAddress;
            }

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

            public double getErrandIncome() {
                return errandIncome;
            }

            public void setErrandIncome(double errandIncome) {
                this.errandIncome = errandIncome;
            }

            public int getErrandLevel() {
                return errandLevel;
            }

            public void setErrandLevel(int errandLevel) {
                this.errandLevel = errandLevel;
            }

            public int getEvaluation() {
                return evaluation;
            }

            public void setEvaluation(int evaluation) {
                this.evaluation = evaluation;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public String getGoodsWeight() {
                return goodsWeight;
            }

            public void setGoodsWeight(String goodsWeight) {
                this.goodsWeight = goodsWeight;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
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

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getPickupTime() {
                return pickupTime;
            }

            public void setPickupTime(String pickupTime) {
                this.pickupTime = pickupTime;
            }

            public int getPublisherId() {
                return publisherId;
            }

            public void setPublisherId(int publisherId) {
                this.publisherId = publisherId;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public int getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(int receiverId) {
                this.receiverId = receiverId;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public double getShippingFee() {
                return shippingFee;
            }

            public void setShippingFee(double shippingFee) {
                this.shippingFee = shippingFee;
            }
        }
    }
}
