package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by GT on 2017/12/8.
 */

public class FindSchoolInfo implements Parcelable {
    /**
     * code : 0
     * data : {"isRecommend":0,"province":"110000","city":"430100","name":"湖南信息职业技术学院","fullName":"湖南信息职业技术学院","id":1756}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected FindSchoolInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<FindSchoolInfo> CREATOR = new Creator<FindSchoolInfo>() {
        @Override
        public FindSchoolInfo createFromParcel(Parcel in) {
            return new FindSchoolInfo(in);
        }

        @Override
        public FindSchoolInfo[] newArray(int size) {
            return new FindSchoolInfo[size];
        }
    };

    public FindSchoolInfo() {
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
         * isRecommend : 0
         * province : 110000
         * city : 430100
         * name : 湖南信息职业技术学院
         * fullName : 湖南信息职业技术学院
         * id : 1756
         */

        private String isRecommend;
        private String province;
        private String city;
        private String name;
        private String fullName;
        private int id;

        protected DataBean(Parcel in) {
            isRecommend = in.readString();
            province = in.readString();
            city = in.readString();
            name = in.readString();
            fullName = in.readString();
            id = in.readInt();
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

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(isRecommend);
            parcel.writeString(province);
            parcel.writeString(city);
            parcel.writeString(name);
            parcel.writeString(fullName);
            parcel.writeInt(id);
        }
    }
}
