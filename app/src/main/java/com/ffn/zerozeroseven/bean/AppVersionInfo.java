package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by GT on 2018/3/8.
 */

public class AppVersionInfo implements Parcelable {
    /**

     * code : 0
     * data : {"targetSize":"5M","latestVersion":"1.0.0","downloadUrl":"http://www.lingling7.com/lingling7-res/apk/lingling7-1.0.0.apk","constraint":0,"id":1,"releaseNote":"我要上墙\u201d：\u201c表白贴\u201d、\u201c技术贴\u201d、\u201c寻物贴\u201d、\u201c交友贴\u201d等板块，各种最in的社交应有尽有。\r\n\u201c杂货铺\u201d：小7将精选零食以最快的速度送到您的寝室，让您可以省下更多宝贵的时间用来学业。\r\n\u201c007\u201d：帮人领快递，顺路赚点钱。\r\n\u201c推广收益\u201d：邀请下载APP，下单还有收益哦。"}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected AppVersionInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<AppVersionInfo> CREATOR = new Creator<AppVersionInfo>() {
        @Override
        public AppVersionInfo createFromParcel(Parcel in) {
            return new AppVersionInfo(in);
        }

        @Override
        public AppVersionInfo[] newArray(int size) {
            return new AppVersionInfo[size];
        }
    };

    public AppVersionInfo() {
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
         * targetSize : 5M
         * latestVersion : 1.0.0
         * downloadUrl : http://www.lingling7.com/lingling7-res/apk/lingling7-1.0.0.apk
         * constraint : 0
         * id : 1
         * releaseNote : 我要上墙”：“表白贴”、“技术贴”、“寻物贴”、“交友贴”等板块，各种最in的社交应有尽有。
         “杂货铺”：小7将精选零食以最快的速度送到您的寝室，让您可以省下更多宝贵的时间用来学业。
         “007”：帮人领快递，顺路赚点钱。
         “推广收益”：邀请下载APP，下单还有收益哦。
         */

        private String targetSize;
        private String latestVersion;
        private String downloadUrl;
        private int id;
        private int isConstraint;

        public int getIsConstraint() {
            return isConstraint;
        }

        public void setIsConstraint(int isConstraint) {
            this.isConstraint = isConstraint;
        }

        public static Creator<DataBean> getCREATOR() {
            return CREATOR;
        }

        private String releaseNote;

        protected DataBean(Parcel in) {
            targetSize = in.readString();
            latestVersion = in.readString();
            downloadUrl = in.readString();
            id = in.readInt();
            releaseNote = in.readString();
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

        public String getTargetSize() {
            return targetSize;
        }

        public void setTargetSize(String targetSize) {
            this.targetSize = targetSize;
        }

        public String getLatestVersion() {
            return latestVersion;
        }

        public void setLatestVersion(String latestVersion) {
            this.latestVersion = latestVersion;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReleaseNote() {
            return releaseNote;
        }

        public void setReleaseNote(String releaseNote) {
            this.releaseNote = releaseNote;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(targetSize);
            parcel.writeString(latestVersion);
            parcel.writeString(downloadUrl);
            parcel.writeInt(id);
            parcel.writeString(releaseNote);
        }
    }
}
