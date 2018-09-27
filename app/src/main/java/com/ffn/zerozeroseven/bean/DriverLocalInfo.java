package com.ffn.zerozeroseven.bean;

import java.util.List;

public class DriverLocalInfo {
    /**
     * code : 0
     * data : {"total":3,"pageIndex":0,"totalPage":1,"pageSize":20,"list":[{"image":"http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537946817862.png","number":0,"uid":"2557645013","sellingTag":"多渠道,dqd","address":"车站中路","distance":"0","drivingId":3,"price":1,"count":1,"title":"明诚驾校"},{"image":"http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537949906817.png","number":0,"uid":"2560373625","sellingTag":"包接送","address":"湖南省长沙市芙蓉区农大路1号","distance":"0","drivingId":4,"price":0.4,"count":0,"title":"金龙驾校"},{"image":"http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537949906817.png","number":0,"uid":"2563921017","sellingTag":"包接送","address":"练车场2号","distance":"0","drivingId":6,"price":0.4,"count":0,"title":"鸿运达驾培中心"}]}
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
         * total : 3
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 20
         * list : [{"image":"http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537946817862.png","number":0,"uid":"2557645013","sellingTag":"多渠道,dqd","address":"车站中路","distance":"0","drivingId":3,"price":1,"count":1,"title":"明诚驾校"},{"image":"http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537949906817.png","number":0,"uid":"2560373625","sellingTag":"包接送","address":"湖南省长沙市芙蓉区农大路1号","distance":"0","drivingId":4,"price":0.4,"count":0,"title":"金龙驾校"},{"image":"http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537949906817.png","number":0,"uid":"2563921017","sellingTag":"包接送","address":"练车场2号","distance":"0","drivingId":6,"price":0.4,"count":0,"title":"鸿运达驾培中心"}]
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
             * image : http://192.168.3.199/lingling7-res/image/20180926/thumbnail1537946817862.png
             * number : 0
             * uid : 2557645013
             * sellingTag : 多渠道,dqd
             * address : 车站中路
             * distance : 0
             * drivingId : 3
             * price : 1
             * count : 1
             * title : 明诚驾校
             */

            private String image;
            private int number;
            private String uid;
            private String sellingTag;
            private String address;
            private String distance;
            private int drivingId;
            private Double price;
            private int count;
            private String title;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getSellingTag() {
                return sellingTag;
            }

            public void setSellingTag(String sellingTag) {
                this.sellingTag = sellingTag;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public int getDrivingId() {
                return drivingId;
            }

            public void setDrivingId(int drivingId) {
                this.drivingId = drivingId;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
