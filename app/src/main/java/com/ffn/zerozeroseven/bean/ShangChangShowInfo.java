package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by GT on 2017/12/3.
 */

public class ShangChangShowInfo implements Parcelable{
    /**
     * code : 0
     * data : {"storeDesc":"零零7湖南农业大学分仓","city":"长沙市","openingTime":"09:00:00","closingTime":"22:00:00","province":"430000","schoolId":1719,"logo":"","storeName":"湖南农业大学","id":14,"openingTime2":"18:00:00","freeOrderNum":1000,"address":"","servicePhone":"85315177","updateTime":"2018-08-29 21:04:55","deliveryPrice":0,"storeBusiTimes":[{"cate":"WM","closingTime":"20:20:20","openingTime":"18:10:10"},{"cate":"WM","closingTime":"20:40:30","openingTime":"14:10:10"}],"adminName":"刘海波","closingTime2":"22:30:00","adminPhone":"18229853043","createTime":"2018-03-06 09:55:42","background":"","isClosing":true,"extraFee":0,"promotion":"新学期 礼品大放送","status":1}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected ShangChangShowInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<ShangChangShowInfo> CREATOR = new Creator<ShangChangShowInfo>() {
        @Override
        public ShangChangShowInfo createFromParcel(Parcel in) {
            return new ShangChangShowInfo(in);
        }

        @Override
        public ShangChangShowInfo[] newArray(int size) {
            return new ShangChangShowInfo[size];
        }
    };

    public ShangChangShowInfo() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(message);
    }


    public static class DataBean implements Parcelable{
        /**
         * storeDesc : 零零7湖南农业大学分仓
         * city : 长沙市
         * openingTime : 09:00:00
         * closingTime : 22:00:00
         * province : 430000
         * schoolId : 1719
         * logo :
         * storeName : 湖南农业大学
         * id : 14
         * openingTime2 : 18:00:00
         * freeOrderNum : 1000
         * address :
         * servicePhone : 85315177
         * updateTime : 2018-08-29 21:04:55
         * deliveryPrice : 0
         * storeBusiTimes : [{"cate":"WM","closingTime":"20:20:20","openingTime":"18:10:10"},{"cate":"WM","closingTime":"20:40:30","openingTime":"14:10:10"}]
         * adminName : 刘海波
         * closingTime2 : 22:30:00
         * adminPhone : 18229853043
         * createTime : 2018-03-06 09:55:42
         * background :
         * isClosing : true
         * extraFee : 0
         * promotion : 新学期 礼品大放送
         * status : 1
         */

        private String storeDesc;
        private String city;
        private String openingTime;
        private String closingTime;
        private String province;
        private int schoolId;
        private String logo;
        private String storeName;
        private int id;
        private String openingTime2;
        private int freeOrderNum;
        private String address;
        private String servicePhone;
        private String updateTime;
        private Double deliveryPrice;
        private String adminName;
        private String closingTime2;
        private String adminPhone;
        private String createTime;
        private String background;
        private boolean isClosing;
        private Double extraFee;
        private String promotion;
        private int status;
        private List<StoreBusiTimesBean> storeBusiTimes;

