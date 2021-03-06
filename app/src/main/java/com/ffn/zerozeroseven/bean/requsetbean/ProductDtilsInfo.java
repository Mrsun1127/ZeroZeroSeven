package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/3.
 */

public class ProductDtilsInfo {
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


        public int getIssuePrizeId() {
            return issuePrizeId;
        }

        public void setIssuePrizeId(int issuePrizeId) {
            this.issuePrizeId = issuePrizeId;
        }

        private int issuePrizeId;
        private String userPhone;

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }


    }
}
