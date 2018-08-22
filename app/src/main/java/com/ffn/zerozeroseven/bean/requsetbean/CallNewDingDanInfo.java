package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/20.
 */

public class CallNewDingDanInfo {
    /**
     * id : 1
     * functionName : CallOrderPay
     * parameters : {"productId":"产品id（会员支付需要）","rechargePrice":"充值金额（选填,单位：分）","payment":"支付方式 WechatPay=微信支付,AliPay=支付宝支付","tradeType":"交易类型 H5=H5支付,APP=App支付","orderType":"订单类型 GOODS=商品订单,MEMBER=会员订单,RECHARGE=充值","orderJsonStr":"{\"storeId\":1,\"buildingName\":\"芷兰5栋\",\"receiverAddress\":\"receiverAddress\",\"receiverName\":\"receiverName\",\"receiverPhone\":\"receiverPhone\"}","orderDetailJsonStr":"[{\"goodsName\":\"苹果\",\"goodsId\":2,\"goodsType\":\"01\",\"goodsCount\":1},{\"goodsName\":\"香蕉\",\"goodsId\":3,\"goodsType\":\"01\",\"goodsCount\":1}]"}
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
         * productId : 产品id（会员支付需要）
         * rechargePrice : 充值金额（选填,单位：分）
         * payment : 支付方式 WechatPay=微信支付,AliPay=支付宝支付
         * tradeType : 交易类型 H5=H5支付,APP=App支付
         * orderType : 订单类型 GOODS=商品订单,MEMBER=会员订单,RECHARGE=充值
         * orderJsonStr : {"storeId":1,"buildingName":"芷兰5栋","receiverAddress":"receiverAddress","receiverName":"receiverName","receiverPhone":"receiverPhone"}
         * orderDetailJsonStr : [{"goodsName":"苹果","goodsId":2,"goodsType":"01","goodsCount":1},{"goodsName":"香蕉","goodsId":3,"goodsType":"01","goodsCount":1}]
         */

        private String productId;
        private String cate;

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        private String rechargePrice;
        private String payment;
        private String tradeType;
        private String orderType;
        private String orderJsonStr;
        private String orderDetailJsonStr;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getRechargePrice() {
            return rechargePrice;
        }

        public void setRechargePrice(String rechargePrice) {
            this.rechargePrice = rechargePrice;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderJsonStr() {
            return orderJsonStr;
        }

        public void setOrderJsonStr(String orderJsonStr) {
            this.orderJsonStr = orderJsonStr;
        }

        public String getOrderDetailJsonStr() {
            return orderDetailJsonStr;
        }

        public void setOrderDetailJsonStr(String orderDetailJsonStr) {
            this.orderDetailJsonStr = orderDetailJsonStr;
        }
    }
}
