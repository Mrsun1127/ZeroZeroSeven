package com.ffn.zerozeroseven.bean.requsetbean;



public class RDriverDetilsInfo {

    /**
     * id : 1
     * functionName : QuerySchoolStore
     * parameters : {"schoolId":1}
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
         */
    private String drivingId;

        public String getDrivingId() {
            return drivingId;
        }

        public void setDrivingId(String drivingId) {
            this.drivingId = drivingId;
        }
    }
}
