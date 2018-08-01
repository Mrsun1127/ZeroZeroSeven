package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by GT on 2017/11/29.
 */

public class CodeInfo implements Parcelable {
    /**
     * code : 0
     * data : {"authcode":"255441"}
     * message :
     */

    private int code;
    private DataBean data;
    private String message;

    protected CodeInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<CodeInfo> CREATOR = new Creator<CodeInfo>() {
        @Override
        public CodeInfo createFromParcel(Parcel in) {
            return new CodeInfo(in);
        }

        @Override
        public CodeInfo[] newArray(int size) {
            return new CodeInfo[size];
        }
    };

    public CodeInfo() {
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
         * authcode : 255441
         */

        private String authcode;

        protected DataBean(Parcel in) {
            authcode = in.readString();
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

        public String getAuthcode() {
            return authcode;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(authcode);
        }
    }
}
