package com.ffn.zerozeroseven.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by GT on 2018/2/5.
 */

public class ChongZhiInfo implements Serializable {
    /**
     * code : 0
     * data : {"package":"Sign=WXPay","orderNo":"151781345348904","appid":"wx189141e4085fa0d1","sign":"8C5728A6724378C8ED7FEA9F99ECF856","payment":"WechatPay","partnerid":"1494287592","prepayid":"wx20180205145129bed7bbbf640161785691","noncestr":"dlYDaJBitBvpN9UO4Mu2","timestamp":"1517813453"}
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
    }
}
