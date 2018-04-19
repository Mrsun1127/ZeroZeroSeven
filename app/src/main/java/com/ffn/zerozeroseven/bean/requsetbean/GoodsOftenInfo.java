package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/6.
 */

public class GoodsOftenInfo {
    /**
     * id : 1
     * functionName : ListUserRegularPurchase
     * parameters : {"schoolId":1,"pageIndex":0,"pageSize":3}
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
         * schoolId : 1
         * pageIndex : 0
         * pageSize : 3
         */

        private int schoolId;
        private int pageIndex;
        private int pageSize;
        private String hotStartTime;
        private String hotEndTime;

        public String getHotStartTime() {
            return hotStartTime;
        }

        public void setHotStartTime(String hotStartTime) {
            this.hotStartTime = hotStartTime;
        }

        public String getHotEndTime() {
            return hotEndTime;
        }

        public void setHotEndTime(String hotEndTime) {
            this.hotEndTime = hotEndTime;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
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
