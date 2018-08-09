package com.ffn.zerozeroseven.bean;


import java.io.Serializable;

public class RrunnerPayInfo implements Serializable {

    /**
     * id : 1
     * functionName : QuerySchoolStore
     * parameters : {"schoolId":1}
     */

    private String id;
    private String functionName;
    private ParametersBean parameters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public ParametersBean getParameters() {
        return parameters;
    }

    public void setParameters(ParametersBean parameters) {
        this.parameters = parameters;
    }

    public static class ParametersBean implements Serializable {
        /**
         */
        private String userId;
        private String goodsType;
        private String goodsWeight;
        private String deliveryAddress;
        private String deliveryName;
        private String deliveryPhone;
        private String receiverAddress;
        private String receiverName;
        private String receiverPhone;
        private String shippingFee;
        private String remark;
        private String errandLevel;
        private String pickupTime;
        private String payment;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
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

        public String getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(String shippingFee) {
            this.shippingFee = shippingFee;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getErrandLevel() {
            return errandLevel;
        }

        public void setErrandLevel(String errandLevel) {
            this.errandLevel = errandLevel;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }
    }
}
