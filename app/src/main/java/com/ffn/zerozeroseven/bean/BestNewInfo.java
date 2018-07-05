package com.ffn.zerozeroseven.bean;

import java.util.List;

public class BestNewInfo {
    /**
     * code : 0
     * data : {"jackpotPrizes":[{"contributionPoint":0,"jackpotId":1,"jackpotSort":1,"lotteryCountdown":30,"prizeIntro":"湘潭宾兰","prizeIssue":1,"prizeName":"冰蓝","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizePoint":20,"prizePrice":200},{"contributionPoint":0,"jackpotId":2,"jackpotSort":2,"lotteryCountdown":200,"prizeIntro":"机械革命电脑","prizeIssue":1,"prizeName":"电脑","prizePic":"http://img12.360buyimg.com/n1/s450x450_jfs/t19492/207/1948722005/158668/46bfdf4e/5adf1435N83459d1e.jpg","prizePoint":1000,"prizePrice":5400}]}
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
        private List<JackpotPrizesBean> jackpotPrizes;

        public List<JackpotPrizesBean> getJackpotPrizes() {
            return jackpotPrizes;
        }

        public void setJackpotPrizes(List<JackpotPrizesBean> jackpotPrizes) {
            this.jackpotPrizes = jackpotPrizes;
        }

        public static class JackpotPrizesBean {
            /**
             * contributionPoint : 0
             * jackpotId : 1
             * jackpotSort : 1
             * lotteryCountdown : 30
             * prizeIntro : 湘潭宾兰
             * prizeIssue : 1
             * prizeName : 冰蓝
             * prizePic : https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?
             * prizePoint : 20
             * prizePrice : 200
             */

            private int contributionPoint;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            private int id;
            private int jackpotId;
            private int jackpotSort;
            private int lotteryCountdown;
            private int countDownTime;
            private String prizeIntro;
            private int prizeIssue;
            private String prizeName;
            private String prizePic;
            private int prizePoint;
            private Double prizePrice;

            public int getCountDownTime() {
                return countDownTime;
            }

            public void setCountDownTime(int countDownTime) {
                this.countDownTime = countDownTime;
            }

            public int getContributionPoint() {
                return contributionPoint;
            }

            public void setContributionPoint(int contributionPoint) {
                this.contributionPoint = contributionPoint;
            }

            public int getJackpotId() {
                return jackpotId;
            }

            public void setJackpotId(int jackpotId) {
                this.jackpotId = jackpotId;
            }

            public int getJackpotSort() {
                return jackpotSort;
            }

            public void setJackpotSort(int jackpotSort) {
                this.jackpotSort = jackpotSort;
            }

            public int getLotteryCountdown() {
                return lotteryCountdown;
            }

            public void setLotteryCountdown(int lotteryCountdown) {
                this.lotteryCountdown = lotteryCountdown;
            }

            public String getPrizeIntro() {
                return prizeIntro;
            }

            public void setPrizeIntro(String prizeIntro) {
                this.prizeIntro = prizeIntro;
            }

            public int getPrizeIssue() {
                return prizeIssue;
            }

            public void setPrizeIssue(int prizeIssue) {
                this.prizeIssue = prizeIssue;
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

            public int getPrizePoint() {
                return prizePoint;
            }

            public void setPrizePoint(int prizePoint) {
                this.prizePoint = prizePoint;
            }

            public Double getPrizePrice() {
                return prizePrice;
            }

            public void setPrizePrice(Double prizePrice) {
                this.prizePrice = prizePrice;
            }
        }
    }
}
