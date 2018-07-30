package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HotInfo implements Parcelable{
    /**
     * code : 0
     * data : {"total":11,"pageIndex":0,"totalPage":2,"stores":{"closingTime2":"00:00:00","closingTime":"00:00:00","openingTime":"00:00:00","isClosing":true,"openingTime2":"00:00:00"},"pageSize":6,"products":[{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180411/thumbnail1523441929111.jpg","price":2.78,"id":782,"storeId":14,"goodsName":"伊利纯牛奶250ml","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523521503424.jpg","price":2.38,"id":777,"storeId":14,"goodsName":"旺仔牛奶纸盒125ml装","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521002854794.jpg","price":3.78,"id":772,"storeId":14,"goodsName":"统一阿萨姆奶茶500ml","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521001793721.jpg","price":3.78,"id":769,"storeId":14,"goodsName":"大瓶康师傅绿茶","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180320/thumbnail1521556150703.jpg","price":7.48,"id":764,"storeId":14,"goodsName":"果粒橙大瓶","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180411/thumbnail1523442007370.jpg","price":5.78,"id":749,"storeId":14,"goodsName":"罐装红牛250ml","extraFee":0,"goodsType":"05"}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected HotInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<HotInfo> CREATOR = new Creator<HotInfo>() {
        @Override
        public HotInfo createFromParcel(Parcel in) {
            return new HotInfo(in);
        }

        @Override
        public HotInfo[] newArray(int size) {
            return new HotInfo[size];
        }
    };

    public HotInfo() {
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
        public DataBean() {
        }

        /**

         * total : 11
         * pageIndex : 0
         * totalPage : 2
         * stores : {"closingTime2":"00:00:00","closingTime":"00:00:00","openingTime":"00:00:00","isClosing":true,"openingTime2":"00:00:00"}
         * pageSize : 6
         * products : [{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180411/thumbnail1523441929111.jpg","price":2.78,"id":782,"storeId":14,"goodsName":"伊利纯牛奶250ml","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180412/thumbnail1523521503424.jpg","price":2.38,"id":777,"storeId":14,"goodsName":"旺仔牛奶纸盒125ml装","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521002854794.jpg","price":3.78,"id":772,"storeId":14,"goodsName":"统一阿萨姆奶茶500ml","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180314/thumbnail1521001793721.jpg","price":3.78,"id":769,"storeId":14,"goodsName":"大瓶康师傅绿茶","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180320/thumbnail1521556150703.jpg","price":7.48,"id":764,"storeId":14,"goodsName":"果粒橙大瓶","extraFee":0,"goodsType":"05"},{"thumbnail":"http://www.lingling7.com/lingling7-res/image/20180411/thumbnail1523442007370.jpg","price":5.78,"id":749,"storeId":14,"goodsName":"罐装红牛250ml","extraFee":0,"goodsType":"05"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private StoresBean stores;
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

        public static class StoresBean {
            /**
             * closingTime2 : 00:00:00
             * closingTime : 00:00:00
             * openingTime : 00:00:00
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

        public static class ProductsBean implements Parcelable{
            /**
             * thumbnail : http://www.lingling7.com/lingling7-res/image/20180411/thumbnail1523441929111.jpg
             * price : 2.78
             * id : 782
             * storeId : 14
             * goodsName : 伊利纯牛奶250ml
             * extraFee : 0
             * goodsType : 05
             */

            private String thumbnail;
            private Double price;
            private int id;
            private int storeId;
            private String goodsName;
            private Double extraFee;
            private String goodsType;

            protected ProductsBean(Parcel in) {
                thumbnail = in.readString();
                if (in.readByte() == 0) {
                    price = null;
                } else {
                    price = in.readDouble();
                }
                id = in.readInt();
                storeId = in.readInt();
                goodsName = in.readString();
                if (in.readByte() == 0) {
                    extraFee = null;
                } else {
                    extraFee = in.readDouble();
                }
                goodsType = in.readString();
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

            public ProductsBean() {
            }

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(thumbnail);
                if (price == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(price);
                }
                parcel.writeInt(id);
                parcel.writeInt(storeId);
                parcel.writeString(goodsName);
                if (extraFee == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(extraFee);
                }
                parcel.writeString(goodsType);
            }
        }
    }
}
