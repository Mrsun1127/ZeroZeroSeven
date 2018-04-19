package com.ffn.zerozeroseven.bean.requsetbean;

/**
 * Created by GT on 2017/12/14.
 */

public class AddNewAdrInfo {

    /**
     * id : 1
     * functionName : AddUserAddress
     * parameters : {"contactAddress":"联系人地址","contactName":"联系人姓名","contactPhone":"联系人手机号码"}
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
        private int schoolId;

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }
    }
}
