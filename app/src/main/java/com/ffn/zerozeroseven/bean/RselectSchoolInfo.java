package com.ffn.zerozeroseven.bean;



public class RselectSchoolInfo {

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
        private String schoolId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getEnrollmentYear() {
            return enrollmentYear;
        }

        public void setEnrollmentYear(String enrollmentYear) {
            this.enrollmentYear = enrollmentYear;
        }

        private String enrollmentYear;

    }
}
