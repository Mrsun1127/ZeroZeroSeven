package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class RunnerInfo  implements Parcelable{

    /**
     * code : 0
     * data : {"item":{"checkStatus":1,"createTime":"2018-08-06 10:42:09","idcard":"430421199810239875","payStatus":1,"phone":"17388933063","realName":"杨洲","refuseReason":"姓名不真实","schoolId":1719,"sex":1,"starLevel":5,"userId":90}}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    protected RunnerInfo(Parcel in) {
        code = in.readInt();
        success = in.readByte() != 0;
    }

    public static final Creator<RunnerInfo> CREATOR = new Creator<RunnerInfo>() {
        @Override
        public RunnerInfo createFromParcel(Parcel in) {
            return new RunnerInfo(in);
        }

        @Override
        public RunnerInfo[] newArray(int size) {
            return new RunnerInfo[size];
        }
    };

    public RunnerInfo() {
    }

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
        /**
         * item : {"checkStatus":1,"createTime":"2018-08-06 10:42:09","idcard":"430421199810239875","payStatus":1,"phone":"17388933063","realName":"杨洲","refuseReason":"姓名不真实","schoolId":1719,"sex":1,"starLevel":5,"userId":90}
         */

        private ItemBean item;

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

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class ItemBean implements Parcelable{
            /**
             * checkStatus : 1
             * createTime : 2018-08-06 10:42:09
             * idcard : 430421199810239875
             * payStatus : 1
             * phone : 17388933063
             * realName : 杨洲
             * refuseReason : 姓名不真实
             * schoolId : 1719
             * sex : 1
             * starLevel : 5
             * userId : 90
             */

            private int checkStatus;
            private String createTime;
            private String idcard;
            private int payStatus;
            private String phone;
            private String realName;
            private String refuseReason;
            private int schoolId;
            private int sex;
            private int starLevel;
            private int userId;

            protected ItemBean(Parcel in) {
                checkStatus = in.readInt();
                createTime = in.readString();
                idcard = in.readString();
                payStatus = in.readInt();
                phone = in.readString();
                realName = in.readString();
                refuseReason = in.readString();
                schoolId = in.readInt();
                sex = in.readInt();
                starLevel = in.readInt();
                userId = in.readInt();
            }

            public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
                @Override
                public ItemBean createFromParcel(Parcel in) {
                    return new ItemBean(in);
                }

                @Override
                public ItemBean[] newArray(int size) {
                    return new ItemBean[size];
                }
            };

            public ItemBean() {
            }

            public int getCheckStatus() {
                return checkStatus;
            }

            public void setCheckStatus(int checkStatus) {
                this.checkStatus = checkStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getStarLevel() {
                return starLevel;
            }

            public void setStarLevel(int starLevel) {
                this.starLevel = starLevel;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(checkStatus);
                parcel.writeString(createTime);
                parcel.writeString(idcard);
                parcel.writeInt(payStatus);
                parcel.writeString(phone);
                parcel.writeString(realName);
                parcel.writeString(refuseReason);
                parcel.writeInt(schoolId);
                parcel.writeInt(sex);
                parcel.writeInt(starLevel);
                parcel.writeInt(userId);
            }
        }
    }
}
