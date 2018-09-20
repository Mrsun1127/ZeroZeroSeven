package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class DriverDetilsInfo implements Serializable{
    /**
     * code : 0
     * data : {"drivingSchool":{"drivingPlaceList":[{"address":"芙蓉区嘉雨路","drivingId":2,"city":"长沙市","thumb":"http://192.168.3.199/lingling7-res/image/20180920/thumbnail1537411424477.png","name":"练车场1号","id":1,"examScope":"科目一。科目二"},{"address":"练车场2号","drivingId":2,"city":"长沙市","thumb":"http://img3.imgtn.bdimg.com/it/u=1608582903,1571872890&fm=27&gp=0.jpg","name":"练车场2号","id":2,"examScope":"科目一。科目二"},{"address":"练车场3号","drivingId":2,"city":"长沙市","thumb":"http://img3.imgtn.bdimg.com/it/u=1608582903,1571872890&fm=27&gp=0.jpg","name":"练车场3号","id":3,"examScope":"科目一。科目二"}],"address":"芙蓉区远大二路247号","contact":"17621247369","latitude":22,"name":"长沙机电驾校","countSignUp":0,"drivingGalleryList":[{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png","drivingId":2,"id":7},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754885.png","drivingId":2,"id":8},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754887.png","drivingId":2,"id":9},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png","drivingId":2,"id":10},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754885.png","drivingId":2,"id":11},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754887.png","drivingId":2,"id":12}],"drivingClassList":[{"totalPrice":1,"name":"C1-急速班","id":1},{"totalPrice":1,"name":"C1-急速班2","id":3}],"id":2,"content":"<table>培训机构<tr><td>全国连锁<\/td><\/tr><\/table>","longitude":22,"notice":"培训机构"}}
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
        /**
         * drivingSchool : {"drivingPlaceList":[{"address":"芙蓉区嘉雨路","drivingId":2,"city":"长沙市","thumb":"http://192.168.3.199/lingling7-res/image/20180920/thumbnail1537411424477.png","name":"练车场1号","id":1,"examScope":"科目一。科目二"},{"address":"练车场2号","drivingId":2,"city":"长沙市","thumb":"http://img3.imgtn.bdimg.com/it/u=1608582903,1571872890&fm=27&gp=0.jpg","name":"练车场2号","id":2,"examScope":"科目一。科目二"},{"address":"练车场3号","drivingId":2,"city":"长沙市","thumb":"http://img3.imgtn.bdimg.com/it/u=1608582903,1571872890&fm=27&gp=0.jpg","name":"练车场3号","id":3,"examScope":"科目一。科目二"}],"address":"芙蓉区远大二路247号","contact":"17621247369","latitude":22,"name":"长沙机电驾校","countSignUp":0,"drivingGalleryList":[{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png","drivingId":2,"id":7},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754885.png","drivingId":2,"id":8},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754887.png","drivingId":2,"id":9},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png","drivingId":2,"id":10},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754885.png","drivingId":2,"id":11},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754887.png","drivingId":2,"id":12}],"drivingClassList":[{"totalPrice":1,"name":"C1-急速班","id":1},{"totalPrice":1,"name":"C1-急速班2","id":3}],"id":2,"content":"<table>培训机构<tr><td>全国连锁<\/td><\/tr><\/table>","longitude":22,"notice":"培训机构"}
         */

        private DrivingSchoolBean drivingSchool;

        public DrivingSchoolBean getDrivingSchool() {
            return drivingSchool;
        }

        public void setDrivingSchool(DrivingSchoolBean drivingSchool) {
            this.drivingSchool = drivingSchool;
        }

        public static class DrivingSchoolBean implements Serializable{
            /**
             * drivingPlaceList : [{"address":"芙蓉区嘉雨路","drivingId":2,"city":"长沙市","thumb":"http://192.168.3.199/lingling7-res/image/20180920/thumbnail1537411424477.png","name":"练车场1号","id":1,"examScope":"科目一。科目二"},{"address":"练车场2号","drivingId":2,"city":"长沙市","thumb":"http://img3.imgtn.bdimg.com/it/u=1608582903,1571872890&fm=27&gp=0.jpg","name":"练车场2号","id":2,"examScope":"科目一。科目二"},{"address":"练车场3号","drivingId":2,"city":"长沙市","thumb":"http://img3.imgtn.bdimg.com/it/u=1608582903,1571872890&fm=27&gp=0.jpg","name":"练车场3号","id":3,"examScope":"科目一。科目二"}]
             * address : 芙蓉区远大二路247号
             * contact : 17621247369
             * latitude : 22
             * name : 长沙机电驾校
             * countSignUp : 0
             * drivingGalleryList : [{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png","drivingId":2,"id":7},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754885.png","drivingId":2,"id":8},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754887.png","drivingId":2,"id":9},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png","drivingId":2,"id":10},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754885.png","drivingId":2,"id":11},{"picUrl":"http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754887.png","drivingId":2,"id":12}]
             * drivingClassList : [{"totalPrice":1,"name":"C1-急速班","id":1},{"totalPrice":1,"name":"C1-急速班2","id":3}]
             * id : 2
             * content : <table>培训机构<tr><td>全国连锁</td></tr></table>
             * longitude : 22
             * notice : 培训机构
             */

            private String address;
            private String contact;
            private Double latitude;
            private String name;
            private int countSignUp;
            private int id;
            private String content;
            private Double longitude;
            private String notice;
            private List<DrivingPlaceListBean> drivingPlaceList;
            private List<DrivingGalleryListBean> drivingGalleryList;
            private List<DrivingClassListBean> drivingClassList;

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

            public Double getLatitude() {
                return latitude;
            }

            public void setLatitude(Double latitude) {
                this.latitude = latitude;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Double getLongitude() {
                return longitude;
            }

            public void setLongitude(Double longitude) {
                this.longitude = longitude;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public List<DrivingPlaceListBean> getDrivingPlaceList() {
                return drivingPlaceList;
            }

            public void setDrivingPlaceList(List<DrivingPlaceListBean> drivingPlaceList) {
                this.drivingPlaceList = drivingPlaceList;
            }

            public List<DrivingGalleryListBean> getDrivingGalleryList() {
                return drivingGalleryList;
            }

            public void setDrivingGalleryList(List<DrivingGalleryListBean> drivingGalleryList) {
                this.drivingGalleryList = drivingGalleryList;
            }

            public List<DrivingClassListBean> getDrivingClassList() {
                return drivingClassList;
            }

            public void setDrivingClassList(List<DrivingClassListBean> drivingClassList) {
                this.drivingClassList = drivingClassList;
            }

            public static class DrivingPlaceListBean implements Serializable{
                /**
                 * address : 芙蓉区嘉雨路
                 * drivingId : 2
                 * city : 长沙市
                 * thumb : http://192.168.3.199/lingling7-res/image/20180920/thumbnail1537411424477.png
                 * name : 练车场1号
                 * id : 1
                 * examScope : 科目一。科目二
                 */

                private String address;
                private int drivingId;
                private String city;
                private String thumb;
                private String name;
                private int id;
                private String examScope;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getDrivingId() {
                    return drivingId;
                }

                public void setDrivingId(int drivingId) {
                    this.drivingId = drivingId;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getExamScope() {
                    return examScope;
                }

                public void setExamScope(String examScope) {
                    this.examScope = examScope;
                }
            }

            public static class DrivingGalleryListBean implements Serializable{
                /**
                 * picUrl : http://192.168.3.199/lingling7-res/image/20180919/thumbnail1537340754875.png
                 * drivingId : 2
                 * id : 7
                 */

                private String picUrl;
                private int drivingId;
                private int id;

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public int getDrivingId() {
                    return drivingId;
                }

                public void setDrivingId(int drivingId) {
                    this.drivingId = drivingId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class DrivingClassListBean implements Serializable{
                /**
                 * totalPrice : 1
                 * name : C1-急速班
                 * id : 1
                 */

                private Double totalPrice;
                private String name;
                private int id;

                public Double getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(Double totalPrice) {
                    this.totalPrice = totalPrice;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
