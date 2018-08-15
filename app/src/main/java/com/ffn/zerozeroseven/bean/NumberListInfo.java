package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NumberListInfo implements Parcelable{
    /**
     * code : 0
     * data : {"total":7,"pageIndex":0,"totalPage":2,"pageSize":6,"list":[{"marketPrice":13999,"salesCount":0,"shopPrice":3599,"goodsThumb":"http://192.168.0.199/lingling7-res/image/20180730/thumbnail1532941272575.jpg","storeCount":10,"goodsBrief":"一加6","id":16,"rangeStatus":1,"goodsName":"苹果台式机"},{"marketPrice":8500,"salesCount":0,"shopPrice":7500,"goodsThumb":"http://192.168.0.199/lingling7-res/image/20180809/thumbnail1533804628303.png","storeCount":300,"goodsBrief":"荣耀AMD锐龙5新品","id":20,"rangeStatus":1,"goodsName":"荣耀MagicBook 14英寸轻薄窄边框笔记本电脑（AMD锐龙5 8G 256G FHD IPS 正版Office）冰河银"},{"marketPrice":4499,"salesCount":0,"shopPrice":4399,"goodsThumb":"http://192.168.0.199/lingling7-res/image/20180810/thumbnail1533873046054.jpg","storeCount":5,"goodsBrief":"宏碁E5-553-153D游戏本","id":21,"rangeStatus":1,"goodsName":"E5-553-153D"},{"salesCount":0,"shopPrice":9999,"goodsThumb":"","storeCount":10,"goodsBrief":"","id":24,"rangeStatus":1,"goodsName":"苹果台式机"},{"marketPrice":13999,"salesCount":0,"shopPrice":9999,"goodsThumb":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","storeCount":1000,"id":9,"rangeStatus":0,"goodsName":"苹果手机"}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected NumberListInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<NumberListInfo> CREATOR = new Creator<NumberListInfo>() {
        @Override
        public NumberListInfo createFromParcel(Parcel in) {
            return new NumberListInfo(in);
        }

        @Override
        public NumberListInfo[] newArray(int size) {
            return new NumberListInfo[size];
        }
    };

    public NumberListInfo() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(message);
    }

    public static class DataBean implements Parcelable{
        /**
         * total : 7
         * pageIndex : 0
         * totalPage : 2
         * pageSize : 6
         * list : [{"marketPrice":13999,"salesCount":0,"shopPrice":3599,"goodsThumb":"http://192.168.0.199/lingling7-res/image/20180730/thumbnail1532941272575.jpg","storeCount":10,"goodsBrief":"一加6","id":16,"rangeStatus":1,"goodsName":"苹果台式机"},{"marketPrice":8500,"salesCount":0,"shopPrice":7500,"goodsThumb":"http://192.168.0.199/lingling7-res/image/20180809/thumbnail1533804628303.png","storeCount":300,"goodsBrief":"荣耀AMD锐龙5新品","id":20,"rangeStatus":1,"goodsName":"荣耀MagicBook 14英寸轻薄窄边框笔记本电脑（AMD锐龙5 8G 256G FHD IPS 正版Office）冰河银"},{"marketPrice":4499,"salesCount":0,"shopPrice":4399,"goodsThumb":"http://192.168.0.199/lingling7-res/image/20180810/thumbnail1533873046054.jpg","storeCount":5,"goodsBrief":"宏碁E5-553-153D游戏本","id":21,"rangeStatus":1,"goodsName":"E5-553-153D"},{"salesCount":0,"shopPrice":9999,"goodsThumb":"","storeCount":10,"goodsBrief":"","id":24,"rangeStatus":1,"goodsName":"苹果台式机"},{"marketPrice":13999,"salesCount":0,"shopPrice":9999,"goodsThumb":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","storeCount":1000,"id":9,"rangeStatus":0,"goodsName":"苹果手机"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ListBean> list;

        protected DataBean(Parcel in) {
            total = in.readInt();
            pageIndex = in.readInt();
            totalPage = in.readInt();
            pageSize = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public DataBean() {
        }

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(total);
            parcel.writeInt(pageIndex);
            parcel.writeInt(totalPage);
            parcel.writeInt(pageSize);
        }

        public static class ListBean implements Parcelable{
            /**
             * marketPrice : 13999
             * salesCount : 0
             * shopPrice : 3599
             * goodsThumb : http://192.168.0.199/lingling7-res/image/20180730/thumbnail1532941272575.jpg
             * storeCount : 10
             * goodsBrief : 一加6
             * id : 16
             * rangeStatus : 1
             * goodsName : 苹果台式机
             */

            private Double marketPrice;
            private int salesCount;
            private Double shopPrice;
            private String goodsThumb;
            private int storeCount;
            private String goodsBrief;
            private int id;
            private int rangeStatus;
            private String goodsName;

            protected ListBean(Parcel in) {
                if (in.readByte() == 0) {
                    marketPrice = null;
                } else {
                    marketPrice = in.readDouble();
                }
                salesCount = in.readInt();
                if (in.readByte() == 0) {
                    shopPrice = null;
                } else {
                    shopPrice = in.readDouble();
                }
                goodsThumb = in.readString();
                storeCount = in.readInt();
                goodsBrief = in.readString();
                id = in.readInt();
                rangeStatus = in.readInt();
                goodsName = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public ListBean() {
            }

            public Double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(Double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getSalesCount() {
                return salesCount;
            }

            public void setSalesCount(int salesCount) {
                this.salesCount = salesCount;
            }

            public Double getShopPrice() {
                return shopPrice;
            }

            public void setShopPrice(Double shopPrice) {
                this.shopPrice = shopPrice;
            }

            public String getGoodsThumb() {
                return goodsThumb;
            }

            public void setGoodsThumb(String goodsThumb) {
                this.goodsThumb = goodsThumb;
            }

            public int getStoreCount() {
                return storeCount;
            }

            public void setStoreCount(int storeCount) {
                this.storeCount = storeCount;
            }

            public String getGoodsBrief() {
                return goodsBrief;
            }

            public void setGoodsBrief(String goodsBrief) {
                this.goodsBrief = goodsBrief;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRangeStatus() {
                return rangeStatus;
            }

            public void setRangeStatus(int rangeStatus) {
                this.rangeStatus = rangeStatus;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                if (marketPrice == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(marketPrice);
                }
                parcel.writeInt(salesCount);
                if (shopPrice == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(shopPrice);
                }
                parcel.writeString(goodsThumb);
                parcel.writeInt(storeCount);
                parcel.writeString(goodsBrief);
                parcel.writeInt(id);
                parcel.writeInt(rangeStatus);
                parcel.writeString(goodsName);
            }
        }
    }
}
