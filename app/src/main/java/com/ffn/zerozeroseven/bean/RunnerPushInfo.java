package com.ffn.zerozeroseven.bean;

public class    RunnerPushInfo {

    /**
     * orderNo : 订单号
     * action : ERRAND_ORDER
     * title : 跑腿已接单
     * content : 您的跑腿已有人接单
     */

    private String orderNo;
    private String action;
    private String title;
    private String content;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
