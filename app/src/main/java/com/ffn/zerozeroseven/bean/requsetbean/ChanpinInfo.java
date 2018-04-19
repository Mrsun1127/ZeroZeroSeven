package com.ffn.zerozeroseven.bean.requsetbean;

import java.util.List;

/**
 * Created by GT on 2018/2/5.
 */

public class ChanpinInfo {
    /**
     * code : 0
     * data : {"products":[{"period":12,"price":1,"name":"VIP黄金会员一年包","discount":0.95,"remark":"全网下单享9.5折，并享受跑腿接单服务","id":1,"type":"VIP"},{"period":12,"price":2,"name":"VIP钻石会员一年包","discount":0.9,"remark":"全网下单享9折，并享受跑腿接单服务","id":2,"type":"VIP"}]}
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
        private List<ProductsBean> products;

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * period : 12
             * price : 1
             * name : VIP黄金会员一年包
             * discount : 0.95
             * remark : 全网下单享9.5折，并享受跑腿接单服务
             * id : 1
             * type : VIP
             */

            private int period;
            private Double price;
            private String name;
            private String discount;
            private String remark;
            private int id;
            private String type;

            public int getPeriod() {
                return period;
            }

            public void setPeriod(int period) {
                this.period = period;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
