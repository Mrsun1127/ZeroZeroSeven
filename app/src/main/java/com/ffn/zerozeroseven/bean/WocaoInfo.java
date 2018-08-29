package com.ffn.zerozeroseven.bean;

public class WocaoInfo {
    /**
     * code : 0
     * data : {"errandCheckFee":0.01}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

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
         * errandCheckFee : 0.01
         */

        private double errandCheckFee;

        public double getErrandCheckFee() {
            return errandCheckFee;
        }

        public void setErrandCheckFee(double errandCheckFee) {
            this.errandCheckFee = errandCheckFee;
        }
    }
}
