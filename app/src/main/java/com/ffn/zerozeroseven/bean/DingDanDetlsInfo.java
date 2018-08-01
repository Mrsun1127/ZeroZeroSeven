package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class DingDanDetlsInfo implements Parcelable {
    /**
     * code : 0
     * data : {"courier":{"city":"430100","courierName":"陈魄","courierNo":"007152030716212806","createTime":"2018-03-06 11:32:42","id":23,"idCard":"431102199501166371","phone":"15616460898","province":"430000","schoolId":1719,"status":2},"order":{"createTime":"2018-03-10 13:01:58","details":[{"goodsCount":1,"goodsId":45,"goodsImage":"http://www.lingling7.com/lingling7-res/image/20180306/1520311575405.jpg","goodsName":"任性王子大辣片","goodsPrice":5,"goodsType":"03"}],"extraPrice":0,"id":102,"orderNo":"152065811068148","receiverAddress":"湖南农业大学芷兰12栋666","receiverName":"网友","receiverPhone":"888","status":1,"storeId":14,"storeName":"湖南农业大学","totalCount":1,"totalPrice":5,"updateTime":"2018-03-10 13:01:58","userId":90}}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected DingDanDetlsInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<DingDanDetlsInfo> CREATOR = new Creator<DingDanDetlsInfo>() {
        @Override
        public DingDanDetlsInfo createFromParcel(Parcel in) {
            return new DingDanDetlsInfo(in);
        }

        @Override
        public DingDanDetlsInfo[] newArray(int size) {
            return new DingDanDetlsInfo[size];
        }
    };

    public DingDanDetlsInfo() {
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
         * courier : {"city":"430100","courierName":"陈魄","courierNo":"007152030716212806","createTime":"2018-03-06 11:32:42","id":23,"idCard":"431102199501166371","phone":"15616460898","province":"430000","schoolId":1719,"status":2}
         * order : {"createTime":"2018-03-10 13:01:58","details":[{"goodsCount":1,"goodsId":45,"goodsImage":"http://www.lingling7.com/lingling7-res/image/20180306/1520311575405.jpg","goodsName":"任性王子大辣片","goodsPrice":5,"goodsType":"03"}],"extraPrice":0,"id":102,"orderNo":"152065811068148","receiverAddress":"湖南农业大学芷兰12栋666","receiverName":"网友","receiverPhone":"888","status":1,"storeId":14,"storeName":"湖南农业大学","totalCount":1,"totalPrice":5,"updateTime":"2018-03-10 13:01:58","userId":90}
         */

        private CourierBean courier;
        private OrderBean order;

        protected DataBean(Parcel in) {
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

        public CourierBean getCourier() {
            return courier;
        }

        public void setCourier(CourierBean courier) {
            this.courier = courier;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class CourierBean implements Parcelable{
            /**
             * city : 430100
             * courierName : 陈魄
             * courierNo : 007152030716212806
             * createTime : 2018-03-06 11:32:42
             * id : 23
             * idCard : 431102199501166371
             * phone : 15616460898
             * province : 430000
             * schoolId : 1719
             * status : 2
             */

            private String city;
            private String courierName;
            private String courierNo;
            private String createTime;
            private int id;
            private String idCard;
            private String phone;
            private String province;
            private int schoolId;
            private int status;

            protected CourierBean(Parcel in) {
                city = in.readString();
                courierName = in.readString();
                courierNo = in.readString();
                createTime = in.readString();
                id = in.readInt();
                idCard = in.readString();
                phone = in.readString();
                province = in.readString();
                schoolId = in.readInt();
                status = in.readInt();
            }

            public static final Creator<CourierBean> CREATOR = new Creator<CourierBean>() {
                @Override
                public CourierBean createFromParcel(Parcel in) {
                    return new CourierBean(in);
                }

                @Override
                public CourierBean[] newArray(int size) {
                    return new CourierBean[size];
                }
            };

            public CourierBean() {
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCourierName() {
                return courierName;
            }

            public void setCourierName(String courierName) {
                this.courierName = courierName;
            }

            public String getCourierNo() {
                return courierNo;
            }

            public void setCourierNo(String courierNo) {
                this.courierNo = courierNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(city);
                parcel.writeString(courierName);
                parcel.writeString(courierNo);
                parcel.writeString(createTime);
                parcel.writeInt(id);
                parcel.writeString(idCard);
                parcel.writeString(phone);
                parcel.writeString(province);
                parcel.writeInt(schoolId);
                parcel.writeInt(status);
            }
        }

        public static class OrderBean implements Parcelable{
            /**
             * createTime : 2018-03-10 13:01:58
             * details : [{"goodsCount":1,"goodsId":45,"goodsImage":"http://www.lingling7.com/lingling7-res/image/20180306/1520311575405.jpg","goodsName":"任性王子大辣片","goodsPrice":5,"goodsType":"03"}]
             * extraPrice : 0
             * id : 102
             * orderNo : 152065811068148
             * receiverAddress : 湖南农业大学芷兰12栋666
             * receiverName : 网友
             * receiverPhone : 888
             * status : 1
             * storeId : 14
             * storeName : 湖南农业大学
             * totalCount : 1
             * totalPrice : 5
             * updateTime : 2018-03-10 13:01:58
             * userId : 90
             */

            private String createTime;
            private Double extraPrice;
            private int id;
            private String orderNo;
            private String receiverAddress;
            private String receiverName;
            private String receiverPhone;
            private int status;
            private int storeId;
            private String storeName;
            private int totalCount;
            private Double totalPrice;
            private String updateTime;
            private int userId;
            private List<DetailsBean> details;
            private String remark;

            protected OrderBean(Parcel in) {
                createTime = in.readString();
                if (in.readByte() == 0) {
                    extraPrice = null;
                } else {
                    extraPrice = in.readDouble();
                }
                id = in.readInt();
                orderNo = in.readString();
                receiverAddress = in.readString();
                receiverName = in.readString();
                receiverPhone = in.readString();
                status = in.readInt();
                storeId = in.readInt();
                storeName = in.readString();
                totalCount = in.readInt();
                if (in.readByte() == 0) {
                    totalPrice = null;
                } else {
                    totalPrice = in.readDouble();
                }
                updateTime = in.readString();
                userId = in.readInt();
                remark = in.readString();
            }

            public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
                @Override
                public OrderBean createFromParcel(Parcel in) {
                    return new OrderBean(in);
                }

                @Override
                public OrderBean[] newArray(int size) {
                    return new OrderBean[size];
                }
            };

            public OrderBean() {
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Double getExtraPrice() {
                return extraPrice;
            }

            public void setExtraPrice(Double extraPrice) {
                this.extraPrice = extraPrice;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public Double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(createTime);
                if (extraPrice == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(extraPrice);
                }
                parcel.writeInt(id);
                parcel.writeString(orderNo);
                parcel.writeString(receiverAddress);
                parcel.writeString(receiverName);
                parcel.writeString(receiverPhone);
                parcel.writeInt(status);
                parcel.writeInt(storeId);
                parcel.writeString(storeName);
                parcel.writeInt(totalCount);
                if (totalPrice == null) {
                    parcel.writeByte((byte) 0);
                } else {
                    parcel.writeByte((byte) 1);
                    parcel.writeDouble(totalPrice);
                }
                parcel.writeString(updateTime);
                parcel.writeInt(userId);
                parcel.writeString(remark);
            }

            public static class DetailsBean {
                /**
                 * goodsCount : 1
                 * goodsId : 45
                 * goodsImage : http://www.lingling7.com/lingling7-res/image/20180306/1520311575405.jpg
                 * goodsName : 任性王子大辣片
                 * goodsPrice : 5
                 * goodsType : 03
                 */

                private int goodsCount;
                private int goodsId;
                private String goodsImage;
                private String goodsName;
                private Double goodsPrice;
                private String goodsType;

                public int getGoodsCount() {
                    return goodsCount;
                }

                public void setGoodsCount(int goodsCount) {
                    this.goodsCount = goodsCount;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
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

                public String getGoodsType() {
                    return goodsType;
                }

                public void setGoodsType(String goodsType) {
                    this.goodsType = goodsType;
                }
            }
        }
    }
}
