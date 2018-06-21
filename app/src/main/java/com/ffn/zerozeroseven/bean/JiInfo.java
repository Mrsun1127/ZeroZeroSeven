package com.ffn.zerozeroseven.bean;

public class JiInfo {
    /**
     * code : 0
     * data : {"honerPoint":96}
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
         * honerPoint : 96
         */

        private int honerPoint;

        public int getHonerPoint() {
            return honerPoint;
        }

        public void setHonerPoint(int honerPoint) {
            this.honerPoint = honerPoint;
        }
    }
}
