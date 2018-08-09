package com.ffn.zerozeroseven.bean;

import java.util.List;

public class RunnerListInfo {
    /**
     * code : 0
     * data : {"errandOrders":[{"createTime":"2018-08-09 20:27:31","deliveryAddress":"郭村19","deliveryName":"一号","deliveryPhone":"17777777777","errandIncome":18,"errandLevel":5,"evaluation":5,"goodsType":"易碎品","goodsWeight":"1","id":1,"orderNo":"11111111","orderStatus":0,"payStatus":1,"payment":"AliPay","pickupTime":"2018-08-07 22:37:07","publisherId":90,"receiverAddress":"农大","receiverId":96,"receiverName":"湘江","receiverPhone":"17388933063","remark":"易碎品","schoolId":1719,"shippingFee":20}]}
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
             * createTime : 2018-08-09 20:27:31
             * deliveryAddress : 郭村19
             * deliveryName : 一号
             * deliveryPhone : 17777777777
             * errandIncome : 18
             * errandLevel : 5
             * evaluation : 5
             * goodsType : 易碎品
             * goodsWeight : 1
             * id : 1
             * orderNo : 11111111
             * orderStatus : 0
             * payStatus : 1
             * payment : AliPay
             * pickupTime : 2018-08-07 22:37:07
             * publisherId : 90
             * receiverAddress : 农大
             * receiverId : 96
             * receiverName : 湘江
             * receiverPhone : 17388933063
             * remark : 易碎品
             * schoolId : 1719
             * shippingFee : 20
             */

            private String createTime;
            private String deliveryAddress;
            private String deliveryName;
            private String deliveryPhone;
            private int errandIncome;
            private int errandLevel;
            private int evaluation;
            private String goodsType;
            private String goodsWeight;
            private int id;
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
            private String remark;
            private int schoolId;
            private Double shippingFee;

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

            public int getErrandIncome() {
                return errandIncome;
            }

            public void setErrandIncome(int errandIncome) {
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public Double getShippingFee() {
                return shippingFee;
            }

            public void setShippingFee(Double shippingFee) {
                this.shippingFee = shippingFee;
            }
        }
    }
}
