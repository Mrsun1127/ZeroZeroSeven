package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2018/1/26.
 */

public class MyRunDingDanInfo implements Serializable {
    /**
     * code : 0
     * data : {"total":7,"pageIndex":0,"totalPage":1,"pageSize":10,"list":[{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:10:18","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":5,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:04","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":7,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:06","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":8,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:08","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":9,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:10","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":10,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:15:12","pickupAddress":"哈哈","price":"20","publisherPhone":"18613991582","weight":"36","id":11,"deliverAddress":"哈哈","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:15:30","pickupAddress":"哈哈","price":"20","publisherPhone":"18613991582","weight":"36","id":12,"deliverAddress":"哈哈","type":"快递","status":0}]}
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
         * total : 7
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * list : [{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:10:18","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":5,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:04","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":7,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:06","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":8,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:08","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":9,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:13:10","pickupAddress":"伯父国际广场","price":"3.3","publisherPhone":"18613991582","weight":"220","id":10,"deliverAddress":"天安门","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:15:12","pickupAddress":"哈哈","price":"20","publisherPhone":"18613991582","weight":"36","id":11,"deliverAddress":"哈哈","type":"快递","status":0},{"pickupTime":"1970-01-01 08:00:02","createTime":"2018-01-25 16:15:30","pickupAddress":"哈哈","price":"20","publisherPhone":"18613991582","weight":"36","id":12,"deliverAddress":"哈哈","type":"快递","status":0}]
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
             * pickupTime : 1970-01-01 08:00:02
             * createTime : 2018-01-25 16:10:18
             * pickupAddress : 伯父国际广场
             * price : 3.3
             * publisherPhone : 18613991582
             * weight : 220
             * id : 5
             * deliverAddress : 天安门
             * type : 快递
             * status : 0
             */

            private String pickupTime;
            private String createTime;
            private String pickupAddress;
            private String price;
            private String publisherPhone;
            private String weight;
            private int id;
            private String deliverAddress;
            private String type;
            private int status;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
