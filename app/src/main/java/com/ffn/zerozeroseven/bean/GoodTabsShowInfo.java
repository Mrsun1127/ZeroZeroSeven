package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/3.
 */

public class GoodTabsShowInfo implements Serializable {
    public GoodTabsShowInfo(){}
    public GoodTabsShowInfo(int code, DataBean data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * code : 0
     * data : {"items":[{"orderNum":0,"dicKey":"01","dicValue":"零食","dicType":"GOODS_TYPE"},{"orderNum":0,"dicKey":"02","dicValue":"香烟","dicType":"GOODS_TYPE"},{"orderNum":0,"dicKey":"03","dicValue":"素食","dicType":"GOODS_TYPE"},{"orderNum":0,"dicKey":"04","dicValue":"日用","dicType":"GOODS_TYPE"},{"orderNum":0,"dicKey":"05","dicValue":"酒水","dicType":"GOODS_TYPE"}]}
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
        public DataBean(){}
        public DataBean(List<ItemsBean> items) {
            this.items = items;
        }

        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            public ItemsBean(){}
            public ItemsBean(int orderNum, String dicKey, String dicValue, String dicType) {
                this.orderNum = orderNum;
                this.dicKey = dicKey;
                this.dicValue = dicValue;
                this.dicType = dicType;
            }

            /**
             * orderNum : 0
             * dicKey : 01
             * dicValue : 零食
             * dicType : GOODS_TYPE
             */

            private int orderNum;
            private String dicKey;
            private String dicValue;
            private String dicType;

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public String getDicKey() {
                return dicKey;
            }

            public void setDicKey(String dicKey) {
                this.dicKey = dicKey;
            }

            public String getDicValue() {
                return dicValue;
            }

            public void setDicValue(String dicValue) {
                this.dicValue = dicValue;
            }

            public String getDicType() {
                return dicType;
            }

            public void setDicType(String dicType) {
                this.dicType = dicType;
            }
        }
    }
}
