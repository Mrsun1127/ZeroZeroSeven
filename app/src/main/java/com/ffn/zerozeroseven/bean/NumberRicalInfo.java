package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class NumberRicalInfo implements Serializable {
    private List<RicalInfo> numberRicalListInfo;

    public List<RicalInfo> getNumberRicalListInfo() {
        return numberRicalListInfo;
    }

    public void setNumberRicalListInfo(List<RicalInfo> numberRicalListInfo) {
        this.numberRicalListInfo = numberRicalListInfo;
    }

    public static class RicalInfo implements Serializable {
        private int id;
        private int type;//1 电脑  2 手机  3 其他
        private String imgUrl;//图片路径1
        private String name;//名字
        private boolean subScribe;//是否预约
        private int count; //购买数量
        private boolean isChecked;//是否选中
        private Double needsMoney;//需要人民币
        private String configuration;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConfiguration() {
            return configuration;
        }

        public void setConfiguration(String configuration) {
            this.configuration = configuration;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSubScribe() {
            return subScribe;
        }

        public void setSubScribe(boolean subScribe) {
            this.subScribe = subScribe;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public Double getNeedsMoney() {
            return needsMoney;
        }

        public void setNeedsMoney(Double needsMoney) {
            this.needsMoney = needsMoney;
        }
    }
}