        protected DataBean(Parcel in) {
            storeDesc = in.readString();
            city = in.readString();
            openingTime = in.readString();
            closingTime = in.readString();
            province = in.readString();
            schoolId = in.readInt();
            logo = in.readString();
            storeName = in.readString();
            id = in.readInt();
            openingTime2 = in.readString();
            freeOrderNum = in.readInt();
            address = in.readString();
            servicePhone = in.readString();
            updateTime = in.readString();
            if (in.readByte() == 0) {
                deliveryPrice = null;
            } else {
                deliveryPrice = in.readDouble();
            }
            adminName = in.readString();
            closingTime2 = in.readString();
            adminPhone = in.readString();
            createTime = in.readString();
            background = in.readString();
            isClosing = in.readByte() != 0;
            if (in.readByte() == 0) {
                extraFee = null;
            } else {
                extraFee = in.readDouble();
            }
            promotion = in.readString();
            status = in.readInt();
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

        public String getStoreDesc() {
            return storeDesc;
        }

        public void setStoreDesc(String storeDesc) {
            this.storeDesc = storeDesc;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getOpeningTime() {
            return openingTime;
        }

        public void setOpeningTime(String openingTime) {
            this.openingTime = openingTime;
        }

        public String getClosingTime() {
            return closingTime;
        }

        public void setClosingTime(String closingTime) {
            this.closingTime = closingTime;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOpeningTime2() {
            return openingTime2;
        }

        public void setOpeningTime2(String openingTime2) {
            this.openingTime2 = openingTime2;
        }

        public int getFreeOrderNum() {
            return freeOrderNum;
        }

        public void setFreeOrderNum(int freeOrderNum) {
            this.freeOrderNum = freeOrderNum;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getServicePhone() {
            return servicePhone;
        }

        public void setServicePhone(String servicePhone) {
            this.servicePhone = servicePhone;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Double getDeliveryPrice() {
            return deliveryPrice;
        }

        public void setDeliveryPrice(Double deliveryPrice) {
            this.deliveryPrice = deliveryPrice;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }

        public String getClosingTime2() {
            return closingTime2;
        }

        public void setClosingTime2(String closingTime2) {
            this.closingTime2 = closingTime2;
        }

        public String getAdminPhone() {
            return adminPhone;
        }

        public void setAdminPhone(String adminPhone) {
            this.adminPhone = adminPhone;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public boolean isIsClosing() {
            return isClosing;
        }

        public void setIsClosing(boolean isClosing) {
            this.isClosing = isClosing;
        }

        public Double getExtraFee() {
            return extraFee;
        }

        public void setExtraFee(Double extraFee) {
            this.extraFee = extraFee;
        }

        public String getPromotion() {
            return promotion;
        }

        public void setPromotion(String promotion) {
            this.promotion = promotion;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<StoreBusiTimesBean> getStoreBusiTimes() {
            return storeBusiTimes;
        }

        public void setStoreBusiTimes(List<StoreBusiTimesBean> storeBusiTimes) {
            this.storeBusiTimes = storeBusiTimes;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(storeDesc);
            parcel.writeString(city);
            parcel.writeString(openingTime);
            parcel.writeString(closingTime);
            parcel.writeString(province);
            parcel.writeInt(schoolId);
            parcel.writeString(logo);
            parcel.writeString(storeName);
            parcel.writeInt(id);
            parcel.writeString(openingTime2);
            parcel.writeInt(freeOrderNum);
            parcel.writeString(address);
            parcel.writeString(servicePhone);
            parcel.writeString(updateTime);
            if (deliveryPrice == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeDouble(deliveryPrice);
            }
            parcel.writeString(adminName);
            parcel.writeString(closingTime2);
            parcel.writeString(adminPhone);
            parcel.writeString(createTime);
            parcel.writeString(background);
            parcel.writeByte((byte) (isClosing ? 1 : 0));
            if (extraFee == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeDouble(extraFee);
            }
            parcel.writeString(promotion);
            parcel.writeInt(status);
        }

        public static class StoreBusiTimesBean implements Parcelable{
            /**
             * cate : WM
             * closingTime : 20:20:20
             * openingTime : 18:10:10
             */

            private String cate;
            private String closingTime;
            private String openingTime;

            protected StoreBusiTimesBean(Parcel in) {
                cate = in.readString();
                closingTime = in.readString();
                openingTime = in.readString();
            }

            public static final Creator<StoreBusiTimesBean> CREATOR = new Creator<StoreBusiTimesBean>() {
                @Override
                public StoreBusiTimesBean createFromParcel(Parcel in) {
                    return new StoreBusiTimesBean(in);
                }

                @Override
                public StoreBusiTimesBean[] newArray(int size) {
                    return new StoreBusiTimesBean[size];
                }
            };

            public StoreBusiTimesBean() {
            }

            public String getCate() {
                return cate;
            }

            public void setCate(String cate) {
                this.cate = cate;
            }

            public String getClosingTime() {
                return closingTime;
            }

            public void setClosingTime(String closingTime) {
                this.closingTime = closingTime;
            }

            public String getOpeningTime() {
                return openingTime;
            }

            public void setOpeningTime(String openingTime) {
                this.openingTime = openingTime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(cate);
                parcel.writeString(closingTime);
                parcel.writeString(openingTime);
            }
        }
    }
}
