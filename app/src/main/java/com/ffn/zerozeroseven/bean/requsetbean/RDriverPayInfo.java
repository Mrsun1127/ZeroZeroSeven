package com.ffn.zerozeroseven.bean.requsetbean;



public class RDriverPayInfo {

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
        private String drivingId;
        private String drivingName;
        private String classId;
        private String idcard;
        private String applicantName;
        private String applicantAddress;
        private String applicantPhone;
        private String totalPrice;

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

        public String getDrivingId() {
            return drivingId;
        }

        public void setDrivingId(String drivingId) {
            this.drivingId = drivingId;
        }

        public String getDrivingName() {
            return drivingName;
        }

        public void setDrivingName(String drivingName) {
            this.drivingName = drivingName;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getApplicantAddress() {
            return applicantAddress;
        }

        public void setApplicantAddress(String applicantAddress) {
            this.applicantAddress = applicantAddress;
        }

        public String getApplicantPhone() {
            return applicantPhone;
        }

        public void setApplicantPhone(String applicantPhone) {
            this.applicantPhone = applicantPhone;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
