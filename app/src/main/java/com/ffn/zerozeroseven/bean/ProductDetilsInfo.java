package com.ffn.zerozeroseven.bean;

import java.util.List;

public class ProductDetilsInfo {
    /**
     * code : 0
     * data : {"item":{"allUserContributionList":[{"createTime":"2018-05-30 14:37:23","issuePrizeId":1,"point":1,"userAvatar":"1","userPhone":"333333"},{"createTime":"2018-05-27 20:30:51","issuePrizeId":1,"point":222,"userAvatar":"1","userPhone":"13657494044"},{"createTime":"2018-05-27 20:30:15","issuePrizeId":1,"point":1,"userAvatar":"1","userPhone":"13657494044"}],"issue":1,"lotteryTime":"2018-05-28 13:09:45","pointPrize":{"contributionPoint":101,"id":1,"jackpotId":1,"jackpotSort":1,"lotteryCountdown":100,"prizeIntro":"黑鲨手机","prizeIssue":4,"prizeName":"手机111","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizePoint":2000,"prizePrice":3600},"pointPrizeWinner":{},"prizeId":1,"status":2,"userContributionList":[]}}
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
         * item : {"allUserContributionList":[{"createTime":"2018-05-30 14:37:23","issuePrizeId":1,"point":1,"userAvatar":"1","userPhone":"333333"},{"createTime":"2018-05-27 20:30:51","issuePrizeId":1,"point":222,"userAvatar":"1","userPhone":"13657494044"},{"createTime":"2018-05-27 20:30:15","issuePrizeId":1,"point":1,"userAvatar":"1","userPhone":"13657494044"}],"issue":1,"lotteryTime":"2018-05-28 13:09:45","pointPrize":{"contributionPoint":101,"id":1,"jackpotId":1,"jackpotSort":1,"lotteryCountdown":100,"prizeIntro":"黑鲨手机","prizeIssue":4,"prizeName":"手机111","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizePoint":2000,"prizePrice":3600},"pointPrizeWinner":{},"prizeId":1,"status":2,"userContributionList":[]}
         */

        private ItemBean item;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * allUserContributionList : [{"createTime":"2018-05-30 14:37:23","issuePrizeId":1,"point":1,"userAvatar":"1","userPhone":"333333"},{"createTime":"2018-05-27 20:30:51","issuePrizeId":1,"point":222,"userAvatar":"1","userPhone":"13657494044"},{"createTime":"2018-05-27 20:30:15","issuePrizeId":1,"point":1,"userAvatar":"1","userPhone":"13657494044"}]
             * issue : 1
             * lotteryTime : 2018-05-28 13:09:45
             * pointPrize : {"contributionPoint":101,"id":1,"jackpotId":1,"jackpotSort":1,"lotteryCountdown":100,"prizeIntro":"黑鲨手机","prizeIssue":4,"prizeName":"手机111","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizePoint":2000,"prizePrice":3600}
             * pointPrizeWinner : {}
             * prizeId : 1
             * status : 2
             * userContributionList : []
             */

            private int issue;
            private String lotteryTime;
            private PointPrizeBean pointPrize;
            private PointPrizeWinnerBean pointPrizeWinner;
            private int prizeId;
            private int status;
            private List<AllUserContributionListBean> allUserContributionList;
            private List<?> userContributionList;

            public int getIssue() {
                return issue;
            }

            public void setIssue(int issue) {
                this.issue = issue;
            }

            public String getLotteryTime() {
                return lotteryTime;
            }

            public void setLotteryTime(String lotteryTime) {
                this.lotteryTime = lotteryTime;
            }

            public PointPrizeBean getPointPrize() {
                return pointPrize;
            }

            public void setPointPrize(PointPrizeBean pointPrize) {
                this.pointPrize = pointPrize;
            }

            public PointPrizeWinnerBean getPointPrizeWinner() {
                return pointPrizeWinner;
            }

            public void setPointPrizeWinner(PointPrizeWinnerBean pointPrizeWinner) {
                this.pointPrizeWinner = pointPrizeWinner;
            }

            public int getPrizeId() {
                return prizeId;
            }

            public void setPrizeId(int prizeId) {
                this.prizeId = prizeId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<AllUserContributionListBean> getAllUserContributionList() {
                return allUserContributionList;
            }

            public void setAllUserContributionList(List<AllUserContributionListBean> allUserContributionList) {
                this.allUserContributionList = allUserContributionList;
            }

            public List<?> getUserContributionList() {
                return userContributionList;
            }

            public void setUserContributionList(List<?> userContributionList) {
                this.userContributionList = userContributionList;
            }

            public static class PointPrizeBean {
            }

            public static class PointPrizeWinnerBean {
            }

            public static class AllUserContributionListBean {
                /**
                 * createTime : 2018-05-30 14:37:23
                 * issuePrizeId : 1
                 * point : 1
                 * userAvatar : 1
                 * userPhone : 333333
                 */

                private String createTime;
                private int issuePrizeId;
                private int point;
                private String userAvatar;
                private String userPhone;

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getIssuePrizeId() {
                    return issuePrizeId;
                }

                public void setIssuePrizeId(int issuePrizeId) {
                    this.issuePrizeId = issuePrizeId;
                }

                public int getPoint() {
                    return point;
                }

                public void setPoint(int point) {
                    this.point = point;
                }

                public String getUserAvatar() {
                    return userAvatar;
                }

                public void setUserAvatar(String userAvatar) {
                    this.userAvatar = userAvatar;
                }

                public String getUserPhone() {
                    return userPhone;
                }

                public void setUserPhone(String userPhone) {
                    this.userPhone = userPhone;
                }
            }
        }
    }
}
