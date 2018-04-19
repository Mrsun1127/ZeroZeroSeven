package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/1.
 */

public class UpdateInfo {
    /**
     * id : 1
     * functionName : UpdateUserDetail
     * parameters : {"birthday":"生日(选填)","realName":"真实姓名","alipayAccount":"支付宝帐号(选填)","college":"学院(选填)","clazz":"班级名称(选填)"}
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
         * birthday : 生日(选填)
         * realName : 真实姓名
         * alipayAccount : 支付宝帐号(选填)
         * college : 学院(选填)
         * clazz : 班级名称(选填)
         */

        private String birthday;
        private String realName;
        private String alipayAccount;
        private String college;
        private String clazz;
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }
    }
}
