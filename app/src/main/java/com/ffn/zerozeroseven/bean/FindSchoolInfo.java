package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/12/8.
 */

public class FindSchoolInfo implements Serializable {
    /**
     * code : 0
     * data : {"isRecommend":0,"province":"110000","city":"430100","name":"湖南信息职业技术学院","fullName":"湖南信息职业技术学院","id":1756}
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
         * isRecommend : 0
         * province : 110000
         * city : 430100
         * name : 湖南信息职业技术学院
         * fullName : 湖南信息职业技术学院
         * id : 1756
         */

        private String isRecommend;
        private String province;
        private String city;
        private String name;
        private String fullName;
        private int id;

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
