package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2018/1/25.
 */

public class RunListRquestInfo implements Serializable{
    /**
     * code : 0
     * data : {"total":6,"pageIndex":0,"totalPage":2,"pageSize":3,"list":[{"pickupTime":"2018-01-25 16:26:00","createTime":"2018-01-25 16:26:22","pickupAddress":"我","price":"我","publisherPhone":"13787228171","weight":"我","id":13,"deliverAddress":"我","status":1},{"pickupTime":"2017-11-15 11:11:00","createTime":"2018-01-25 16:10:41","pickupAddress":"申请人学校","price":"1","publisherPhone":"13787228171","weight":"1kg","id":6,"deliverAddress":"申请人学校 ","type":"快递","status":1},{"pickupTime":"2018-11-11 11:11:11","createTime":"2018-01-25 14:03:53","pickupAddress":"取件地址","price":"价格","publisherPhone":"13787228171","weight":"物品重量","id":4,"deliverAddress":"送货地址","type":"其它","status":1}]}
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
         * total : 6
         * pageIndex : 0
         * totalPage : 2
         * pageSize : 3
         * list : [{"pickupTime":"2018-01-25 16:26:00","createTime":"2018-01-25 16:26:22","pickupAddress":"我","price":"我","publisherPhone":"13787228171","weight":"我","id":13,"deliverAddress":"我","status":1},{"pickupTime":"2017-11-15 11:11:00","createTime":"2018-01-25 16:10:41","pickupAddress":"申请人学校","price":"1","publisherPhone":"13787228171","weight":"1kg","id":6,"deliverAddress":"申请人学校 ","type":"快递","status":1},{"pickupTime":"2018-11-11 11:11:11","createTime":"2018-01-25 14:03:53","pickupAddress":"取件地址","price":"价格","publisherPhone":"13787228171","weight":"物品重量","id":4,"deliverAddress":"送货地址","type":"其它","status":1}]
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

        public static class ListBean implements Serializable{
            /**
             * pickupTime : 2018-01-25 16:26:00
             * createTime : 2018-01-25 16:26:22
             * pickupAddress : 我
             * price : 我
             * publisherPhone : 13787228171
             * weight : 我
             * id : 13
             * deliverAddress : 我
             * status : 1
             * type : 快递
             */

            private String pickupTime;
            private String createTime;
            private String pickupAddress;
            private String price;
            private String publisherPhone;
            private String weight;
            private int id;
            private String deliverAddress;
            private int status;
            private String type;

            public String getPickupTime() {
                return pickupTime;
            }

            public void setPickupTime(String pickupTime) {
                this.pickupTime = pickupTime;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPickupAddress() {
                return pickupAddress;
            }

            public void setPickupAddress(String pickupAddress) {
                this.pickupAddress = pickupAddress;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPublisherPhone() {
                return publisherPhone;
            }

            public void setPublisherPhone(String publisherPhone) {
                this.publisherPhone = publisherPhone;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDeliverAddress() {
                return deliverAddress;
            }

            public void setDeliverAddress(String deliverAddress) {
                this.deliverAddress = deliverAddress;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
