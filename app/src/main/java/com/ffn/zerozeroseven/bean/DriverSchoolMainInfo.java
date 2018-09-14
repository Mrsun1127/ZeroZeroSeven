package com.ffn.zerozeroseven.bean;

import java.util.List;

public class DriverSchoolMainInfo {
    /**
     * status : 0
     * total : 2
     * size : 2
     * contents : [{"driving_id":"1","check_status":1,"image":{"imgid":"56437507362","big":"http://a.imgsrc.baidu.com/lbsapi/pic/item/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg","mid":"http://a.imgsrc.baidu.com/lbsapi/wh%3D160%2C160/sign=2ed261009adda144da5c64b38487fc93/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg","sml":"http://a.imgsrc.baidu.com/lbsapi/wh%3D16%2C16/sign=90feb33560224f4a57cc7b123fdba164/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg"},"tags":"驾校","price":300,"uid":2557645013,"province":"湖南省","selling_tag":"多渠道,dqd","geotable_id":194571,"modify_time":1536825671,"district":"芙蓉区","create_time":1536635894,"number":0,"city":"长沙市","location":[113.022603,28.199393],"address":"湖南省长沙市芙蓉区车站中路406号","title":"零零7驾校","coord_type":3,"direction":"西","type":0,"distance":3978,"weight":0},{"driving_id":"2","check_status":1,"image":{"imgid":"56437496883","big":"http://f.imgsrc.baidu.com/lbsapi/pic/item/63d0f703918fa0ec3b26be1e2b9759ee3c6ddbc4.jpg","mid":"http://f.imgsrc.baidu.com/lbsapi/wh%3D160%2C160/sign=4f0562330ce939015657853f4ddc78d6/63d0f703918fa0ec3b26be1e2b9759ee3c6ddbc4.jpg","sml":"http://f.imgsrc.baidu.com/lbsapi/wh%3D16%2C16/sign=58bf190766600c33f02cd6c92c606039/63d0f703918fa0ec3b26be1e2b9759ee3c6ddbc4.jpg"},"tags":"驾校","price":3200,"uid":2557645298,"province":"湖南省","selling_tag":"包接送","geotable_id":194571,"modify_time":1536825665,"district":"雨花区","create_time":1536635948,"number":0,"city":"长沙市","location":[113.022603,28.1899393],"address":"湖南省长沙市芙蓉区车站中路406号","title":"辉煌驾校","coord_type":3,"direction":"西","type":0,"distance":4203,"weight":0}]
     */

    private int status;
    private int total;
    private int size;
    private List<ContentsBean> contents;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public static class ContentsBean {
        /**
         * driving_id : 1
         * check_status : 1
         * image : {"imgid":"56437507362","big":"http://a.imgsrc.baidu.com/lbsapi/pic/item/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg","mid":"http://a.imgsrc.baidu.com/lbsapi/wh%3D160%2C160/sign=2ed261009adda144da5c64b38487fc93/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg","sml":"http://a.imgsrc.baidu.com/lbsapi/wh%3D16%2C16/sign=90feb33560224f4a57cc7b123fdba164/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg"}
         * tags : 驾校
         * price : 300
         * uid : 2557645013
         * province : 湖南省
         * selling_tag : 多渠道,dqd
         * geotable_id : 194571
         * modify_time : 1536825671
         * district : 芙蓉区
         * create_time : 1536635894
         * number : 0
         * city : 长沙市
         * location : [113.022603,28.199393]
         * address : 湖南省长沙市芙蓉区车站中路406号
         * title : 零零7驾校
         * coord_type : 3
         * direction : 西
         * type : 0
         * distance : 3978
         * weight : 0
         */

        private String driving_id;
        private int check_status;
        private ImageBean image;
        private String tags;
        private String price;
        private long uid;
        private String province;
        private String selling_tag;
        private int geotable_id;
        private String modify_time;
        private String district;
        private String create_time;
        private String number;
        private String city;
        private String address;
        private String title;
        private int coord_type;
        private String direction;
        private int type;
        private String distance;
        private String weight;
        private List<Double> location;

        public String getDriving_id() {
            return driving_id;
        }

        public void setDriving_id(String driving_id) {
            this.driving_id = driving_id;
        }

        public int getCheck_status() {
            return check_status;
        }

        public void setCheck_status(int check_status) {
            this.check_status = check_status;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSelling_tag() {
            return selling_tag;
        }

        public void setSelling_tag(String selling_tag) {
            this.selling_tag = selling_tag;
        }

        public int getGeotable_id() {
            return geotable_id;
        }

        public void setGeotable_id(int geotable_id) {
            this.geotable_id = geotable_id;
        }

        public String getModify_time() {
            return modify_time;
        }

        public void setModify_time(String modify_time) {
            this.modify_time = modify_time;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCoord_type() {
            return coord_type;
        }

        public void setCoord_type(int coord_type) {
            this.coord_type = coord_type;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public List<Double> getLocation() {
            return location;
        }

        public void setLocation(List<Double> location) {
            this.location = location;
        }

        public static class ImageBean {
            /**
             * imgid : 56437507362
             * big : http://a.imgsrc.baidu.com/lbsapi/pic/item/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg
             * mid : http://a.imgsrc.baidu.com/lbsapi/wh%3D160%2C160/sign=2ed261009adda144da5c64b38487fc93/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg
             * sml : http://a.imgsrc.baidu.com/lbsapi/wh%3D16%2C16/sign=90feb33560224f4a57cc7b123fdba164/b999a9014c086e063f90fa580f087bf40ad1cbed.jpg
             */

            private String imgid;
            private String big;
            private String mid;
            private String sml;

            public String getImgid() {
                return imgid;
            }

            public void setImgid(String imgid) {
                this.imgid = imgid;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getSml() {
                return sml;
            }

            public void setSml(String sml) {
                this.sml = sml;
            }
        }
    }
}
