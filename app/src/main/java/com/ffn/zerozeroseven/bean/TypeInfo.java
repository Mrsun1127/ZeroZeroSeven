package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/17.
 */

public class TypeInfo implements Serializable {
    /**
     * code : 0
     * data : {"items":[{"orderNum":0,"dicKey":"01","dicValue":"表白贴","dicType":"POST_TYPE"},{"orderNum":0,"dicKey":"02","dicValue":"技术贴","dicType":"POST_TYPE"},{"orderNum":0,"dicKey":"03","dicValue":"寻物贴","dicType":"POST_TYPE"},{"orderNum":0,"dicKey":"04","dicValue":"交友贴","dicType":"POST_TYPE"}]}
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
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean implements Serializable{
            /**
             * orderNum : 0
             * dicKey : 01
             * dicValue : 表白贴
             * dicType : POST_TYPE
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
