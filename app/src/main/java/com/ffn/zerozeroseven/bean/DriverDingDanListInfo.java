package com.ffn.zerozeroseven.bean;

import java.util.List;

public class DriverDingDanListInfo {
    /**
     * code : 0
     * data : {"total":1,"pageIndex":0,"totalPage":1,"pageSize":6,"list":[{"classId":2,"className":"C2-急速班","count":0,"createTime":"2018-09-19 14:39:38","drivingId":3,"drivingName":"明诚驾校","id":14,"isApplyRefund":false,"isSignUp":1,"orderNo":"153733917887205","status":1,"totalPrice":1,"userId":7136}]}
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
         * total : 1
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 6
         * list : [{"classId":2,"className":"C2-急速班","count":0,"createTime":"2018-09-19 14:39:38","drivingId":3,"drivingName":"明诚驾校","id":14,"isApplyRefund":false,"isSignUp":1,"orderNo":"153733917887205","status":1,"totalPrice":1,"userId":7136}]
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
             * classId : 2
             * className : C2-急速班
             * count : 0
             * createTime : 2018-09-19 14:39:38
             * drivingId : 3
             * drivingName : 明诚驾校
             * id : 14
             * isApplyRefund : false
             * isSignUp : 1
             * orderNo : 153733917887205
             * status : 1
             * totalPrice : 1
             * userId : 7136
             */

            private int classId;
            private String className;
            private int count;
            private String createTime;
            private int drivingId;
            private String drivingName;
            private int id;
            private boolean isApplyRefund;
            private int isSignUp;
            private String orderNo;
            private int status;
            private Double totalPrice;
            private int userId;
            private String picUrl;

            public boolean isApplyRefund() {
                return isApplyRefund;
            }

            public void setApplyRefund(boolean applyRefund) {
                isApplyRefund = applyRefund;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getClassId() {
                return classId;
            }

            public void setClassId(int classId) {
                this.classId = classId;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
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

            public boolean isIsApplyRefund() {
                return isApplyRefund;
            }

            public void setIsApplyRefund(boolean isApplyRefund) {
                this.isApplyRefund = isApplyRefund;
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
