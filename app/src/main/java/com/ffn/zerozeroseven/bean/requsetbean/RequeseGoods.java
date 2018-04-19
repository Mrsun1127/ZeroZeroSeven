package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/3/2.
 */

public class RequeseGoods {
    /**
     * id : 1
     * functionName : QueryGoods
     * parameters : {"goodsId":64}
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
         * goodsId : 64
         */

        private int goodsId;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }
    }
}
