package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/11/29.
 */

public class CodeLoginInfo {
    /**
     * id : 1
     * functionName : UserAuthcodeLogin
     * parameters : {"phone":"17936459545","authcode":"2"}
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
         * phone : 17936459545
         * authcode : 2
         */

        private String phone;
        private String authcode;
        private String inviteCode;

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

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
    }
}
