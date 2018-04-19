package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/2/3.
 */

public class PushMessAgeInfo {
    /**
     * id : 1
     * functionName : ListPushNews
     * parameters : {"pageIndex":0,"pageSize":10}
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
         * pageIndex : 0
         * pageSize : 10
         */

        private int pageIndex;
        private int pageSize;

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }
}
