package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/18.
 */

public class LunXunInfo {
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
        private String schoolId;

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }
    }
}
