package com.ffn.zerozeroseven.bean;

import java.util.List;

public class JiangChiInfo {
    /**
     * code : 0
     * data : {"jackpotPrizes":[{"prizeIssue":1,"prizePic":"http://192.168.0.199/lingling7-res/image/20180625/1529917683192.jpg","prizeType":"PHYSICAL","contributionPoint":0,"lotteryCountdown":30,"prizePrice":50,"jackpotId":33,"prizeName":"GranTurismo","prizeIntro":"GranTurismo\n非凡跑车，源自经典\nGranTurismo的每一处细节都堪称意大利设计的杰出典范","id":13,"issuePrizeId":16,"jackpotSort":0,"prizePoint":50},{"lotteryCountdown":20000,"prizePrice":200,"jackpotId":3,"prizeIssue":1,"prizeName":"车222","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizeIntro":"湘潭宾兰","contributionPoint":0,"id":4,"jackpotSort":0,"prizePoint":20},{"lotteryCountdown":30,"prizePrice":10,"jackpotId":4,"prizeIssue":1,"prizeName":"耳机333","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizeIntro":"bbbbbbbbbb","contributionPoint":0,"id":5,"jackpotSort":0,"prizePoint":1000},{"lotteryCountdown":30,"prizePrice":20,"jackpotId":5,"prizeIssue":1,"prizeName":"冰箱444","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizeIntro":"cccccccccccccc","contributionPoint":0,"id":6,"jackpotSort":0,"prizePoint":20},{"lotteryCountdown":30000,"prizePrice":5400,"jackpotId":2,"prizeIssue":2,"prizeName":"电脑","prizePic":"http://img12.360buyimg.com/n1/s450x450_jfs/t19492/207/1948722005/158668/46bfdf4e/5adf1435N83459d1e.jpg","prizeIntro":"机械革命电脑","contributionPoint":0,"id":2,"jackpotSort":2,"prizePoint":1000}]}
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
        private List<JackpotPrizesBean> jackpotPrizes;

        public List<JackpotPrizesBean> getJackpotPrizes() {
            return jackpotPrizes;
        }

        public void setJackpotPrizes(List<JackpotPrizesBean> jackpotPrizes) {
            this.jackpotPrizes = jackpotPrizes;
        }

        public static class JackpotPrizesBean {
            /**
             * prizeIssue : 1
             * prizePic : http://192.168.0.199/lingling7-res/image/20180625/1529917683192.jpg
             * prizeType : PHYSICAL
             * contributionPoint : 0
             * lotteryCountdown : 30
             * prizePrice : 50
             * jackpotId : 33
             * prizeName : GranTurismo
             * prizeIntro : GranTurismo
             非凡跑车，源自经典
             GranTurismo的每一处细节都堪称意大利设计的杰出典范
             * id : 13
             * issuePrizeId : 16
             * jackpotSort : 0
             * prizePoint : 50
             */

            private int prizeIssue;
            private String prizePic;
            private String prizeType;
            private int contributionPoint;
            private int lotteryCountdown;
            private int prizePrice;
            private int jackpotId;
            private String prizeName;
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
    }
}
