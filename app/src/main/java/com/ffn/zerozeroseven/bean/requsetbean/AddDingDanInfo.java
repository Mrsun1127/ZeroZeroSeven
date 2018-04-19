package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/4.
 */

public class AddDingDanInfo {
    /**
     * id : 1
     * functionName : AddGoodsOrder
     * parameters : {"storeId":2,"receiverAddress":"receiverAddress","receiverName":"receiverName","receiverPhone":"receiverPhone","payment":"WechatPay","tradeType":"APP","goodsJsonString":"[{\"goodsCount\":5,\"goodsId\":1}]"}
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

    public static class ParametersBean {
        /**
         * storeId : 2
         * receiverAddress : receiverAddress
         * receiverName : receiverName
         * receiverPhone : receiverPhone
         * payment : WechatPay
         * tradeType : APP
         * goodsJsonString : [{"goodsCount":5,"goodsId":1}]
         */

        private int storeId;
        private String receiverAddress;
        private String receiverName;
        private String receiverPhone;
        private String payment;
        private String tradeType;
        private String goodsJsonString;
        private int buildingId;

        public int getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(int buildingId) {
            this.buildingId = buildingId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
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

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public String getGoodsJsonString() {
            return goodsJsonString;
        }

        public void setGoodsJsonString(String goodsJsonString) {
            this.goodsJsonString = goodsJsonString;
        }
    }
}
