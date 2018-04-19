package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/18.
 */

public class BecomeInfo {
    /**
     * id : 1
     * functionName : AddStoreApplication
     * parameters : {"applicantName":"申请人姓名","applicantPhone":"申请人手机号码","applicantSchool":"申请人学校"}
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
         * applicantName : 申请人姓名
         * applicantPhone : 申请人手机号码
         * applicantSchool : 申请人学校
         */

        private String applicantName;
        private String applicantPhone;
        private String applicantSchool;

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getApplicantPhone() {
            return applicantPhone;
        }

        public void setApplicantPhone(String applicantPhone) {
            this.applicantPhone = applicantPhone;
        }

        public String getApplicantSchool() {
            return applicantSchool;
        }

        public void setApplicantSchool(String applicantSchool) {
            this.applicantSchool = applicantSchool;
        }
    }
}
