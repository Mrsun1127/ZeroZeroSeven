package com.ffn.zerozeroseven.bean;

public class RunnerInfo  {

    /**
     * code : 0
     * data : {"realName":"杨洲","checkStatus":1,"createTime":"2018-08-06 10:42:09","phone":"17388933063","idcard":"430421199810239875","schoolId":1719,"sex":1,"starLevel":5,"schoolName":"湖南农业大学","payStatus":1,"userId":90,"refuseReason":"姓名不真实"}
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
        /**
         * realName : 杨洲
         * checkStatus : 1
         * createTime : 2018-08-06 10:42:09
         * phone : 17388933063
         * idcard : 430421199810239875
         * schoolId : 1719
         * sex : 1
         * starLevel : 5
         * schoolName : 湖南农业大学
         * payStatus : 1
         * userId : 90
         * refuseReason : 姓名不真实
         */

        private String realName;
        private int checkStatus;
        private String createTime;
        private String phone;
        private String idcard;
        private int schoolId;
        private int sex;
        private int starLevel;
        private String schoolName;
        private int payStatus;
        private int userId;
        private String refuseReason;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStarLevel() {
            return starLevel;
        }

        public void setStarLevel(int starLevel) {
            this.starLevel = starLevel;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getRefuseReason() {
            return refuseReason;
        }

        public void setRefuseReason(String refuseReason) {
            this.refuseReason = refuseReason;
        }
    }
}
