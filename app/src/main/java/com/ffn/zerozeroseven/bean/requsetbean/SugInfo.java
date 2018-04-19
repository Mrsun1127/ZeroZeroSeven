package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/5.
 */

public class SugInfo {
    /**
     * id : 1
     * functionName : DeleteUserPost
     * parameters : {"postId":1,"userId":1}
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
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCourierPhone() {
            return courierPhone;
        }

        public void setCourierPhone(String courierPhone) {
            this.courierPhone = courierPhone;
        }

        /**
         * postId : 1
         * userId : 1
         */


        private String content;
        private String courierPhone;


    }
}
