package com.ffn.zerozeroseven.bean.requsetbean;

import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class BestNewShowInfo {
    /**
     * code : 0
     * data : {"LatestGoods":[{"goodsName":"蜀鼎香辣金针菇","id":1337,"price":0.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523508511329.jpg"},{"goodsName":"湘阁香辣脆骨","id":1336,"price":0.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509209700.jpg"},{"goodsName":"顶牛爆炒牛筋","id":1335,"price":0.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509113402.jpg"},{"goodsName":"美味乐麻辣素牛肉","id":1334,"price":1.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509347906.jpg"},{"goodsName":"京牛香素食烤鸭","id":1333,"price":2.48,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509301145.jpg"},{"goodsName":"双汇Q趣香辣风味香肠","id":1332,"price":1.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523508906592.jpg"}],"total":229,"pageIndex":0,"totalPage":39,"pageSize":6}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * LatestGoods : [{"goodsName":"蜀鼎香辣金针菇","id":1337,"price":0.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523508511329.jpg"},{"goodsName":"湘阁香辣脆骨","id":1336,"price":0.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509209700.jpg"},{"goodsName":"顶牛爆炒牛筋","id":1335,"price":0.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509113402.jpg"},{"goodsName":"美味乐麻辣素牛肉","id":1334,"price":1.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509347906.jpg"},{"goodsName":"京牛香素食烤鸭","id":1333,"price":2.48,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523509301145.jpg"},{"goodsName":"双汇Q趣香辣风味香肠","id":1332,"price":1.98,"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523508906592.jpg"}]
         * total : 229
         * pageIndex : 0
         * totalPage : 39
         * pageSize : 6
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
             * goodsName : 蜀鼎香辣金针菇
             * id : 1337
             * price : 0.98
             * thumbnail : http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523508511329.jpg
             */

            private String goodsName;
            private int id;
            private Double price;
            private String thumbnail;

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }
        }
    }
}
