package com.ffn.zerozeroseven.bean;

import java.util.List;

public class ZhongJiangListInfo {
    /**
     * code : 0
     * data : {"pointPrizeWinnerList":[{"accept":true,"createTime":"2018-06-25 11:01:38","issue":1,"issuePrizeId":11,"prizeName":"洗衣机","prizePic":"https://f11.baidu.com/it/u=115911115,4065369961&fm=72"},{"accept":true,"createTime":"2018-06-25 11:01:43","issue":1,"issuePrizeId":12,"prizeName":"马赛拉蒂","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?"},{"accept":true,"createTime":"2018-06-25 11:01:51","issue":1,"issuePrizeId":13,"prizeName":"麻辣","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?"}]}
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
        private List<PointPrizeWinnerListBean> pointPrizeWinnerList;

        public List<PointPrizeWinnerListBean> getPointPrizeWinnerList() {
            return pointPrizeWinnerList;
        }

        public void setPointPrizeWinnerList(List<PointPrizeWinnerListBean> pointPrizeWinnerList) {
            this.pointPrizeWinnerList = pointPrizeWinnerList;
        }

        public static class PointPrizeWinnerListBean {
            /**
             * accept : true
             * createTime : 2018-06-25 11:01:38
             * issue : 1
             * issuePrizeId : 11
             * prizeName : 洗衣机
             * prizePic : https://f11.baidu.com/it/u=115911115,4065369961&fm=72
             */

            private boolean accept;
            private String createTime;
            private int issue;
            private int issuePrizeId;
            private String prizeName;
            private String prizePic;

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public String getPrizeName() {
                return prizeName;
            }

            public void setPrizeName(String prizeName) {
                this.prizeName = prizeName;
            }

            public String getPrizePic() {
                return prizePic;
            }

            public void setPrizePic(String prizePic) {
                this.prizePic = prizePic;
            }
        }
    }
}
