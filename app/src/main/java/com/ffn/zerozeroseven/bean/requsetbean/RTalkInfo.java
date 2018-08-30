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
        private int speedStarCount;
        private int serviceStarCount;
        private int completeStarCount;
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

        public int getSpeedStarCount() {
            return speedStarCount;
        }

        public void setSpeedStarCount(int speedStarCount) {
            this.speedStarCount = speedStarCount;
        }

        public int getServiceStarCount() {
            return serviceStarCount;
        }

        public void setServiceStarCount(int serviceStarCount) {
            this.serviceStarCount = serviceStarCount;
        }

        public int getCompleteStarCount() {
            return completeStarCount;
        }

        public void setCompleteStarCount(int completeStarCount) {
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
