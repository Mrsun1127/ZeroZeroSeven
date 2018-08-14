package com.ffn.zerozeroseven.bean;

public class RunnerCountInfo {
    /**
     * code : 0
     * data : {"income":0,"receiveOrderCount":0,"haveOrderCount":0}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * income : 0
         * receiveOrderCount : 0
         * haveOrderCount : 0
         */

        private Double income;
        private int receiveOrderCount;
        private int haveOrderCount;


        public Double getIncome() {
            return income;
        }

        public void setIncome(Double income) {
            this.income = income;
        }

        public int getReceiveOrderCount() {
            return receiveOrderCount;
        }

        public void setReceiveOrderCount(int receiveOrderCount) {
            this.receiveOrderCount = receiveOrderCount;
        }

        public int getHaveOrderCount() {
            return haveOrderCount;
        }

        public void setHaveOrderCount(int haveOrderCount) {
            this.haveOrderCount = haveOrderCount;
        }
    }
}
