package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/6.
 */

public class GoodsDetilsInfo {
    /**
     * id : 1
     * functionName : QueryGoodsOrderDetail
     * parameters : {"schoolId":1,"orderId":61}
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
         * orderId : 61
         */

        private int schoolId;
        private int orderId;

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
    }
}
