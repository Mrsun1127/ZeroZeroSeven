package com.ffn.zerozeroseven.bean;

public class OrderLeaseJsonInfo {
    private String schoolId;
    private String receivingAddress;
    private String receivingPhone;
    private String deliveryName;
    private String postscript;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getReceivingPhone() {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }
}
