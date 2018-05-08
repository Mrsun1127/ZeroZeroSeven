package com.ffn.zerozeroseven.bean.requsetbean;

import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class BestNewShowInfo {
    /**
     * code : 0
     * data : {"LatestGoods":[{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521001836630.jpg","price":2.48,"id":620,"storeId":14,"goodsName":"翻天娃红烧牛腩味","extraFee":0,"goodsType":"02"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521002055424.jpg","price":2.48,"id":619,"storeId":14,"goodsName":"翻天娃薯条味辣条","extraFee":0,"goodsType":"02"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521002022637.jpg","price":4.98,"id":618,"storeId":14,"goodsName":"酱香鸭脖","extraFee":0,"goodsType":"02"}],"total":22,"pageIndex":0,"totalPage":8,"pageSize":3}
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
         * LatestGoods : [{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521001836630.jpg","price":2.48,"id":620,"storeId":14,"goodsName":"翻天娃红烧牛腩味","extraFee":0,"goodsType":"02"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521002055424.jpg","price":2.48,"id":619,"storeId":14,"goodsName":"翻天娃薯条味辣条","extraFee":0,"goodsType":"02"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521002022637.jpg","price":4.98,"id":618,"storeId":14,"goodsName":"酱香鸭脖","extraFee":0,"goodsType":"02"}]
         * total : 22
         * pageIndex : 0
         * totalPage : 8
         * pageSize : 3
         */

        private int total;
        private int pageIndex;
        private int totalPage;
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

        public static class LatestGoodsBean {
            /**
             * thumbnail : http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521001836630.jpg
             * price : 2.48
             * id : 620
             * storeId : 14
             * goodsName : 翻天娃红烧牛腩味
             * extraFee : 0
             * goodsType : 02
             */

            private String thumbnail;
            private Double price;
            private int id;
            private String goodsName;
            private int storeId;
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
