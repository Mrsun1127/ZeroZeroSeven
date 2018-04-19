package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/11/30.
 */

public class PoppurlarListInfo {
    /**
     * id : 1
     * functionName : ListPopularPost
     * parameters : {"pageSize":"20","pageIndex":"0"}
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
         * pageSize : 20
         * pageIndex : 0
         */

        private int pageSize;
        private int pageIndex;

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
