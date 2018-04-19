package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2018/1/25.
 */

public class RleaseRunInfo {
    /**
     * id : 1
     * functionName : AddErrandOrder
     * parameters : {"type":"跑腿类型","schoolId":"学校id","pickupAddress":"取件地址","deliverAddress":"送货地址","weight":"物品重量","price":"价格","pickupTime":"取件时间","expressCompany":"快递公司","courierPhone":"快递员号码","fee":"小费：默认0，单位：分"}
     */

    private String id;
    private String functionName;
    private ParametersBean parameters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public ParametersBean getParameters() {
        return parameters;
    }

    public void setParameters(ParametersBean parameters) {
        this.parameters = parameters;
    }

    public static class ParametersBean {
        /**
         * type : 跑腿类型
         * schoolId : 学校id
         * pickupAddress : 取件地址
         * deliverAddress : 送货地址
         * weight : 物品重量
         * price : 价格
         * pickupTime : 取件时间
         * expressCompany : 快递公司
         * courierPhone : 快递员号码
         * fee : 小费：默认0，单位：分
         */

        private String type;
        private String schoolId;
        private String pickupAddress;
        private String deliverAddress;
        private String weight;
        private String price;
        private String pickupTime;
        private String expressCompany;
        private String courierPhone;
        private String fee;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getPickupAddress() {
            return pickupAddress;
        }

        public void setPickupAddress(String pickupAddress) {
            this.pickupAddress = pickupAddress;
        }

        public String getDeliverAddress() {
            return deliverAddress;
        }

        public void setDeliverAddress(String deliverAddress) {
            this.deliverAddress = deliverAddress;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getExpressCompany() {
            return expressCompany;
        }

        public void setExpressCompany(String expressCompany) {
            this.expressCompany = expressCompany;
        }

        public String getCourierPhone() {
            return courierPhone;
        }

        public void setCourierPhone(String courierPhone) {
            this.courierPhone = courierPhone;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }
    }
}
