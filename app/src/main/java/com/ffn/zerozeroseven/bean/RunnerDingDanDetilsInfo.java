package com.ffn.zerozeroseven.bean;

public class RunnerDingDanDetilsInfo {
    /**
     * code : 0
     * data : {"deliveryPhone":"17777777777","isComment":0,"orderStatus":1,"remark":"易碎品","goodsWeight":"1","evaluation":5,"pickupTime":"2018-08-07 22:37:07","publisherId":90,"receiverId":96,"receiverPhone":"17388933063","errandIncome":18,"deliveryAddress":"郭村19","schoolId":1719,"payment":"AliPay","id":1,"orderNo":"11111111","receiverName":"湘江","goodsType":"易碎品","receiverAddress":"农大","realName":"会飞的猪","shippingFee":20,"createTime":"2018-08-10 20:27:31","phone":"15526452745","errandLevel":5,"deliveryName":"一号","payStatus":1}
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
         * deliveryPhone : 17777777777
         * isComment : 0
         * orderStatus : 1
         * remark : 易碎品
         * goodsWeight : 1
         * evaluation : 5
         * pickupTime : 2018-08-07 22:37:07
         * publisherId : 90
         * receiverId : 96
         * receiverPhone : 17388933063
         * errandIncome : 18
         * deliveryAddress : 郭村19
         * schoolId : 1719
         * payment : AliPay
         * id : 1
         * orderNo : 11111111
         * receiverName : 湘江
         * goodsType : 易碎品
         * receiverAddress : 农大
         * realName : 会飞的猪
         * shippingFee : 20
         * createTime : 2018-08-10 20:27:31
         * phone : 15526452745
         * errandLevel : 5
         * deliveryName : 一号
         * payStatus : 1
         */

        private String deliveryPhone;
        private int isComment;
        private int orderStatus;
        private String remark;
        private String goodsWeight;
        private int evaluation;
        private String pickupTime;
        private int publisherId;
        private int receiverId;
        private String receiverPhone;
        private int errandIncome;
        private String deliveryAddress;
        private int schoolId;
        private String payment;
        private int id;
        private String orderNo;
        private String receiverName;
        private String goodsType;
        private String receiverAddress;
        private String realName;
        private double shippingFee;
        private String createTime;
        private String phone;
        private int errandLevel;
        private String deliveryName;



        private int payStatus;

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public int getIsComment() {
            return isComment;
        }

        public void setIsComment(int isComment) {
            this.isComment = isComment;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getGoodsWeight() {
            return goodsWeight;
        }

        public void setGoodsWeight(String goodsWeight) {
            this.goodsWeight = goodsWeight;
        }

        public int getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(int evaluation) {
            this.evaluation = evaluation;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public int getPublisherId() {
            return publisherId;
        }

        public void setPublisherId(int publisherId) {
            this.publisherId = publisherId;
        }

        public int getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(int receiverId) {
            this.receiverId = receiverId;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }

        public int getErrandIncome() {
            return errandIncome;
        }

        public void setErrandIncome(int errandIncome) {
            this.errandIncome = errandIncome;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public double getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(double shippingFee) {
            this.shippingFee = shippingFee;
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

        public int getErrandLevel() {
            return errandLevel;
        }

        public void setErrandLevel(int errandLevel) {
            this.errandLevel = errandLevel;
        }

        public String getDeliveryName() {
            return deliveryName;
        }

        public void setDeliveryName(String deliveryName) {
            this.deliveryName = deliveryName;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }
    }
}
