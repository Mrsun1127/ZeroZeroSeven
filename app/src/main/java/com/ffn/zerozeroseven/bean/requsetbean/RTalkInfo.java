package com.ffn.zerozeroseven.bean.requsetbean;



public class RTalkInfo {

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
        private String userPhone;
        private String orderNo;
        private String serviceEvaluation;
        private String speedStarCount;
        private String serviceStarCount;
        private String completeStarCount;
        private String remark;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getServiceEvaluation() {
            return serviceEvaluation;
        }

        public void setServiceEvaluation(String serviceEvaluation) {
            this.serviceEvaluation = serviceEvaluation;
        }

        public String getSpeedStarCount() {
            return speedStarCount;
        }

        public void setSpeedStarCount(String speedStarCount) {
            this.speedStarCount = speedStarCount;
        }

        public String getServiceStarCount() {
            return serviceStarCount;
        }

        public void setServiceStarCount(String serviceStarCount) {
            this.serviceStarCount = serviceStarCount;
        }

        public String getCompleteStarCount() {
            return completeStarCount;
        }

        public void setCompleteStarCount(String completeStarCount) {
            this.completeStarCount = completeStarCount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
