package com.ffn.zerozeroseven.bean.requsetbean;

public class RleaseXiaDanInfo {
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
        private String userId;
        private String payment;
        private String orderInfoJson;
        private String orderGoodsJson;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
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
