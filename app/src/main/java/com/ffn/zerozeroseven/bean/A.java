package com.ffn.zerozeroseven.bean;

import java.util.List;

public class A {
    /**
     * code : 0
     * data : {"issue":1,"nickName":"mrsun","userContributionList":[{"createTime":"2018-06-28 14:35:27","userAvatar":"http://www.lingling7.com/lingling7-res/image/20180504/1525413361851.jpg","userPhone":"17388933063","issuePrizeId":28,"point":5200}],"pointPrize":{"prizeIssue":2,"prizePic":"http://192.168.0.199/lingling7-res/image/20180628/1530155862735.png","prizeType":"PHYSICAL","contributionPoint":5200,"lotteryCountdown":30,"prizePrice":30,"jackpotId":42,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","issuePrizeStatus":2,"lotteryTime":"2018-06-28 14:35:58","prizeIntro":"元素空间 夏季冰丝坐垫 夏天冰丝餐椅垫 记忆棉连体坐垫防滑透气办公室学生靠垫一体腰靠垫背","id":21,"issuePrizeId":28,"jackpotSort":2,"prizePoint":5200},"allUserContributionList":[{"createTime":"2018-06-28 14:35:27","userAvatar":"http://www.lingling7.com/lingling7-res/image/20180504/1525413361851.jpg","userPhone":"17388933063","issuePrizeId":28,"point":5200}],"lotteryTime":"2018-06-28 14:35:58","pointPrizeWinner":{"accept":false},"id":28,"prizeId":21,"status":2}
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
        /**
         * issue : 1
         * nickName : mrsun
         * userContributionList : [{"createTime":"2018-06-28 14:35:27","userAvatar":"http://www.lingling7.com/lingling7-res/image/20180504/1525413361851.jpg","userPhone":"17388933063","issuePrizeId":28,"point":5200}]
         * pointPrize : {"prizeIssue":2,"prizePic":"http://192.168.0.199/lingling7-res/image/20180628/1530155862735.png","prizeType":"PHYSICAL","contributionPoint":5200,"lotteryCountdown":30,"prizePrice":30,"jackpotId":42,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","issuePrizeStatus":2,"lotteryTime":"2018-06-28 14:35:58","prizeIntro":"元素空间 夏季冰丝坐垫 夏天冰丝餐椅垫 记忆棉连体坐垫防滑透气办公室学生靠垫一体腰靠垫背","id":21,"issuePrizeId":28,"jackpotSort":2,"prizePoint":5200}
         * allUserContributionList : [{"createTime":"2018-06-28 14:35:27","userAvatar":"http://www.lingling7.com/lingling7-res/image/20180504/1525413361851.jpg","userPhone":"17388933063","issuePrizeId":28,"point":5200}]
         * lotteryTime : 2018-06-28 14:35:58
         * pointPrizeWinner : {"accept":false}
         * id : 28
         * prizeId : 21
         * status : 2
         */

        private int issue;
        private String nickName;
        private PointPrizeBean pointPrize;
        private String lotteryTime;
        private PointPrizeWinnerBean pointPrizeWinner;
        private int id;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public static class PointPrizeBean {
            /**
             * prizeIssue : 2
             * prizePic : http://192.168.0.199/lingling7-res/image/20180628/1530155862735.png
             * prizeType : PHYSICAL
             * contributionPoint : 5200
             * lotteryCountdown : 30
             * prizePrice : 30
             * jackpotId : 42
             * prizeName : 夏季冰丝记忆棉坐垫办公室椅垫
             * issuePrizeStatus : 2
             * lotteryTime : 2018-06-28 14:35:58
             * prizeIntro : 元素空间 夏季冰丝坐垫 夏天冰丝餐椅垫 记忆棉连体坐垫防滑透气办公室学生靠垫一体腰靠垫背
             * id : 21
             * issuePrizeId : 28
             * jackpotSort : 2
             * prizePoint : 5200
             */

            private int prizeIssue;
            private String prizePic;
            private String prizeType;
            private int contributionPoint;
            private int lotteryCountdown;
            private int prizePrice;
            private int jackpotId;
            private String prizeName;
            private int issuePrizeStatus;
            private String lotteryTime;
            private String prizeIntro;
            private int id;
            private int issuePrizeId;
            private int jackpotSort;
            private int prizePoint;

            public int getPrizeIssue() {
                return prizeIssue;
            }

            public void setPrizeIssue(int prizeIssue) {
                this.prizeIssue = prizeIssue;
            }

            public String getPrizePic() {
                return prizePic;
            }

            public void setPrizePic(String prizePic) {
                this.prizePic = prizePic;
            }

            public String getPrizeType() {
                return prizeType;
            }

            public void setPrizeType(String prizeType) {
                this.prizeType = prizeType;
            }

            public int getContributionPoint() {
                return contributionPoint;
            }

            public void setContributionPoint(int contributionPoint) {
                this.contributionPoint = contributionPoint;
            }

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

            public int getIssuePrizeStatus() {
                return issuePrizeStatus;
            }

            public void setIssuePrizeStatus(int issuePrizeStatus) {
                this.issuePrizeStatus = issuePrizeStatus;
            }

            public String getLotteryTime() {
                return lotteryTime;
            }

            public void setLotteryTime(String lotteryTime) {
                this.lotteryTime = lotteryTime;
            }

            public String getPrizeIntro() {
                return prizeIntro;
            }

            public void setPrizeIntro(String prizeIntro) {
                this.prizeIntro = prizeIntro;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIssuePrizeId() {
                return issuePrizeId;
            }

            public void setIssuePrizeId(int issuePrizeId) {
                this.issuePrizeId = issuePrizeId;
            }

            public int getJackpotSort() {
                return jackpotSort;
            }

            public void setJackpotSort(int jackpotSort) {
                this.jackpotSort = jackpotSort;
            }

            public int getPrizePoint() {
                return prizePoint;
            }

            public void setPrizePoint(int prizePoint) {
                this.prizePoint = prizePoint;
            }
        }

        public static class PointPrizeWinnerBean {
            /**
             * accept : false
             */

            private boolean accept;

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
            }
        }

        public static class UserContributionListBean {
            /**
             * createTime : 2018-06-28 14:35:27
             * userAvatar : http://www.lingling7.com/lingling7-res/image/20180504/1525413361851.jpg
             * userPhone : 17388933063
             * issuePrizeId : 28
             * point : 5200
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

        public static class AllUserContributionListBean {
            /**
             * createTime : 2018-06-28 14:35:27
             * userAvatar : http://www.lingling7.com/lingling7-res/image/20180504/1525413361851.jpg
             * userPhone : 17388933063
             * issuePrizeId : 28
             * point : 5200
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
    }
}
