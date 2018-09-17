package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class DriverDetilsInfo implements Serializable{
    /**
     * code : 0
     * data : {"drivingSchool":{"address":"车站中路","contact":"17621247369","content":"<table>培训机构<tr><td>全国连锁<\/td><\/tr><\/table>","countSignUp":0,"drivingClassList":[{"id":2,"name":"C2-急速班","totalPrice":3000}],"drivingGalleryList":[],"drivingPlaceList":[],"id":3,"name":"明诚驾校","notice":"培训机构"}}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean implements Serializable{
        /**
         * drivingSchool : {"address":"车站中路","contact":"17621247369","content":"<table>培训机构<tr><td>全国连锁<\/td><\/tr><\/table>","countSignUp":0,"drivingClassList":[{"id":2,"name":"C2-急速班","totalPrice":3000}],"drivingGalleryList":[],"drivingPlaceList":[],"id":3,"name":"明诚驾校","notice":"培训机构"}
         */

        private DrivingSchoolBean drivingSchool;

        public DrivingSchoolBean getDrivingSchool() {
            return drivingSchool;
        }

        public void setDrivingSchool(DrivingSchoolBean drivingSchool) {
            this.drivingSchool = drivingSchool;
        }

        public static class DrivingSchoolBean  implements Serializable{
            /**
             * address : 车站中路
             * contact : 17621247369
             * content : <table>培训机构<tr><td>全国连锁</td></tr></table>
             * countSignUp : 0
             * drivingClassList : [{"id":2,"name":"C2-急速班","totalPrice":3000}]
             * drivingGalleryList : []
             * drivingPlaceList : []
             * id : 3
             * name : 明诚驾校
             * notice : 培训机构
             */

            private String address;
            private String contact;
            private String content;
            private int countSignUp;
            private int id;
            private String name;
            private String notice;
            private List<DrivingClassListBean> drivingClassList;
            private List<?> drivingGalleryList;
            private List<?> drivingPlaceList;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCountSignUp() {
                return countSignUp;
            }

            public void setCountSignUp(int countSignUp) {
                this.countSignUp = countSignUp;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public List<DrivingClassListBean> getDrivingClassList() {
                return drivingClassList;
            }

            public void setDrivingClassList(List<DrivingClassListBean> drivingClassList) {
                this.drivingClassList = drivingClassList;
            }

            public List<?> getDrivingGalleryList() {
                return drivingGalleryList;
            }

            public void setDrivingGalleryList(List<?> drivingGalleryList) {
                this.drivingGalleryList = drivingGalleryList;
            }

            public List<?> getDrivingPlaceList() {
                return drivingPlaceList;
            }

            public void setDrivingPlaceList(List<?> drivingPlaceList) {
                this.drivingPlaceList = drivingPlaceList;
            }

            public static class DrivingClassListBean  implements Serializable{
                /**
                 * id : 2
                 * name : C2-急速班
                 * totalPrice : 3000
                 */

                private int id;
                private String name;
                private Double totalPrice;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Double getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(Double totalPrice) {
                    this.totalPrice = totalPrice;
                }
            }
        }
    }
}
