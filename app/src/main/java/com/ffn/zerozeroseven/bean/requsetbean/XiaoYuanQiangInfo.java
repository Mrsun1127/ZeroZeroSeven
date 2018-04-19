package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/1.
 */

public class XiaoYuanQiangInfo {
    /**
     * id : 1
     * functionName : ListUserPost
     * parameters : {"userId":"2","postType":"POST_TYPE","pageSize":10,"pageIndex":0}
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
         * postType : POST_TYPE
         * pageSize : 10
         * pageIndex : 0
         */

        private String postType;
        private int pageSize;
        private int pageIndex;


        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
}
