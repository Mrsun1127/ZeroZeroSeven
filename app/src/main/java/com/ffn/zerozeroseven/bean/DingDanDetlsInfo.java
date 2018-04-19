package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/6.
 */

public class DingDanDetlsInfo implements Serializable {
    /**
     * code : 0
     * data : {"courier":{"city":"430100","courierName":"陈魄","courierNo":"007152030716212806","createTime":"2018-03-06 11:32:42","id":23,"idCard":"431102199501166371","phone":"15616460898","province":"430000","schoolId":1719,"status":2},"order":{"createTime":"2018-03-10 13:01:58","details":[{"goodsCount":1,"goodsId":45,"goodsImage":"http://www.lingling7.com/lingling7-res/image/20180306/1520311575405.jpg","goodsName":"任性王子大辣片","goodsPrice":5,"goodsType":"03"}],"extraPrice":0,"id":102,"orderNo":"152065811068148","receiverAddress":"湖南农业大学芷兰12栋666","receiverName":"网友","receiverPhone":"888","status":1,"storeId":14,"storeName":"湖南农业大学","totalCount":1,"totalPrice":5,"updateTime":"2018-03-10 13:01:58","userId":90}}
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

    public static class DataBean implements Serializable{
        /**
         * courier : {"city":"430100","courierName":"陈魄","courierNo":"007152030716212806","createTime":"2018-03-06 11:32:42","id":23,"idCard":"431102199501166371","phone":"15616460898","province":"430000","schoolId":1719,"status":2}
         * order : {"createTime":"2018-03-10 13:01:58","details":[{"goodsCount":1,"goodsId":45,"goodsImage":"http://www.lingling7.com/lingling7-res/image/20180306/1520311575405.jpg","goodsName":"任性王子大辣片","goodsPrice":5,"goodsType":"03"}],"extraPrice":0,"id":102,"orderNo":"152065811068148","receiverAddress":"湖南农业大学芷兰12栋666","receiverName":"网友","receiverPhone":"888","status":1,"storeId":14,"storeName":"湖南农业大学","totalCount":1,"totalPrice":5,"updateTime":"2018-03-10 13:01:58","userId":90}
         */

        private CourierBean courier;
        private OrderBean order;

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

        public static class CourierBean {
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
        }

        public static class OrderBean implements Serializable{
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
