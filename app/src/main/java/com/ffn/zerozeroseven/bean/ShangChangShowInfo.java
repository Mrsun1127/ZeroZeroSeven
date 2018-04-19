package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/12/3.
 */

public class ShangChangShowInfo implements Serializable {
    public ShangChangShowInfo(){}
    public ShangChangShowInfo(int code, DataBean data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * code : 0
     * data : {"storeDesc":"专卖香甜水果","storeType":"01","address":"地址","createTime":"2017-11-30 00:09:51","schoolId":1,"storeName":"007商铺","id":1,"extraFee":1,"promotion":"明天打5折"}
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
        public DataBean(){}
        public DataBean(String storeDesc, String storeType, String address, String createTime, int schoolId, String storeName, int id, Double extraFee, String promotion) {
            this.storeDesc = storeDesc;
            this.storeType = storeType;
            this.address = address;
            this.createTime = createTime;
            this.schoolId = schoolId;
            this.storeName = storeName;
            this.id = id;
            this.extraFee = extraFee;
            this.promotion = promotion;
        }

        /**
         * storeDesc : 专卖香甜水果
         * storeType : 01
         * address : 地址
         * createTime : 2017-11-30 00:09:51
         * schoolId : 1
         * storeName : 007商铺
         * id : 1
         * extraFee : 1
         * promotion : 明天打5折
         */

        private String storeDesc;
        private String storeType;
        private String address;
        private String createTime;
        private int schoolId;
        private String storeName;
        private int id;
        private Double extraFee;
        private String promotion;

        public String getStoreDesc() {
            return storeDesc;
        }

        public void setStoreDesc(String storeDesc) {
            this.storeDesc = storeDesc;
        }

        public String getStoreType() {
            return storeType;
        }

        public void setStoreType(String storeType) {
            this.storeType = storeType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
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
    }
}
