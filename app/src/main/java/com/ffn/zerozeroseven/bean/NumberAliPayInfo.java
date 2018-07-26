package com.ffn.zerozeroseven.bean;

public class NumberAliPayInfo {
    /**
     * code : 0
     * data : {"orderNo":"153257449743602","payment":"AliPay","body":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017120700422311&biz_content=%7B%22body%22%3A%22%E9%9B%B6%E9%9B%B67%E6%95%B0%E7%A0%81%22%2C%22out_trade_no%22%3A%22153257449743602%22%2C%22passback_params%22%3A%22%7B%5C%22orderType%5C%22%3A%5C%22DIGITAL%5C%22%2C%5C%22payType%5C%22%3A%5C%22BALANCE%5C%22%7D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22quit_url%22%3A%22%22%2C%22subject%22%3A%22%E9%9B%B6%E9%9B%B67%E6%95%B0%E7%A0%81%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%229939.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fc39d6b5d.ngrok.io%2Flingling7-server%2Fcallback%2Fpay%2Falipay&sign=NpQfvyJg08wh844yFcGtHHJMlf9jadJIIBrbgueEI8kLTp7YTcnKBgFAbsZQFnFPRoP7ZJRtytOpICdIH3AddQVGxtm2w8pg10iVvPhhUwIMi%2ByX45xKphX8SLY4%2BuxN5z68NdEpiGbetCArWNpmH4swLfo6XAjXbpakoVHPD%2BOqTg6%2BcvsLJCzcc3QNUcmagr%2BHYWXzOV8VBgKP2q3AMpyrp8fRTHfffNsQwjIrgXdv1jZNiqgNRe0mYhDBLiAtUsYz6678%2BZ3BasXdtoRSFZTA3dcK733PNPePubKpaOiY18oMv7rgLY%2Ff3lyMDloJFz1UZsXbfdrkFL9m52N1EQ%3D%3D&sign_type=RSA2&timestamp=2018-07-26+11%3A08%3A17&version=1.0"}
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
         * orderNo : 153257449743602
         * payment : AliPay
         * body : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017120700422311&biz_content=%7B%22body%22%3A%22%E9%9B%B6%E9%9B%B67%E6%95%B0%E7%A0%81%22%2C%22out_trade_no%22%3A%22153257449743602%22%2C%22passback_params%22%3A%22%7B%5C%22orderType%5C%22%3A%5C%22DIGITAL%5C%22%2C%5C%22payType%5C%22%3A%5C%22BALANCE%5C%22%7D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22quit_url%22%3A%22%22%2C%22subject%22%3A%22%E9%9B%B6%E9%9B%B67%E6%95%B0%E7%A0%81%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%229939.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fc39d6b5d.ngrok.io%2Flingling7-server%2Fcallback%2Fpay%2Falipay&sign=NpQfvyJg08wh844yFcGtHHJMlf9jadJIIBrbgueEI8kLTp7YTcnKBgFAbsZQFnFPRoP7ZJRtytOpICdIH3AddQVGxtm2w8pg10iVvPhhUwIMi%2ByX45xKphX8SLY4%2BuxN5z68NdEpiGbetCArWNpmH4swLfo6XAjXbpakoVHPD%2BOqTg6%2BcvsLJCzcc3QNUcmagr%2BHYWXzOV8VBgKP2q3AMpyrp8fRTHfffNsQwjIrgXdv1jZNiqgNRe0mYhDBLiAtUsYz6678%2BZ3BasXdtoRSFZTA3dcK733PNPePubKpaOiY18oMv7rgLY%2Ff3lyMDloJFz1UZsXbfdrkFL9m52N1EQ%3D%3D&sign_type=RSA2&timestamp=2018-07-26+11%3A08%3A17&version=1.0
         */

        private String orderNo;
        private String payment;
        private String body;

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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
