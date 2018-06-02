package com.ffn.zerozeroseven.bean;

public class ShopDetilsInfo {

    /**
     * code : 0
     * data : {"isRecommend":0,"promotionPrice":0,"orderNum":1,"purchasePrice":22,"hotEndTime":"00:00","isFree":0,"price":25.98,"schoolId":1719,"stockNum":5,"id":1539,"inventoryThreshold":1,"goodsName":"沙宣200ml修护水养洗发露","goodsDesc":"滋养修复秀发","limitNum":0,"hotStartTime":"00:00","image":"http://www.lingling7.com/lingling7-res/image/20180426/1524720768487.jpg","thumbnail":"http://www.lingling7.com/lingling7-res/image/20180426/thumbnail1524720768488.jpg","salesNum":0,"updateTime":"2018-04-27 14:15:18","storeId":14,"goodsType":"07","createTime":"2018-04-27 14:15:18","extraFee":0,"promotion":"","status":1}
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

    public static class DataBean {
        /**
         * isRecommend : 0
         * promotionPrice : 0
         * orderNum : 1
         * purchasePrice : 22
         * hotEndTime : 00:00
         * isFree : 0
         * price : 25.98
         * schoolId : 1719
         * stockNum : 5
         * id : 1539
         * inventoryThreshold : 1
         * goodsName : 沙宣200ml修护水养洗发露
         * goodsDesc : 滋养修复秀发
         * limitNum : 0
         * hotStartTime : 00:00
         * image : http://www.lingling7.com/lingling7-res/image/20180426/1524720768487.jpg
         * thumbnail : http://www.lingling7.com/lingling7-res/image/20180426/thumbnail1524720768488.jpg
         * salesNum : 0
         * updateTime : 2018-04-27 14:15:18
         * storeId : 14
         * goodsType : 07
         * createTime : 2018-04-27 14:15:18
         * extraFee : 0
         * promotion :
         * status : 1
         */

        private int isRecommend;
        private Double promotionPrice;
        private int orderNum;
        private Double purchasePrice;
        private String hotEndTime;
        private int isFree;
        private Double price;
        private int schoolId;
        private int stockNum;
        private int id;
        private int inventoryThreshold;
        private String goodsName;
        private String goodsDesc;
        private int limitNum;
        private String hotStartTime;
        private String image;
        private String thumbnail;
        private int salesNum;
        private String updateTime;
        private int storeId;
        private String goodsType;
        private String createTime;
        private Double extraFee;
        private String promotion;
        private int status;

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public Double getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(Double promotionPrice) {
            this.promotionPrice = promotionPrice;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public Double getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(Double purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public String getHotEndTime() {
            return hotEndTime;
        }

        public void setHotEndTime(String hotEndTime) {
            this.hotEndTime = hotEndTime;
        }

        public int getIsFree() {
            return isFree;
        }

        public void setIsFree(int isFree) {
            this.isFree = isFree;
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

        public int getInventoryThreshold() {
            return inventoryThreshold;
        }

        public void setInventoryThreshold(int inventoryThreshold) {
            this.inventoryThreshold = inventoryThreshold;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public int getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(int limitNum) {
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

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
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

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
