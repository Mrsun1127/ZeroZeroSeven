package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/2.
 */

public class UpDateAdrInfo {
    /**
     * id : 1
     * functionName : UpdateUserAddress
     * parameters : {"id":1,"contactAddress":"联系人地址","contactName":"联系人姓名","contactPhone":"联系人手机号码"}
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
         * id : 1
         * contactAddress : 联系人地址
         * contactName : 联系人姓名
         * contactPhone : 联系人手机号码
         */

        private int id;
        private String contactSchool;
        private String contactName;
        private String contactPhone;
        private String contactBuilding;
        private String contactDorm;
        private int buildingId;

        public String getContactSchool() {
            return contactSchool;
        }

        public void setContactSchool(String contactSchool) {
            this.contactSchool = contactSchool;
        }

        public String getContactBuilding() {
            return contactBuilding;
        }

        public void setContactBuilding(String contactBuilding) {
            this.contactBuilding = contactBuilding;
        }

        public String getContactDorm() {
            return contactDorm;
        }

        public void setContactDorm(String contactDorm) {
            this.contactDorm = contactDorm;
        }

        public int getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(int buildingId) {
            this.buildingId = buildingId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }



        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }
    }
}
