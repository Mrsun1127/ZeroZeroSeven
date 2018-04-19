package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/14.
 */

public class PeiSongInfo {
    /**
     * id : 1
     * functionName : ListCourierGoodsOrder
     * parameters : {"courierId":2,"status":"5","pageIndex":0,"pageSize":10}
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
         * courierId : 2
         * status : 5
         * pageIndex : 0
         * pageSize : 10
         */

        private int courierId;
        private String status;
        private int pageIndex;
        private int pageSize;

        public int getCourierId() {
            return courierId;
        }

        public void setCourierId(int courierId) {
            this.courierId = courierId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

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
