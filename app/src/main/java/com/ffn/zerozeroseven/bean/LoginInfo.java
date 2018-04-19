package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/11/30.
 */

public class LoginInfo implements Serializable {
    /**
     * code : 0
     * data : {"income":0,"balance":0,"phone":"18613991581","id":15,"token":"eyJhbGciOiJIUzUxMiJ9.eyJwcml2aWxlZ2VzIjpudWxsLCJzdWIiOiIxODYxMzk5MTU4MSIsImlkIjoxNSwiZXhwIjoxNTEyNjEyNzQ2LCJpYXQiOjE1MTIwMDc5NDZ9.rOezhFchziLHb2P4zRASHLtFa-zcICv-xR_h2Tt8P-n7rMCefD6-zLZQFL18AMPMEg5WpjaG5ja705xFr-GORw"}
     * message :
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
         * income : 0
         * balance : 0
         * phone : 18613991581
         * id : 15
         * token : eyJhbGciOiJIUzUxMiJ9.eyJwcml2aWxlZ2VzIjpudWxsLCJzdWIiOiIxODYxMzk5MTU4MSIsImlkIjoxNSwiZXhwIjoxNTEyNjEyNzQ2LCJpYXQiOjE1MTIwMDc5NDZ9.rOezhFchziLHb2P4zRASHLtFa-zcICv-xR_h2Tt8P-n7rMCefD6-zLZQFL18AMPMEg5WpjaG5ja705xFr-GORw
         */

        private int income;
        private int balance;
        private String phone;
        private String id;
        private String token;

        public int getIncome() {
            return income;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
