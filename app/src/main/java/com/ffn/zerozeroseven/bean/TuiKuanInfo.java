package com.ffn.zerozeroseven.bean;

import java.util.List;

public class TuiKuanInfo {
    /**
     * code : 0
     * data : {"orderGoodsList":[{"costPrice":0,"goodsCount":1,"goodsId":3,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","goodsName":"苹果台式机","id":7,"marketPrice":13999,"orderNo":"153250175965101","shopPrice":9999,"specKey":"9_11_15","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配"}],"refundApply":{"createTime":"2018-08-20 18:02:32","id":2,"orderAmount":60,"orderNo":"153250175965101","orderType":"DIGITAL","payment":"WechatPay","refundAmount":60,"refundReason":"拍错 不想要 多拍 未按约定时间发货 ","refundRemark":"哦哦哦","refuseReason":"就是不退","schoolId":1719,"schoolName":"湖南农业大学","status":-2,"userId":90}}
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
         * orderGoodsList : [{"costPrice":0,"goodsCount":1,"goodsId":3,"goodsImage":"http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg","goodsName":"苹果台式机","id":7,"marketPrice":13999,"orderNo":"153250175965101","shopPrice":9999,"specKey":"9_11_15","specKeyName":"选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配"}]
         * refundApply : {"createTime":"2018-08-20 18:02:32","id":2,"orderAmount":60,"orderNo":"153250175965101","orderType":"DIGITAL","payment":"WechatPay","refundAmount":60,"refundReason":"拍错 不想要 多拍 未按约定时间发货 ","refundRemark":"哦哦哦","refuseReason":"就是不退","schoolId":1719,"schoolName":"湖南农业大学","status":-2,"userId":90}
         */

        private RefundApplyBean refundApply;
        private List<OrderGoodsListBean> orderGoodsList;

        public RefundApplyBean getRefundApply() {
            return refundApply;
        }

        public void setRefundApply(RefundApplyBean refundApply) {
            this.refundApply = refundApply;
        }

        public List<OrderGoodsListBean> getOrderGoodsList() {
            return orderGoodsList;
        }

        public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
            this.orderGoodsList = orderGoodsList;
        }

        public static class RefundApplyBean {
            /**
             * createTime : 2018-08-20 18:02:32
             * id : 2
             * orderAmount : 60
             * orderNo : 153250175965101
             * orderType : DIGITAL
             * payment : WechatPay
             * refundAmount : 60
             * refundReason : 拍错 不想要 多拍 未按约定时间发货
             * refundRemark : 哦哦哦
             * refuseReason : 就是不退
             * schoolId : 1719
             * schoolName : 湖南农业大学
             * status : -2
             * userId : 90
             */

            private String createTime;
            private int id;
            private int orderAmount;
            private String orderNo;
            private String orderType;
            private String payment;
            private int refundAmount;
            private String refundReason;
            private String refundRemark;
            private String refuseReason;
            private int schoolId;
            private String schoolName;
            private int status;
            private int userId;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(int orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public int getRefundAmount() {
                return refundAmount;
            }

            public void setRefundAmount(int refundAmount) {
                this.refundAmount = refundAmount;
            }

            public String getRefundReason() {
                return refundReason;
            }

            public void setRefundReason(String refundReason) {
                this.refundReason = refundReason;
            }

            public String getRefundRemark() {
                return refundRemark;
            }

            public void setRefundRemark(String refundRemark) {
                this.refundRemark = refundRemark;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public String getSchoolName() {
                return schoolName;
            }

            public void setSchoolName(String schoolName) {
                this.schoolName = schoolName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }

        public static class OrderGoodsListBean {
            /**
             * costPrice : 0
             * goodsCount : 1
             * goodsId : 3
             * goodsImage : http://www.xianjichina.com/data/editer/20170405/image/ff25b8a0fef9eebd70cd0454f696bde0.jpg
             * goodsName : 苹果台式机
             * id : 7
             * marketPrice : 13999
             * orderNo : 153250175965101
             * shopPrice : 9999
             * specKey : 9_11_15
             * specKeyName : 选择版本:全网通3G+32G 选择颜色:红色 套餐类型:官方标配
             */

            private Double costPrice;
            private int goodsCount;
            private int goodsId;
            private String goodsImage;
            private String goodsName;
            private int id;
            private Double marketPrice;
            private String orderNo;
            private Double shopPrice;
            private String specKey;
            private String specKeyName;

            public Double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(Double costPrice) {
                this.costPrice = costPrice;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(Double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public Double getShopPrice() {
                return shopPrice;
            }

            public void setShopPrice(Double shopPrice) {
                this.shopPrice = shopPrice;
            }

            public String getSpecKey() {
                return specKey;
            }

            public void setSpecKey(String specKey) {
                this.specKey = specKey;
            }

            public String getSpecKeyName() {
                return specKeyName;
            }

            public void setSpecKeyName(String specKeyName) {
                this.specKeyName = specKeyName;
            }
        }
    }
}
