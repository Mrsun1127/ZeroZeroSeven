package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/2.
 */

public class ShouHuoInfo implements Serializable {
    /**
     * code : 0
     * data : {"addresses":[{"contactBuilding":"东湖3栋","contactName":"哈哈","id":96,"contactPhone":"115","contactSchool":"湖南农业大学","contactDorm":"66"}]}
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
        private List<AddressesBean> addresses;

        public List<AddressesBean> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<AddressesBean> addresses) {
            this.addresses = addresses;
        }

        public static class AddressesBean {
            /**
             * contactBuilding : 东湖3栋
             * contactName : 哈哈
             * id : 96
             * contactPhone : 115
             * contactSchool : 湖南农业大学
             * contactDorm : 66
             */

            private String contactBuilding;
            private String contactName;
            private int id;
            private String contactPhone;
            private String contactSchool;
            private String contactDorm;
            private int isDefault;

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getContactBuilding() {
                return contactBuilding;
            }

            public void setContactBuilding(String contactBuilding) {
                this.contactBuilding = contactBuilding;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }

            public String getContactSchool() {
                return contactSchool;
            }

            public void setContactSchool(String contactSchool) {
                this.contactSchool = contactSchool;
            }

            public String getContactDorm() {
                return contactDorm;
            }

            public void setContactDorm(String contactDorm) {
                this.contactDorm = contactDorm;
            }
        }
    }
}
