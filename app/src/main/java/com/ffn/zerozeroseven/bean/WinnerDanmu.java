package com.ffn.zerozeroseven.bean;

import java.util.List;

public class WinnerDanmu {

    /**
     * code : 0
     * data : {"pointPrizeWinner":[{"accept":false,"issuePrizeId":24,"prizeName":"GranTurismo","userPhone":"17388933063"},{"accept":false,"issuePrizeId":25,"prizeName":"积分券","userPhone":"13787228171"},{"accept":false,"issuePrizeId":26,"prizeName":"urus","userPhone":"13787228171"},{"accept":false,"issuePrizeId":27,"prizeName":"米魂牧马人-游戏鼠标","userPhone":"17388933063"},{"accept":false,"issuePrizeId":28,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","userPhone":"17388933063"},{"accept":false,"issuePrizeId":29,"prizeName":"雷迪凯 七彩机械手感键盘","userPhone":"17388933063"},{"accept":false,"issuePrizeId":33,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","userPhone":"17388933063"},{"accept":false,"issuePrizeId":36,"prizeName":"米魂牧马人-游戏鼠标","userPhone":"17388933063"}]}
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
        private List<PointPrizeWinnerBean> pointPrizeWinner;

        public List<PointPrizeWinnerBean> getPointPrizeWinner() {
            return pointPrizeWinner;
        }

        public void setPointPrizeWinner(List<PointPrizeWinnerBean> pointPrizeWinner) {
            this.pointPrizeWinner = pointPrizeWinner;
        }

        public static class PointPrizeWinnerBean {
            /**
             * accept : false
             * issuePrizeId : 24
             * prizeName : GranTurismo
             * userPhone : 17388933063
             */

            private boolean accept;
            private int issuePrizeId;
            private String prizeName;
            private String userPhone;

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
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

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }
        }
    }
}
