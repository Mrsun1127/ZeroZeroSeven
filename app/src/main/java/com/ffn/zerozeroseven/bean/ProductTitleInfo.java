package com.ffn.zerozeroseven.bean;

import java.util.List;

public class ProductTitleInfo {
    /**
     * code : 0
     * data : {"issues":[{"issue":1,"id":1},{"issue":2,"id":7},{"issue":3,"id":8},{"issue":4,"id":9}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<IssuesBean> issues;

        public List<IssuesBean> getIssues() {
            return issues;
        }

        public void setIssues(List<IssuesBean> issues) {
            this.issues = issues;
        }

        public static class IssuesBean {
            /**
             * issue : 1
             * id : 1
             */

            private int issue;
            private int id;

            public int getIssue() {
                return issue;
            }

            public void setIssue(int issue) {
                this.issue = issue;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
