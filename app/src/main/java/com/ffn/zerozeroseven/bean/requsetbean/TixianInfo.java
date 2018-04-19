package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/2/5.
 */

public class TixianInfo {
    /**
     * id : 1
     * functionName : AlipayWithdrawalToAccount
     * parameters : {"amount":"提现金额（单位：元）"}
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
         * amount : 提现金额（单位：元）
         */

        private String amount;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
