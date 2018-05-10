package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/12/3.
 */

public class ShangChangShowInfo implements Serializable {
    /**
     * code : 0
     * data : {"storeDesc":"零零7湖南农业大学分仓","freeOrderNum":1000,"address":"","servicePhone":"85315177","city":"430100","openingTime":"11:30:00","updateTime":"2018-05-07 11:59:01","deliveryPrice":5,"adminName":"刘海波","closingTime2":"22:30:00","adminPhone":"18229853043","closingTime":"14:00:00","province":"430000","createTime":"2018-03-06 09:55:42","schoolId":1719,"storeName":"湖南农业大学","id":14,"isClosing":true,"extraFee":0,"openingTime2":"18:00:00","promotion":"随机免单+推广收益","status":1}
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
         * storeDesc : 零零7湖南农业大学分仓
         * freeOrderNum : 1000
         * address :
         * servicePhone : 85315177
         * city : 430100
         * openingTime : 11:30:00
         * updateTime : 2018-05-07 11:59:01
         * deliveryPrice : 5
         * adminName : 刘海波
         * closingTime2 : 22:30:00
         * adminPhone : 18229853043
         * closingTime : 14:00:00
         * province : 430000
         * createTime : 2018-03-06 09:55:42
         * schoolId : 1719
         * storeName : 湖南农业大学
         * id : 14
         * isClosing : true
         * extraFee : 0
         * openingTime2 : 18:00:00
         * promotion : 随机免单+推广收益
         * status : 1
         */

        private String storeDesc;
        private int freeOrderNum;
        private String address;
        private String servicePhone;
        private String city;
        private String openingTime;
        private String updateTime;
        private Double deliveryPrice;
        private String adminName;
        private String closingTime2;
        private String adminPhone;
        private String closingTime;
        private String province;
        private String createTime;
        private int schoolId;
        private String storeName;
        private int id;
        private boolean isClosing;
        private Double extraFee;
        private String openingTime2;
        private String promotion;
        private int status;

        public String getStoreDesc() {
            return storeDesc;
        }

        public void setStoreDesc(String storeDesc) {
            this.storeDesc = storeDesc;
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

        public String getOpeningTime2() {
            return openingTime2;
        }

        public void setOpeningTime2(String openingTime2) {
            this.openingTime2 = openingTime2;
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
    }
}
