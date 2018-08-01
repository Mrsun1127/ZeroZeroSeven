package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by GT on 2018/2/5.
 */

public class ChongZhiInfo implements Parcelable {
    /**
     * code : 0
     * data : {"package":"Sign=WXPay","orderNo":"151781345348904","appid":"wx189141e4085fa0d1","sign":"8C5728A6724378C8ED7FEA9F99ECF856","payment":"WechatPay","partnerid":"1494287592","prepayid":"wx20180205145129bed7bbbf640161785691","noncestr":"dlYDaJBitBvpN9UO4Mu2","timestamp":"1517813453"}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected ChongZhiInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<ChongZhiInfo> CREATOR = new Creator<ChongZhiInfo>() {
        @Override
        public ChongZhiInfo createFromParcel(Parcel in) {
            return new ChongZhiInfo(in);
        }

        @Override
        public ChongZhiInfo[] newArray(int size) {
            return new ChongZhiInfo[size];
        }
    };

    public ChongZhiInfo() {
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
         * package : Sign=WXPay
         * orderNo : 151781345348904
         * appid : wx189141e4085fa0d1
         * sign : 8C5728A6724378C8ED7FEA9F99ECF856
         * payment : WechatPay
         * partnerid : 1494287592
         * prepayid : wx20180205145129bed7bbbf640161785691
         * noncestr : dlYDaJBitBvpN9UO4Mu2
         * timestamp : 1517813453
         */

        @SerializedName("package")
        private String packageX;
        private String orderNo;
        private String appid;
        private String sign;
        private String payment;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        protected DataBean(Parcel in) {
            packageX = in.readString();
            orderNo = in.readString();
            appid = in.readString();
            sign = in.readString();
            payment = in.readString();
            partnerid = in.readString();
            prepayid = in.readString();
            noncestr = in.readString();
            timestamp = in.readString();
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

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(packageX);
            parcel.writeString(orderNo);
            parcel.writeString(appid);
            parcel.writeString(sign);
            parcel.writeString(payment);
            parcel.writeString(partnerid);
            parcel.writeString(prepayid);
            parcel.writeString(noncestr);
            parcel.writeString(timestamp);
        }
    }
}
