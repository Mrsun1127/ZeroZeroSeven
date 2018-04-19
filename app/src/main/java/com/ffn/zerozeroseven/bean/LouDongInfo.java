package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/14.
 */

public class LouDongInfo implements Serializable {
    /**
     * code : 0
     * data : {"buildingNames":["东湖1栋","东湖2栋","东湖3栋","东湖4栋","东湖5栋","东湖6栋","丰泽1栋","丰泽2栋","丰泽3栋","丰泽4栋","丰泽5栋","丰泽6栋","丰泽7栋","芷兰10栋","芷兰11栋","芷兰12栋","芷兰13栋","芷兰14栋","芷兰15栋","芷兰16栋","芷兰17栋","芷兰5栋","芷兰6栋","芷兰7栋","芷兰8栋","芷兰9栋","金岸1栋","金岸2栋","金岸3栋","金岸4栋","金岸5栋","金岸6栋","金岸7栋","金岸8栋","金岸9栋"]}
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
        private List<String> buildingNames;

        public List<String> getBuildingNames() {
            return buildingNames;
        }

        public void setBuildingNames(List<String> buildingNames) {
            this.buildingNames = buildingNames;
        }
    }
}
