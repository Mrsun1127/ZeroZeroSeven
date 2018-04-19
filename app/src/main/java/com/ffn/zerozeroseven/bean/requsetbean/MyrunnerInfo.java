package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/1/26.
 */

public class MyrunnerInfo {
    /**
     * id : 1
     * functionName :
     * parameters : {"status":"0","pageSize":10,"pageIndex":0}
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
         * status : 0
         * pageSize : 10
         * pageIndex : 0
         */

        private String status;
        private int pageSize;
        private int pageIndex;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
}
