package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/8.
 */

public class SearchSchoolInfo {
    /**
     * id : 1
     * functionName : QuerySchoolByName
     * parameters : {"schoolName":"湖南信息职业技术学院"}
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
         * schoolName : 湖南信息职业技术学院
         */

        private String schoolName;

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
    }
}
