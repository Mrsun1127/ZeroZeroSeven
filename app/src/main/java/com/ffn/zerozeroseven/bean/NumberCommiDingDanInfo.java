package com.ffn.zerozeroseven.bean;



public class NumberCommiDingDanInfo {

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

    public static class ParametersBean {
        /**
         */
        private int userId;
        private String payment;
        private String payType;
        private String orderInfoJson;
        private String orderGoodsJson;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getOrderInfoJson() {
            return orderInfoJson;
        }

        public void setOrderInfoJson(String orderInfoJson) {
            this.orderInfoJson = orderInfoJson;
        }

        public String getOrderGoodsJson() {
            return orderGoodsJson;
        }

        public void setOrderGoodsJson(String orderGoodsJson) {
            this.orderGoodsJson = orderGoodsJson;
        }
    }
}
