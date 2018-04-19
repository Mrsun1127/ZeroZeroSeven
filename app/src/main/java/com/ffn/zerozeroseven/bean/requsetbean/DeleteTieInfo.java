package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/5.
 */

public class DeleteTieInfo {
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
        /**
         * postId : 1
         * userId : 1
         */

        private int postId;
        private int userId;

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
