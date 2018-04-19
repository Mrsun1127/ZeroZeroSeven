package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/12/10.
 */

public class CuriousInfo implements Serializable {
    /**
     * code : 0
     * data : {"courierName":"杨雄浩","orderScope":"湖南师范大学：1舍 2舍","phone":"15810446482","id":2,"courierNo":"123","status":2}
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
         * courierName : 杨雄浩
         * orderScope : 湖南师范大学：1舍 2舍
         * phone : 15810446482
         * id : 2
         * courierNo : 123
         * status : 2
         */

        private String courierName;
        private String orderScope;
        private String phone;
        private int id;
        private String courierNo;
        private int status;

        public String getCourierName() {
            return courierName;
        }

        public void setCourierName(String courierName) {
            this.courierName = courierName;
        }

        public String getOrderScope() {
            return orderScope;
        }

        public void setOrderScope(String orderScope) {
            this.orderScope = orderScope;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCourierNo() {
            return courierNo;
        }

        public void setCourierNo(String courierNo) {
            this.courierNo = courierNo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
