package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/11/29.
 */

public class RegInfo {
    /**
     * id : 1
     * functionName : UserRegister
     * parameters : {"phone":"2","password":"2","authcode":2121}
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
         * phone : 2
         * password : 2
         * authcode : 2121
         */

        private String phone;
        private String password;
        private String authcode;

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        private String inviteCode;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAuthcode() {
            return authcode;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }
    }
}
