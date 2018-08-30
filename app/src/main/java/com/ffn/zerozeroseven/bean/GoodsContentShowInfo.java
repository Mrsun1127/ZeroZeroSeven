package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by GT on 2017/12/3.
 */

public class GoodsContentShowInfo implements Parcelable{
    public GoodsContentShowInfo(){}

    public GoodsContentShowInfo(int code, DataBean data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * code : 0
     * data : {"total":4,"pageIndex":0,"totalPage":1,"pageSize":10,"products":[{"thumbnail":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=302546753,428540576&fm=27&gp=0.jpg","promotionPrice":0,"price":20,"stockNum":0,"id":5,"goodsName":"橘子"},{"thumbnail":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1869686845,2088147234&fm=27&gp=0.jpg","promotionPrice":0,"price":50,"stockNum":0,"id":4,"goodsName":"火龙果"},{"thumbnail":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","promotionPrice":0,"price":10,"stockNum":0,"id":3,"goodsName":"苹果"},{"thumbnail":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2937327088,1826565765&fm=27&gp=0.jpg","promotionPrice":0,"price":100,"stockNum":20,"id":2,"goodsName":"浩子"}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected GoodsContentShowInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<GoodsContentShowInfo> CREATOR = new Creator<GoodsContentShowInfo>() {
        @Override
        public GoodsContentShowInfo createFromParcel(Parcel in) {
            return new GoodsContentShowInfo(in);
        }

        @Override
        public GoodsContentShowInfo[] newArray(int size) {
            return new GoodsContentShowInfo[size];
        }
    };

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
        public DataBean(){}

        public DataBean(int total, int pageIndex, int totalPage, int pageSize, List<ProductsBean> products) {
            this.total = total;
            this.pageIndex = pageIndex;
            this.totalPage = totalPage;
            this.pageSize = pageSize;
            this.products = products;
        }

        /**
         * total : 4
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * products : [{"thumbnail":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=302546753,428540576&fm=27&gp=0.jpg","promotionPrice":0,"price":20,"stockNum":0,"id":5,"goodsName":"橘子"},{"thumbnail":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1869686845,2088147234&fm=27&gp=0.jpg","promotionPrice":0,"price":50,"stockNum":0,"id":4,"goodsName":"火龙果"},{"thumbnail":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3783019988,3974813389&fm=27&gp=0.jpg","promotionPrice":0,"price":10,"stockNum":0,"id":3,"goodsName":"苹果"},{"thumbnail":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2937327088,1826565765&fm=27&gp=0.jpg","promotionPrice":0,"price":100,"stockNum":20,"id":2,"goodsName":"浩子"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ProductsBean> products;

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

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
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

        public static class ProductsBean implements Parcelable {
            public ProductsBean(){}

            public ProductsBean(String thumbnail, String promotionPrice, Double price, int stockNum, int id, String goodsName) {
                this.thumbnail = thumbnail;
                this.promotionPrice = promotionPrice;
                this.price = price;
                this.stockNum = stockNum;
                this.id = id;
                this.goodsName = goodsName;
            }



            /**
             * thumbnail : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=302546753,428540576&fm=27&gp=0.jpg
             * promotionPrice : 0
             * price : 20
             * stockNum : 0
             * id : 5

             * goodsName : 橘子
             */

            private String thumbnail;
            private String promotionPrice;
            private Double price;
            private int stockNum;
            private int id;
            private String goodsName;
            private String marketPrice;

            public String getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(String marketPrice) {
                this.marketPrice = marketPrice;
            }

            private String goodsDesc;
            protected ProductsBean(Parcel in) {
                thumbnail = in.readString();
                promotionPrice = in.readString();
                price = in.readDouble();
                stockNum = in.readInt();
                id = in.readInt();
                goodsName = in.readString();
                goodsDesc=in.readString();
            }

            public static final Creator<ProductsBean> CREATOR = new Creator<ProductsBean>() {
                @Override
                public ProductsBean createFromParcel(Parcel in) {
                    return new ProductsBean(in);
                }

                @Override
                public ProductsBean[] newArray(int size) {
                    return new ProductsBean[size];
                }
            };
            public String getGoodsDesc() {
                return goodsDesc;
            }

            public void setGoodsDesc(String goodsDesc) {
                this.goodsDesc = goodsDesc;
            }
            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getPromotionPrice() {
                return promotionPrice;
            }

            public void setPromotionPrice(String promotionPrice) {
                this.promotionPrice = promotionPrice;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(thumbnail);
                parcel.writeString(promotionPrice);
                parcel.writeDouble(price);
                parcel.writeInt(stockNum);
                parcel.writeInt(id);
                parcel.writeString(goodsName);
                parcel.writeString(goodsDesc);
            }
        }
    }
}
