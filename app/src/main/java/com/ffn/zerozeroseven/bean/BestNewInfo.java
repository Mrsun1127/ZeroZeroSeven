package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BestNewInfo implements Parcelable{
    public BestNewInfo() {
    }

    /**

     * code : 0
     * data : {"jackpotPrizes":[{"contributionPoint":0,"jackpotId":1,"jackpotSort":1,"lotteryCountdown":30,"prizeIntro":"湘潭宾兰","prizeIssue":1,"prizeName":"冰蓝","prizePic":"https://i1.mifile.cn/f/i/18/blackshark/index/index_summary1.png?","prizePoint":20,"prizePrice":200},{"contributionPoint":0,"jackpotId":2,"jackpotSort":2,"lotteryCountdown":200,"prizeIntro":"机械革命电脑","prizeIssue":1,"prizeName":"电脑","prizePic":"http://img12.360buyimg.com/n1/s450x450_jfs/t19492/207/1948722005/158668/46bfdf4e/5adf1435N83459d1e.jpg","prizePoint":1000,"prizePrice":5400}]}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

    protected BestNewInfo(Parcel in) {
        code = in.readInt();
        success = in.readByte() != 0;
    }

    public static final Creator<BestNewInfo> CREATOR = new Creator<BestNewInfo>() {
        @Override
        public BestNewInfo createFromParcel(Parcel in) {
            return new BestNewInfo(in);
        }

        @Override
        public BestNewInfo[] newArray(int size) {
            return new BestNewInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeByte((byte) (success ? 1 : 0));
    }

    public static class DataBean implements Parcelable{
        private List<JackpotPrizesBean> jackpotPrizes;

        protected DataBean(Parcel in) {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public DataBean() {
        }

        public List<JackpotPrizesBean> getJackpotPrizes() {
            return jackpotPrizes;
        }

        public void setJackpotPrizes(List<JackpotPrizesBean> jackpotPrizes) {
            this.jackpotPrizes = jackpotPrizes;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class JackpotPrizesBean implements Parcelable{
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

            protected JackpotPrizesBean(Parcel in) {
                contributionPoint = in.readInt();
                id = in.readInt();
                jackpotId = in.readInt();
                jackpotSort = in.readInt();
                lotteryCountdown = in.readInt();
                countDownTime = in.readInt();
                prizeIntro = in.readString();
                prizeIssue = in.readInt();
                prizeName = in.readString();
                prizePic = in.readString();
                prizePoint = in.readInt();
                if (in.readByte() == 0) {
                    prizePrice = null;
                } else {
                    prizePrice = in.readDouble();
                }
            }

            public static final Creator<JackpotPrizesBean> CREATOR = new Creator<JackpotPrizesBean>() {
                @Override
                public JackpotPrizesBean createFromParcel(Parcel in) {
                    return new JackpotPrizesBean(in);
                }

                @Override
                public JackpotPrizesBean[] newArray(int size) {
                    return new JackpotPrizesBean[size];
                }
            };

            public JackpotPrizesBean() {
            }

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(contributionPoint);
                parcel.writeInt(id);
                parcel.writeInt(jackpotId);
                parcel.writeInt(jackpotSort);
                parcel.writeInt(lotteryCountdown);
                parcel.writeInt(countDownTime);
                parcel.writeString(prizeIntro);
                parcel.writeInt(prizeIssue);
                parcel.writeString(prizeName);
                parcel.writeString(prizePic);
                parcel.writeInt(prizePoint);
                if (prizePrice == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(prizePrice);
                }
            }
        }
    }
}
