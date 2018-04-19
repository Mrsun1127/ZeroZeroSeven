package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/1/27.
 */

public class QiangRunRequsetInfo {
    /**
     * id : 1
     * functionName : QueryErrandOrder
     * parameters : {"id":"跑腿订单id"}
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
         * id : 跑腿订单id
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
