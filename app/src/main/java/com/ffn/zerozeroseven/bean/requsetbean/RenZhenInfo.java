package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/5.
 */

public class RenZhenInfo {
    /**
     * id : 1
     * functionName : ValidateCourier
     * parameters : {"phone":"手机号码","courierNo":"快递员身份码"}
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
         * phone : 手机号码
         * courierNo : 快递员身份码
         */

        private String phone;
        private String courierNo;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCourierNo() {
            return courierNo;
        }

        public void setCourierNo(String courierNo) {
            this.courierNo = courierNo;
        }
    }
}
