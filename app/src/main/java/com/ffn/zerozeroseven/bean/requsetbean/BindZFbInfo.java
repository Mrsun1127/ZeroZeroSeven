package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/2/3.
 */

public class BindZFbInfo {
    /**
     * id : 1
     * functionName : UpdateAlipayAccount
     * parameters : {"phone":"1","authcode":"验证码","alipayAccount":"支付宝账号"}
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
         * phone : 1
         * authcode : 验证码
         * alipayAccount : 支付宝账号
         */

        private String phone;
        private String authcode;
        private String alipayAccount;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAuthcode() {
            return authcode;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }
    }
}
