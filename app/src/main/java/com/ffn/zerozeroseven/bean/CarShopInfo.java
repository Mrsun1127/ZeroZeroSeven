package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/4.
 */

public class CarShopInfo implements Serializable{
    List<ShopInfo> shopInfos;

    public List<ShopInfo> getShopInfos() {
        return shopInfos;
    }

    public void setShopInfos(List<ShopInfo> shopInfos) {
        this.shopInfos = shopInfos;
    }

    public static class ShopInfo implements Serializable{
        private String imagUrl;
        private String shopId;//商家id
        private String ShopName;
        private int buyCount;
        private Double shopMoney;
        private Double runMoney;
        private int goodsId;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getImagUrl() {
            return imagUrl;
        }

        public void setImagUrl(String imagUrl) {
            this.imagUrl = imagUrl;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public Double getShopMoney() {
            return shopMoney;
        }

        public void setShopMoney(Double shopMoney) {
            this.shopMoney = shopMoney;
        }

        public Double getRunMoney() {
            return runMoney;
        }

        public void setRunMoney(Double runMoney) {
            this.runMoney = runMoney;
        }


    }
}
