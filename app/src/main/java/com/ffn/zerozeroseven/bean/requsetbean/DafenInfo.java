package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/1.
 */

public class DafenInfo {
    /**
     * id : 1
            * functionName : UpdatePostLike
     * parameters : {"userId":"2","postId":"2","event":"ADD"}
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
         * userId : 2
         * postId : 2
         * event : ADD
         */

        private String userId;
        private String postId;
        private String event;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }
}
