package com.ffn.zerozeroseven.bean;

import java.util.List;

public class LeaseDingDanListINfo {
    /**
     * code : 0
     * data : {"total":1,"pageIndex":0,"totalPage":1,"pageSize":6,"list":[{"receivingName":"杨洲","orderGoodsList":[{"goodsCount":1,"goodsPrice":300,"isSend":0,"goodsName":"电脑"}],"orderNo":"888888","payTime":"2018-08-23 14:51:23","isDelete":0,"postscript":"白色","receivingAddress":"火车站","orderStatus":4,"userId":90,"shippingStatus":1,"goodsCount":1,"createTime":"2018-08-23 14:52:02","goodsPrice":200,"schoolId":1719,"payment":"ALIPAY","id":1,"shippingTime":"2018-08-23 17:53:05","payStatus":1,"receivingPhone":"17388933063"}]}
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
         * total : 1
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 6
         * list : [{"receivingName":"杨洲","orderGoodsList":[{"goodsCount":1,"goodsPrice":300,"isSend":0,"goodsName":"电脑"}],"orderNo":"888888","payTime":"2018-08-23 14:51:23","isDelete":0,"postscript":"白色","receivingAddress":"火车站","orderStatus":4,"userId":90,"shippingStatus":1,"goodsCount":1,"createTime":"2018-08-23 14:52:02","goodsPrice":200,"schoolId":1719,"payment":"ALIPAY","id":1,"shippingTime":"2018-08-23 17:53:05","payStatus":1,"receivingPhone":"17388933063"}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ListBean> list;

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

        public static class ListBean {
            /**
             * receivingName : 杨洲
             * orderGoodsList : [{"goodsCount":1,"goodsPrice":300,"isSend":0,"goodsName":"电脑"}]
             * orderNo : 888888
             * payTime : 2018-08-23 14:51:23
             * isDelete : 0
             * postscript : 白色
             * receivingAddress : 火车站
             * orderStatus : 4
             * userId : 90
             * shippingStatus : 1
             * goodsCount : 1
             * createTime : 2018-08-23 14:52:02
             * goodsPrice : 200
             * schoolId : 1719
             * payment : ALIPAY
             * id : 1
             * shippingTime : 2018-08-23 17:53:05
             * payStatus : 1
             * receivingPhone : 17388933063
             */

            private String receivingName;
            private String orderNo;
            private String payTime;
            private int isDelete;
            private String postscript;
            private String receivingAddress;
            private int orderStatus;
            private int userId;
            private int shippingStatus;
            private int goodsCount;
            private String createTime;
            private Double goodsPrice;
            private int schoolId;
            private String payment;
            private int id;
            private String shippingTime;
            private int payStatus;
            private String receivingPhone;
            private List<OrderGoodsListBean> orderGoodsList;

            public String getReceivingName() {
                return receivingName;
            }

            public void setReceivingName(String receivingName) {
                this.receivingName = receivingName;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getPayTime() {
                return payTime;
            }

            public void setPayTime(String payTime) {
                this.payTime = payTime;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getPostscript() {
                return postscript;
            }

            public void setPostscript(String postscript) {
                this.postscript = postscript;
            }

            public String getReceivingAddress() {
                return receivingAddress;
            }

            public void setReceivingAddress(String receivingAddress) {
                this.receivingAddress = receivingAddress;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getShippingStatus() {
                return shippingStatus;
            }

            public void setShippingStatus(int shippingStatus) {
                this.shippingStatus = shippingStatus;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(Double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getShippingTime() {
                return shippingTime;
            }

            public void setShippingTime(String shippingTime) {
                this.shippingTime = shippingTime;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public String getReceivingPhone() {
                return receivingPhone;
            }

            public void setReceivingPhone(String receivingPhone) {
                this.receivingPhone = receivingPhone;
            }

            public List<OrderGoodsListBean> getOrderGoodsList() {
                return orderGoodsList;
            }

            public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
                this.orderGoodsList = orderGoodsList;
            }

            public static class OrderGoodsListBean {
                /**
                 * goodsCount : 1
                 * goodsPrice : 300
                 * isSend : 0
                 * goodsName : 电脑
                 */

                private int goodsCount;
                private Double goodsPrice;
                private int isSend;
                private String goodsName;
                private int goodId;

                public int getGoodId() {
                    return goodId;
                }

                public void setGoodId(int goodId) {
                    this.goodId = goodId;
                }

                public String getGoodsThumb() {
                    return goodsThumb;
                }

                public void setGoodsThumb(String goodsThumb) {
                    this.goodsThumb = goodsThumb;
                }

                private String goodsThumb;

                public int getGoodsCount() {
                    return goodsCount;
                }

                public void setGoodsCount(int goodsCount) {
                    this.goodsCount = goodsCount;
                }

                public Double getGoodsPrice() {
                    return goodsPrice;
                }

                public void setGoodsPrice(Double goodsPrice) {
                    this.goodsPrice = goodsPrice;
                }

                public int getIsSend() {
                    return isSend;
                }

                public void setIsSend(int isSend) {
                    this.isSend = isSend;
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
}
