package com.ffn.zerozeroseven.bean.requsetbean;



public class RBitisDeitlsInfo {

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
        private int userId;
        private int id;
        private int messageId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }
    }
}
