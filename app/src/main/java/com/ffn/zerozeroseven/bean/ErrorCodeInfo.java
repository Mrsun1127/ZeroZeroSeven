package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GT on 2017/11/30.
 */

public class ErrorCodeInfo implements Parcelable{
    /**
     * code : -1
     * data : {}
     * message : 手机号13187654321的验证码处于有效状态，无需重新发送
     */

    private int code;
    private DataBean data;
    private String message;

    protected ErrorCodeInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<ErrorCodeInfo> CREATOR = new Creator<ErrorCodeInfo>() {
        @Override
        public ErrorCodeInfo createFromParcel(Parcel in) {
            return new ErrorCodeInfo(in);
        }

        @Override
        public ErrorCodeInfo[] newArray(int size) {
            return new ErrorCodeInfo[size];
        }
    };

    public ErrorCodeInfo() {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }
    }
}
