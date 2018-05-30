package com.ffn.zerozeroseven.bean.requsetbean;

public class InteraglSignInfo {

    /**
     * functionName : AddUserSignInPoint
     * parameters : {}
     */

    private String functionName;
    private ParametersBean parameters;

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
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
