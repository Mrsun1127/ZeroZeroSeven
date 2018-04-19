package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * Created by GT on 2017/12/5.
 */

public class CommitDingDanInfo implements Serializable {
    /**
     * code : 0
     * data : {"orderNo":"151315784887716","payment":"AliPay","body":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017120700422311&biz_content=%7B%22body%22%3A%22%E9%9B%B6%E9%9B%B67%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%22151315784887716%22%2C%22passback_params%22%3A%22%7B%5C%22orderType%5C%22%3A%5C%22GOODS%5C%22%7D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22quit_url%22%3A%22%22%2C%22subject%22%3A%22%E9%9B%B6%E9%9B%B67%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F218.76.7.150%3A8080%2Fxz-007-server%2Fcallback%2Falipay&sign=GsrtpRZK0GkRmkc%2Bhc4JxtaXLr4AzqOemDTsz8W9wCf%2Fw%2BfXHneQ%2BNigxGKGfg9p2mnWj11dnZ8vpfbkYGjqJQYxBGKbn%2B0%2Bm16vWld%2F7UxuIBA6W8X9nPAQMkFeTXZNLoWOqfwDLM%2BJqSk3TBP8zD0GkiPIOlMe%2Fc5%2Bp4pHrYGEvHtuLkDbFGRXZDtTvR3QRqGxVqFKb5BWeGOVC8d8e%2F1DOput%2FXNjx0Vx%2FchJDkjsaq6K7ZOcBiSn6dEZAqpJr49ifzmiGshJ6GlowNKoMQN4IU873N13eXNAGMCeXJzn83GON3B6mvEZ20%2Bza5w7jgVxtbS7sIBwDZl36efXAQ%3D%3D&sign_type=RSA2&timestamp=2017-12-13+17%3A37%3A28&version=1.0"}
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
         * orderNo : 151315784887716
         * payment : AliPay
         * body : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017120700422311&biz_content=%7B%22body%22%3A%22%E9%9B%B6%E9%9B%B67%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%22151315784887716%22%2C%22passback_params%22%3A%22%7B%5C%22orderType%5C%22%3A%5C%22GOODS%5C%22%7D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22quit_url%22%3A%22%22%2C%22subject%22%3A%22%E9%9B%B6%E9%9B%B67%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F218.76.7.150%3A8080%2Fxz-007-server%2Fcallback%2Falipay&sign=GsrtpRZK0GkRmkc%2Bhc4JxtaXLr4AzqOemDTsz8W9wCf%2Fw%2BfXHneQ%2BNigxGKGfg9p2mnWj11dnZ8vpfbkYGjqJQYxBGKbn%2B0%2Bm16vWld%2F7UxuIBA6W8X9nPAQMkFeTXZNLoWOqfwDLM%2BJqSk3TBP8zD0GkiPIOlMe%2Fc5%2Bp4pHrYGEvHtuLkDbFGRXZDtTvR3QRqGxVqFKb5BWeGOVC8d8e%2F1DOput%2FXNjx0Vx%2FchJDkjsaq6K7ZOcBiSn6dEZAqpJr49ifzmiGshJ6GlowNKoMQN4IU873N13eXNAGMCeXJzn83GON3B6mvEZ20%2Bza5w7jgVxtbS7sIBwDZl36efXAQ%3D%3D&sign_type=RSA2&timestamp=2017-12-13+17%3A37%3A28&version=1.0
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
