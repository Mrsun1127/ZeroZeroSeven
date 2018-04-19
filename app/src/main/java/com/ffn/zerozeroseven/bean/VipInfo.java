package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2018/2/5.
 */

public class VipInfo implements Serializable {
    /**
     * code : 0
     * data : {"birthday":"2017.12.20","college":"体育","honerPoint":0,"income":0,"avatar":"http://218.76.7.150:8080/middle-res/007/image/20180105/1515143232472.jpeg","isMember":0,"alipayAccount":"","honerLevel":1,"token":"eyJhbGciOiJIUzUxMiJ9.eyJwcml2aWxlZ2VzIjpudWxsLCJzdWIiOiIxODYxMzk5MTU4MiIsInNjaG9vbElkIjpudWxsLCJpZCI6IjI0IiwiZXhwIjoxNTE4MTY0MzE4LCJpYXQiOjE1MTc1NTk1MTh9.akqLyFETSt6gVULbRpaA04boxfD4HNc39cdQJB62BJRDaaWdFSBvfSojhrjTWThtYz8FE_giVfH4DStNp12FCg","realName":"意境","balance":377.2,"phone":"18613991582","inviteCode":"cdbMFmGu","id":24,"clazz":"体育1426"}
     * message : 请求成功
     */

    private int code;
    private UserInfo.DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserInfo.DataBean getData() {
        return data;
    }

    public void setData(UserInfo.DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean  implements Serializable{
        /**
         * birthday : 2017.12.20
         * college : 体育
         * honerPoint : 0
         * income : 0
         * avatar : http://218.76.7.150:8080/middle-res/007/image/20180105/1515143232472.jpeg
         * isMember : 0
         * alipayAccount :
         * honerLevel : 1
         * token : eyJhbGciOiJIUzUxMiJ9.eyJwcml2aWxlZ2VzIjpudWxsLCJzdWIiOiIxODYxMzk5MTU4MiIsInNjaG9vbElkIjpudWxsLCJpZCI6IjI0IiwiZXhwIjoxNTE4MTY0MzE4LCJpYXQiOjE1MTc1NTk1MTh9.akqLyFETSt6gVULbRpaA04boxfD4HNc39cdQJB62BJRDaaWdFSBvfSojhrjTWThtYz8FE_giVfH4DStNp12FCg
         * realName : 意境
         * balance : 377.2
         * phone : 18613991582
         * inviteCode : cdbMFmGu
         * id : 24
         * clazz : 体育1426
         */

        private String birthday;
        private String college;
        private int honerPoint;
        private Double income=0.0;
        private String avatar;
        private int isMember;
        private String alipayAccount;
        private int honerLevel;
        private String token;
        private String realName;
        private Double balance=0.0;
        private String phone;
        private String inviteCode;
        private int id;
        private String clazz;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public int getHonerPoint() {
            return honerPoint;
        }

        public void setHonerPoint(int honerPoint) {
            this.honerPoint = honerPoint;
        }

        public Double getIncome() {
            return income;
        }

        public void setIncome(Double income) {
            this.income = income;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getIsMember() {
            return isMember;
        }

        public void setIsMember(int isMember) {
            this.isMember = isMember;
        }

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        public int getHonerLevel() {
            return honerLevel;
        }

        public void setHonerLevel(int honerLevel) {
            this.honerLevel = honerLevel;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }
    }
}
