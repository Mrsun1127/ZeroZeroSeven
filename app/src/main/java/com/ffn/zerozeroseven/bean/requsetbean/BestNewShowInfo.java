package com.ffn.zerozeroseven.bean.requsetbean;

import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class BestNewShowInfo {
    /**
     * code : 0
     * data : {"total":2,"LatestGoods":[{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180601/thumbnail1527839848496.jpg","price":23,"id":2453,"storeId":14,"goodsName":"手机","extraFee":0,"goodsType":"07"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180505/thumbnail1525498574652.jpg","price":3.78,"id":1863,"storeId":14,"goodsName":"统一来一桶桶装农家小炒肉面","extraFee":0,"goodsType":"01"}],"pageIndex":0,"totalPage":1,"stores":{"closingTime2":"00:00:00","closingTime":"10:00:00","openingTime":"10:00:00","isClosing":true,"openingTime2":"00:00:00"},"pageSize":3}
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
         * total : 2
         * LatestGoods : [{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180601/thumbnail1527839848496.jpg","price":23,"id":2453,"storeId":14,"goodsName":"手机","extraFee":0,"goodsType":"07"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180505/thumbnail1525498574652.jpg","price":3.78,"id":1863,"storeId":14,"goodsName":"统一来一桶桶装农家小炒肉面","extraFee":0,"goodsType":"01"}]
         * pageIndex : 0
         * totalPage : 1
         * stores : {"closingTime2":"00:00:00","closingTime":"10:00:00","openingTime":"10:00:00","isClosing":true,"openingTime2":"00:00:00"}
         * pageSize : 3
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private StoresBean stores;
        private int pageSize;
        private List<LatestGoodsBean> LatestGoods;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public StoresBean getStores() {
            return stores;
        }

        public void setStores(StoresBean stores) {
            this.stores = stores;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<LatestGoodsBean> getLatestGoods() {
            return LatestGoods;
        }

        public void setLatestGoods(List<LatestGoodsBean> LatestGoods) {
            this.LatestGoods = LatestGoods;
        }

        public static class StoresBean {
            /**
             * closingTime2 : 00:00:00
             * closingTime : 10:00:00
             * openingTime : 10:00:00
             * isClosing : true
             * openingTime2 : 00:00:00
             */

            private String closingTime2;
            private String closingTime;
            private String openingTime;
            private boolean isClosing;
            private String openingTime2;

            public String getClosingTime2() {
                return closingTime2;
            }

            public void setClosingTime2(String closingTime2) {
                this.closingTime2 = closingTime2;
            }

            public String getClosingTime() {
                return closingTime;
            }

            public void setClosingTime(String closingTime) {
                this.closingTime = closingTime;
            }

            public String getOpeningTime() {
                return openingTime;
            }

            public void setOpeningTime(String openingTime) {
                this.openingTime = openingTime;
            }

            public boolean isIsClosing() {
                return isClosing;
            }

            public void setIsClosing(boolean isClosing) {
                this.isClosing = isClosing;
            }

            public String getOpeningTime2() {
                return openingTime2;
            }

            public void setOpeningTime2(String openingTime2) {
                this.openingTime2 = openingTime2;
            }
        }

        public static class LatestGoodsBean {
            /**
             * thumbnail : http://www.lingling7.com/lingling7-res/image/20180601/thumbnail1527839848496.jpg
             * price : 23
             * id : 2453
             * storeId : 14
             * goodsName : 手机
             * extraFee : 0
             * goodsType : 07
             */

            private String thumbnail;
            private Double price;
            private int id;
            private int storeId;
            private String goodsName;
            private Double extraFee;
            private String goodsType;

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public Double getExtraFee() {
                return extraFee;
            }

            public void setExtraFee(Double extraFee) {
                this.extraFee = extraFee;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }
        }
    }
}
