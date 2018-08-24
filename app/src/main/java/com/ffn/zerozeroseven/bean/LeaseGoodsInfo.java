package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LeaseGoodsInfo implements Parcelable{
    /**
     * code : 0
     * data : {"total":2,"pageIndex":0,"totalPage":1,"pageSize":10,"list":[{"cateId":1,"createTime":"2018-08-22 11:41:33","goodsDesc":"15天退货","goodsImage":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","goodsName":"电脑","goodsPrice":100,"goodsThumb":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","id":1,"isOnSale":1,"rangeStatus":0,"sortOrder":0,"storeCount":0},{"cateId":1,"createTime":"2018-08-22 11:48:08","goodsDesc":"15天退货  品质保证","goodsImage":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","goodsName":"华为手机","goodsPrice":100,"goodsThumb":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","id":4,"isOnSale":1,"rangeStatus":1,"sortOrder":0,"storeCount":0}]}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

    protected LeaseGoodsInfo(Parcel in) {
        code = in.readInt();
        success = in.readByte() != 0;
    }

    public static final Creator<LeaseGoodsInfo> CREATOR = new Creator<LeaseGoodsInfo>() {
        @Override
        public LeaseGoodsInfo createFromParcel(Parcel in) {
            return new LeaseGoodsInfo(in);
        }

        @Override
        public LeaseGoodsInfo[] newArray(int size) {
            return new LeaseGoodsInfo[size];
        }
    };

    public LeaseGoodsInfo() {
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeByte((byte) (success ? 1 : 0));
    }

    public static class DataBean implements Parcelable{
        /**
         * total : 2
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * list : [{"cateId":1,"createTime":"2018-08-22 11:41:33","goodsDesc":"15天退货","goodsImage":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","goodsName":"电脑","goodsPrice":100,"goodsThumb":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","id":1,"isOnSale":1,"rangeStatus":0,"sortOrder":0,"storeCount":0},{"cateId":1,"createTime":"2018-08-22 11:48:08","goodsDesc":"15天退货  品质保证","goodsImage":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","goodsName":"华为手机","goodsPrice":100,"goodsThumb":"http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg","id":4,"isOnSale":1,"rangeStatus":1,"sortOrder":0,"storeCount":0}]
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
             * cateId : 1
             * createTime : 2018-08-22 11:41:33
             * goodsDesc : 15天退货
             * goodsImage : http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg
             * goodsName : 电脑
             * goodsPrice : 100
             * goodsThumb : http://img13.360buyimg.com/n7/jfs/t19585/182/2579614246/216906/b669638f/5afced42N397104cb.jpg
             * id : 1
             * isOnSale : 1
             * rangeStatus : 0
             * sortOrder : 0
             * storeCount : 0
             */

            private int cateId;
            private String createTime;
            private String goodsDesc;
            private String goodsImage;
            private String goodsName;
            private Double goodsPrice;
            private String goodsThumb;
            private int id;
            private int isOnSale;
            private int rangeStatus;
            private int sortOrder;
            private int storeCount;

            protected ListBean(Parcel in) {
                cateId = in.readInt();
                createTime = in.readString();
                goodsDesc = in.readString();
                goodsImage = in.readString();
                goodsName = in.readString();
                if (in.readByte() == 0) {
                    goodsPrice = null;
                } else {
                    goodsPrice = in.readDouble();
                }
                goodsThumb = in.readString();
                id = in.readInt();
                isOnSale = in.readInt();
                rangeStatus = in.readInt();
                sortOrder = in.readInt();
                storeCount = in.readInt();
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

            public int getCateId() {
                return cateId;
            }

            public void setCateId(int cateId) {
                this.cateId = cateId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getGoodsDesc() {
                return goodsDesc;
            }

            public void setGoodsDesc(String goodsDesc) {
                this.goodsDesc = goodsDesc;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public Double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(Double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public String getGoodsThumb() {
                return goodsThumb;
            }

            public void setGoodsThumb(String goodsThumb) {
                this.goodsThumb = goodsThumb;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsOnSale() {
                return isOnSale;
            }

            public void setIsOnSale(int isOnSale) {
                this.isOnSale = isOnSale;
            }

            public int getRangeStatus() {
                return rangeStatus;
            }

            public void setRangeStatus(int rangeStatus) {
                this.rangeStatus = rangeStatus;
            }

            public int getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(int sortOrder) {
                this.sortOrder = sortOrder;
            }

            public int getStoreCount() {
                return storeCount;
            }

            public void setStoreCount(int storeCount) {
                this.storeCount = storeCount;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(cateId);
                parcel.writeString(createTime);
                parcel.writeString(goodsDesc);
                parcel.writeString(goodsImage);
                parcel.writeString(goodsName);
                if (goodsPrice == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(goodsPrice);
                }
                parcel.writeString(goodsThumb);
                parcel.writeInt(id);
                parcel.writeInt(isOnSale);
                parcel.writeInt(rangeStatus);
                parcel.writeInt(sortOrder);
                parcel.writeInt(storeCount);
            }
        }
    }
}
