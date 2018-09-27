package com.ffn.zerozeroseven.bean;

import java.util.List;

public class DriverLocalInfo {
    /**
     * code : 0
     * data : {"total":3,"pageIndex":0,"totalPage":1,"pageSize":20,"list":[{"address":"天心区大托机场口","thumb":"http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538044472984.jpg","price":1,"latitude":28.073783,"name":"远征驾校","countSignUp":0,"commission":0,"id":13,"longitude":112.99757},{"address":"芙蓉区嘉雨路","thumb":"http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538033950077.jpg","price":2.48,"latitude":28,"name":"恒大驾校","countSignUp":0,"commission":0,"id":12,"longitude":113},{"address":"湖南省长沙市天心区城南路79号南门口地铁站附近79号南门口地铁站附近","thumb":"http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538031584898.jpg","price":1,"latitude":28.155898,"name":"鸿运达驾培中心","countSignUp":5,"commission":0,"id":11,"longitude":113.046822}]}
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
         * list : [{"address":"天心区大托机场口","thumb":"http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538044472984.jpg","price":1,"latitude":28.073783,"name":"远征驾校","countSignUp":0,"commission":0,"id":13,"longitude":112.99757},{"address":"芙蓉区嘉雨路","thumb":"http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538033950077.jpg","price":2.48,"latitude":28,"name":"恒大驾校","countSignUp":0,"commission":0,"id":12,"longitude":113},{"address":"湖南省长沙市天心区城南路79号南门口地铁站附近79号南门口地铁站附近","thumb":"http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538031584898.jpg","price":1,"latitude":28.155898,"name":"鸿运达驾培中心","countSignUp":5,"commission":0,"id":11,"longitude":113.046822}]
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
             * address : 天心区大托机场口
             * thumb : http://192.168.3.199/lingling7-res/image/20180927/thumbnail1538044472984.jpg
             * price : 1
             * latitude : 28.073783
             * name : 远征驾校
             * countSignUp : 0
             * commission : 0
             * id : 13
             * longitude : 112.99757
             */

            private String address;
            private String thumb;
            private Double price;
            private double latitude;
            private String name;
            private int countSignUp;
            private int commission;
            private int id;
            private double longitude;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCountSignUp() {
                return countSignUp;
            }

            public void setCountSignUp(int countSignUp) {
                this.countSignUp = countSignUp;
            }

            public int getCommission() {
                return commission;
            }

            public void setCommission(int commission) {
                this.commission = commission;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }
}
