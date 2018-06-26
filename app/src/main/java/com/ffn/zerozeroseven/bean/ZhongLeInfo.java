package com.ffn.zerozeroseven.bean;

public class ZhongLeInfo {
    /**
     * code : 0
     * data : {"pointPrizeWinners":{"accept":true,"issue":1,"issuePrizeId":13}}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * pointPrizeWinners : {"accept":true,"issue":1,"issuePrizeId":13}
         */

        private PointPrizeWinnersBean pointPrizeWinners;

        public PointPrizeWinnersBean getPointPrizeWinners() {
            return pointPrizeWinners;
        }

        public void setPointPrizeWinners(PointPrizeWinnersBean pointPrizeWinners) {
            this.pointPrizeWinners = pointPrizeWinners;
        }

        public static class PointPrizeWinnersBean {
            /**
             * accept : true
             * issue : 1
             * issuePrizeId : 13
             */

            private boolean accept;
            private int issue;
            private int issuePrizeId;

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
            }

            public int getIssue() {
                return issue;
            }

            public void setIssue(int issue) {
                this.issue = issue;
            }

            public int getIssuePrizeId() {
                return issuePrizeId;
            }

            public void setIssuePrizeId(int issuePrizeId) {
                this.issuePrizeId = issuePrizeId;
            }
        }
    }
}
