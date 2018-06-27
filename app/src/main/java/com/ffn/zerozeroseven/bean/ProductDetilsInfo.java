package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class ProductDetilsInfo implements Serializable {
    /**
     * code : 0
     * data : {"issue":1,"userContributionList":[],"pointPrize":{"lotteryCountdown":30000,"prizePrice":5400,"jackpotId":2,"prizeName":"电脑","prizePic":"http://img12.360buyimg.com/n1/s450x450_jfs/t19492/207/1948722005/158668/46bfdf4e/5adf1435N83459d1e.jpg","prizeIntro":"机械革命电脑","contributionPoint":0,"id":2,"prizePoint":1000},"allUserContributionList":[{"createTime":"2018-05-30 14:37:04","userAvatar":"1","userPhone":"222222","issuePrizeId":2,"point":1},{"createTime":"2018-05-30 14:36:52","userAvatar":"1","userPhone":"111111","issuePrizeId":2,"point":10},{"createTime":"2018-05-30 14:36:41","userAvatar":"1","userPhone":"444444","issuePrizeId":2,"point":22}],"lotteryTime":"2018-06-28 13:09:45","pointPrizeWinner":{"createTime":"2018-05-28 21:09:39","userAvatar":"131313","userPhone":"13657494044","issuePrizeId":2,"status":1},"prizeId":2,"status":2}
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

    public static class DataBean implements Serializable{
        /**
         * issue : 1
         * userContributionList : []
         * pointPrize : {"lotteryCountdown":30000,"prizePrice":5400,"jackpotId":2,"prizeName":"电脑","prizePic":"http://img12.360buyimg.com/n1/s450x450_jfs/t19492/207/1948722005/158668/46bfdf4e/5adf1435N83459d1e.jpg","prizeIntro":"机械革命电脑","contributionPoint":0,"id":2,"prizePoint":1000}
         * allUserContributionList : [{"createTime":"2018-05-30 14:37:04","userAvatar":"1","userPhone":"222222","issuePrizeId":2,"point":1},{"createTime":"2018-05-30 14:36:52","userAvatar":"1","userPhone":"111111","issuePrizeId":2,"point":10},{"createTime":"2018-05-30 14:36:41","userAvatar":"1","userPhone":"444444","issuePrizeId":2,"point":22}]
         * lotteryTime : 2018-06-28 13:09:45
         * pointPrizeWinner : {"createTime":"2018-05-28 21:09:39","userAvatar":"131313","userPhone":"13657494044","issuePrizeId":2,"status":1}
         * prizeId : 2
         * status : 2
         */

        private int issue;
        private PointPrizeBean pointPrize;
        private String lotteryTime;
        private PointPrizeWinnerBean pointPrizeWinner;
        private int prizeId;
        private int status;
        private List<UserContributionListBean> userContributionList;
        private List<AllUserContributionListBean> allUserContributionList;

        public int getIssue() {
            return issue;
        }

        public void setIssue(int issue) {
            this.issue = issue;
        }

        public PointPrizeBean getPointPrize() {
            return pointPrize;
        }

        public void setPointPrize(PointPrizeBean pointPrize) {
            this.pointPrize = pointPrize;
        }

        public String getLotteryTime() {
            return lotteryTime;
        }

        public void setLotteryTime(String lotteryTime) {
            this.lotteryTime = lotteryTime;
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

        public List<UserContributionListBean> getUserContributionList() {
            return userContributionList;
        }

        public void setUserContributionList(List<UserContributionListBean> userContributionList) {
            this.userContributionList = userContributionList;
        }

        public List<AllUserContributionListBean> getAllUserContributionList() {
            return allUserContributionList;
        }

        public void setAllUserContributionList(List<AllUserContributionListBean> allUserContributionList) {
            this.allUserContributionList = allUserContributionList;
        }

        public static class PointPrizeBean implements Serializable{
            /**
             * lotteryCountdown : 30000
             * prizePrice : 5400
             * jackpotId : 2
             * prizeName : 电脑
             * prizePic : http://img12.360buyimg.com/n1/s450x450_jfs/t19492/207/1948722005/158668/46bfdf4e/5adf1435N83459d1e.jpg
             * prizeIntro : 机械革命电脑
             * contributionPoint : 0
             * id : 2
             * prizePoint : 1000
             */

            private int lotteryCountdown;
            private int prizePrice;
            private int jackpotId;
            private String prizeName;
            private String prizePic;
            private String prizeIntro;
            private int contributionPoint;
            private int id;
            private int prizePoint;

            public int getLotteryCountdown() {
                return lotteryCountdown;
            }

            public void setLotteryCountdown(int lotteryCountdown) {
                this.lotteryCountdown = lotteryCountdown;
            }

            public int getPrizePrice() {
                return prizePrice;
            }

            public void setPrizePrice(int prizePrice) {
                this.prizePrice = prizePrice;
            }

            public int getJackpotId() {
                return jackpotId;
            }

            public void setJackpotId(int jackpotId) {
                this.jackpotId = jackpotId;
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

            public String getPrizeIntro() {
                return prizeIntro;
            }

            public void setPrizeIntro(String prizeIntro) {
                this.prizeIntro = prizeIntro;
            }

            public int getContributionPoint() {
                return contributionPoint;
            }

            public void setContributionPoint(int contributionPoint) {
                this.contributionPoint = contributionPoint;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPrizePoint() {
                return prizePoint;
            }

            public void setPrizePoint(int prizePoint) {
                this.prizePoint = prizePoint;
            }
        }

        public static class PointPrizeWinnerBean implements Serializable{
            /**
             * createTime : 2018-05-28 21:09:39
             * userAvatar : 131313
             * userPhone : 13657494044
             * issuePrizeId : 2
             * status : 1
             */

            private String createTime;
            private String userAvatar;
            private String userPhone;
            private int issuePrizeId;
            private int status;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public int getIssuePrizeId() {
                return issuePrizeId;
            }

            public void setIssuePrizeId(int issuePrizeId) {
                this.issuePrizeId = issuePrizeId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class AllUserContributionListBean implements Serializable{
            /**
             * createTime : 2018-05-30 14:37:04
             * userAvatar : 1
             * userPhone : 222222
             * issuePrizeId : 2
             * point : 1
             */

            private String createTime;
            private String userAvatar;
            private String userPhone;
            private int issuePrizeId;
            private int point;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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
        }
        public static class UserContributionListBean implements Serializable{
            private String createTime;
            private String userPhone;
            private String issuePrizeId;
            private String point;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getIssuePrizeId() {
                return issuePrizeId;
            }

            public void setIssuePrizeId(String issuePrizeId) {
                this.issuePrizeId = issuePrizeId;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }
        }
    }
}
