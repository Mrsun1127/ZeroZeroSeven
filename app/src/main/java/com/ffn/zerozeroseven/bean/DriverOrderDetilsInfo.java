package com.ffn.zerozeroseven.bean;

public class DriverOrderDetilsInfo {
    /**
     * code : 0
     * data : {"drivingOrder":{"applicantAddress":"adr","applicantName":"扬州","applicantPhone":"17388933063","classId":2,"count":0,"createTime":"2018-09-19 14:39:38","drivingId":3,"drivingName":"明诚驾校","id":14,"idcard":"10086","isSignUp":0,"orderNo":"153733917887205","payment":"AliPay","profits":0.4,"status":1,"totalPrice":1,"userId":7136}}
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
         * drivingOrder : {"applicantAddress":"adr","applicantName":"扬州","applicantPhone":"17388933063","classId":2,"count":0,"createTime":"2018-09-19 14:39:38","drivingId":3,"drivingName":"明诚驾校","id":14,"idcard":"10086","isSignUp":0,"orderNo":"153733917887205","payment":"AliPay","profits":0.4,"status":1,"totalPrice":1,"userId":7136}
         */

        private DrivingOrderBean drivingOrder;

        public DrivingOrderBean getDrivingOrder() {
            return drivingOrder;
        }

        public void setDrivingOrder(DrivingOrderBean drivingOrder) {
            this.drivingOrder = drivingOrder;
        }

        public static class DrivingOrderBean {
            /**
             * applicantAddress : adr
             * applicantName : 扬州
             * applicantPhone : 17388933063
             * classId : 2
             * count : 0
             * createTime : 2018-09-19 14:39:38
             * drivingId : 3
             * drivingName : 明诚驾校
             * id : 14
             * idcard : 10086
             * isSignUp : 0
             * orderNo : 153733917887205
             * payment : AliPay
             * profits : 0.4
             * status : 1
             * totalPrice : 1
             * userId : 7136
             */

            private String applicantAddress;
            private String applicantName;
            private String applicantPhone;
            private int classId;
            private int count;
            private String createTime;
            private int drivingId;
            private String drivingName;
            private int id;
            private String idcard;
            private int isSignUp;
            private String orderNo;
            private String payment;
            private Double profits;
            private int status;
            private Double totalPrice;
            private int userId;

            public String getApplicantAddress() {
                return applicantAddress;
            }

            public void setApplicantAddress(String applicantAddress) {
                this.applicantAddress = applicantAddress;
            }

            public String getApplicantName() {
                return applicantName;
            }

            public void setApplicantName(String applicantName) {
                this.applicantName = applicantName;
            }

            public String getApplicantPhone() {
                return applicantPhone;
            }

            public void setApplicantPhone(String applicantPhone) {
                this.applicantPhone = applicantPhone;
            }

            public int getClassId() {
                return classId;
            }

            public void setClassId(int classId) {
                this.classId = classId;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDrivingId() {
                return drivingId;
            }

            public void setDrivingId(int drivingId) {
                this.drivingId = drivingId;
            }

            public String getDrivingName() {
                return drivingName;
            }

            public void setDrivingName(String drivingName) {
                this.drivingName = drivingName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public int getIsSignUp() {
                return isSignUp;
            }

            public void setIsSignUp(int isSignUp) {
                this.isSignUp = isSignUp;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public Double getProfits() {
                return profits;
            }

            public void setProfits(Double profits) {
                this.profits = profits;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
