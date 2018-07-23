package com.ffn.zerozeroseven.bean.requsetbean;

public class NumberHomeInfo {
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
        private int isFilter;

        public int getIsFilter() {
            return isFilter;
        }

        public void setIsFilter(int isFilter) {
            this.isFilter = isFilter;
        }
    }
}
