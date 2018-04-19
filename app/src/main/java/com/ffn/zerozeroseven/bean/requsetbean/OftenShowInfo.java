package com.ffn.zerozeroseven.bean.requsetbean;

import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class OftenShowInfo {
    /**
     * code : 0
     * data : {"total":9,"pageIndex":0,"totalPage":3,"goods":[{"thumbnail":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512479562442&di=7aded2b2a925f0580385ec85f7b33563&imgtype=0&src=http%3A%2F%2Fstatic.i3.xywy.com%2Fcms%2F20141128%2Fece9ad7afa270657f2b491b7a34a274943546.jpg","id":10,"goodsName":"牛油果"},{"thumbnail":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1869686845,2088147234&fm=27&gp=0.jpg","id":4,"goodsName":"火龙果"},{"thumbnail":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","id":3,"goodsName":"苹果"}],"pageSize":3}
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
         * total : 9
         * pageIndex : 0
         * totalPage : 3
         * goods : [{"thumbnail":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512479562442&di=7aded2b2a925f0580385ec85f7b33563&imgtype=0&src=http%3A%2F%2Fstatic.i3.xywy.com%2Fcms%2F20141128%2Fece9ad7afa270657f2b491b7a34a274943546.jpg","id":10,"goodsName":"牛油果"},{"thumbnail":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1869686845,2088147234&fm=27&gp=0.jpg","id":4,"goodsName":"火龙果"},{"thumbnail":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","id":3,"goodsName":"苹果"}]
         * pageSize : 3
         */

        private String total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<GoodsBean> goods;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
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

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * thumbnail : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512479562442&di=7aded2b2a925f0580385ec85f7b33563&imgtype=0&src=http%3A%2F%2Fstatic.i3.xywy.com%2Fcms%2F20141128%2Fece9ad7afa270657f2b491b7a34a274943546.jpg
             * id : 10
             * goodsName : 牛油果
             */

            private String thumbnail;
            private int id;
            private String goodsName;

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
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
        }
    }
}
