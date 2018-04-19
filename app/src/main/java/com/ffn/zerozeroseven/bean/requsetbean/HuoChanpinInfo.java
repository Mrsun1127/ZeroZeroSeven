package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/2/5.
 */

public class HuoChanpinInfo {
    /**
     * id : 1
     * functionName : ListUserProduct
     * parameters : {"productType":"VIP"}
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
         * productType : VIP
         */

        private String productType;

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }
    }
}
