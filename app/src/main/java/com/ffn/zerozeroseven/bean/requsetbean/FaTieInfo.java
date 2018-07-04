package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/1.
 */

public class FaTieInfo {

    /**
     * id : 1
     * functionName : UserPosting
     * parameters : {"userId":2,"title":"标题","content":"内容","postType":"POST_TYPE","isAnonymity":"0"}
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
         * title : 标题
         * content : 内容
         * postType : POST_TYPE
         * isAnonymity : 0
         */

        private int userId;
        private String content;
        private String postType;
        private String isAnonymity;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }



        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
        }

        public String getIsAnonymity() {
            return isAnonymity;
        }

        public void setIsAnonymity(String isAnonymity) {
            this.isAnonymity = isAnonymity;
        }
    }
}
