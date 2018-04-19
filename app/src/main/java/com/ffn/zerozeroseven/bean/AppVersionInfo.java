package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2018/3/8.
 */

public class AppVersionInfo implements Serializable {
    /**
     * code : 0
     * data : {"targetSize":"5M","latestVersion":"1.0.0","downloadUrl":"http://www.lingling7.com/lingling7-res/apk/lingling7-1.0.0.apk","constraint":0,"id":1,"releaseNote":"我要上墙\u201d：\u201c表白贴\u201d、\u201c技术贴\u201d、\u201c寻物贴\u201d、\u201c交友贴\u201d等板块，各种最in的社交应有尽有。\r\n\u201c杂货铺\u201d：小7将精选零食以最快的速度送到您的寝室，让您可以省下更多宝贵的时间用来学业。\r\n\u201c007\u201d：帮人领快递，顺路赚点钱。\r\n\u201c推广收益\u201d：邀请下载APP，下单还有收益哦。"}
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

    public static class DataBean implements Serializable{
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
        private int constraint;
        private int id;
        private String releaseNote;

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

        public int getConstraint() {
            return constraint;
        }

        public void setConstraint(int constraint) {
            this.constraint = constraint;
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
    }
}
