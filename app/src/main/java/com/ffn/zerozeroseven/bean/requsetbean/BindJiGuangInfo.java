package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/2/1.
 */

public class BindJiGuangInfo {
    /**
     * id : 1
     * functionName : AddUserDevice
     * parameters : {"userId":"用户id（选填）","clientId":"极光推送的registerId","platform":"平台：android / ios"}
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
         * userId : 用户id（选填）
         * clientId : 极光推送的registerId
         * platform : 平台：android / ios
         */

        private String userId;
        private String clientId;
        private String platform;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }
    }
}
