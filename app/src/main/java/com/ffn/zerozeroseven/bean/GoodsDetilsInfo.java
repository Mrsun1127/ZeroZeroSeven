package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2018/3/2.
 */

public class GoodsDetilsInfo implements Serializable {
    /**
     * code : 0
     * data : {"limitNum":0,"hotStartTime":"00:00","image":"http://218.76.7.150:8080/middle-res/007/image/20180301/1519876655647.jpg","isRecommend":0,"thumbnail":"http://218.76.7.150:8080/middle-res/007/image/20180301/1519876655647.jpg","promotionPrice":0,"salesNum":2,"updateTime":"2018-03-01 11:58:00","storeId":1,"hotEndTime":"00:00","goodsType":"05","isFree":0,"createTime":"2018-03-01 11:58:00","price":0.1,"schoolId":1719,"stockNum":98,"id":64,"goodsName":"大瓶康师傅冰红茶","extraFee":0,"goodsDesc":"游击队","promotion":"","status":1}
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
         * limitNum : 0
         * hotStartTime : 00:00
         * image : http://218.76.7.150:8080/middle-res/007/image/20180301/1519876655647.jpg
         * isRecommend : 0
         * thumbnail : http://218.76.7.150:8080/middle-res/007/image/20180301/1519876655647.jpg
         * promotionPrice : 0
         * salesNum : 2
         * updateTime : 2018-03-01 11:58:00
         * storeId : 1
         * hotEndTime : 00:00
         * goodsType : 05
         * isFree : 0
         * createTime : 2018-03-01 11:58:00
         * price : 0.1
         * schoolId : 1719
         * stockNum : 98
         * id : 64
         * goodsName : 大瓶康师傅冰红茶
         * extraFee : 0
         * goodsDesc : 游击队
         * promotion :
         * status : 1
         */

        private String limitNum;
        private String hotStartTime;
        private String image;
        private String isRecommend;
        private String thumbnail;
        private Double promotionPrice;
        private int salesNum;
        private String updateTime;
        private int storeId;
        private String hotEndTime;
        private String goodsType;
        private int isFree;
        private String createTime;
        private Double price;
        private int schoolId;
        private int stockNum;
        private int id;
        private String goodsName;
        private int extraFee;
        private String goodsDesc;
        private String promotion;
        private int status;

        public String getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(String limitNum) {
            this.limitNum = limitNum;
        }

        public String getHotStartTime() {
            return hotStartTime;
        }

        public void setHotStartTime(String hotStartTime) {
            this.hotStartTime = hotStartTime;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Double getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(Double promotionPrice) {
            this.promotionPrice = promotionPrice;
        }

        public int getSalesNum() {
            return salesNum;
        }

        public void setSalesNum(int salesNum) {
            this.salesNum = salesNum;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getHotEndTime() {
            return hotEndTime;
        }

        public void setHotEndTime(String hotEndTime) {
            this.hotEndTime = hotEndTime;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public int getIsFree() {
            return isFree;
        }

        public void setIsFree(int isFree) {
            this.isFree = isFree;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getExtraFee() {
            return extraFee;
        }

        public void setExtraFee(int extraFee) {
            this.extraFee = extraFee;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
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
