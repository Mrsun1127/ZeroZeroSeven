package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/5.
 */

public class HistoryTieInfo {

    /**
     * id : 1
     * functionName : ListUserPost
     * parameters : {"userId":1,"pageSize":10,"pageIndex":0}
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
         * userId : 1
         * pageSize : 10
         * pageIndex : 0
         */

        private int userId;
        private int pageSize;
        private int pageIndex;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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
