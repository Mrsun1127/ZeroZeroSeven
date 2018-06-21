package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/3.
 */

public class GobuyInfo {
    /**
     * id : 1
     * functionName : QuerySchoolStore
     * parameters : {"schoolId":1}
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
         */
        private int userId;
        private int issuePrizeId;
        private String honerPoint;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getIssuePrizeId() {
            return issuePrizeId;
        }

        public void setIssuePrizeId(int issuePrizeId) {
            this.issuePrizeId = issuePrizeId;
        }

        public String getHonerPoint() {
            return honerPoint;
        }

        public void setHonerPoint(String honerPoint) {
            this.honerPoint = honerPoint;
        }
    }
}
