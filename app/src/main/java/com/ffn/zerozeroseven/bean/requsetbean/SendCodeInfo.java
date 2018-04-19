package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/11/29.
 */

public class SendCodeInfo {
    /**
     * id : 1
     * functionName : SendAuthcode
     * parameters : {"phone":"2","event":"REGISTER"}
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
         * event : REGISTER
         */

        private String phone;
        private String event;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }
}
