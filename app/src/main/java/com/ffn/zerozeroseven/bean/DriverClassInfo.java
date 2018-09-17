package com.ffn.zerozeroseven.bean;

public class DriverClassInfo {
    /**
     * code : 0
     * data : {"drivingClass":{"boardingTime":"2018-09-12","carBrand":"大众","cardFee":1,"classesIntro":"一周三次","createTime":"2018-09-12 16:14:31","drivingId":3,"examFee":1000,"exclusive":"补考费","id":2,"isRecommend":1,"licenseType":"A2","name":"C2-急速班","nominalFee":1000,"photoFee":1,"premium":1,"servicePromise":"包教会","shuttleType":"教练接送","takeTime":"2018-11-11","totalPrice":3000,"trainingFee":997}}
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

    public static class DataBean {
        /**
         * drivingClass : {"boardingTime":"2018-09-12","carBrand":"大众","cardFee":1,"classesIntro":"一周三次","createTime":"2018-09-12 16:14:31","drivingId":3,"examFee":1000,"exclusive":"补考费","id":2,"isRecommend":1,"licenseType":"A2","name":"C2-急速班","nominalFee":1000,"photoFee":1,"premium":1,"servicePromise":"包教会","shuttleType":"教练接送","takeTime":"2018-11-11","totalPrice":3000,"trainingFee":997}
         */

        private DrivingClassBean drivingClass;

        public DrivingClassBean getDrivingClass() {
            return drivingClass;
        }

        public void setDrivingClass(DrivingClassBean drivingClass) {
            this.drivingClass = drivingClass;
        }

        public static class DrivingClassBean {
            /**
             * boardingTime : 2018-09-12
             * carBrand : 大众
             * cardFee : 1
             * classesIntro : 一周三次
             * createTime : 2018-09-12 16:14:31
             * drivingId : 3
             * examFee : 1000
             * exclusive : 补考费
             * id : 2
             * isRecommend : 1
             * licenseType : A2
             * name : C2-急速班
             * nominalFee : 1000
             * photoFee : 1
             * premium : 1
             * servicePromise : 包教会
             * shuttleType : 教练接送
             * takeTime : 2018-11-11
             * totalPrice : 3000
             * trainingFee : 997
             */

            private String boardingTime;
            private String carBrand;
            private Double cardFee;
            private String classesIntro;
            private String createTime;
            private int drivingId;
            private Double examFee;
            private String exclusive;
            private int id;
            private int isRecommend;
            private String licenseType;
            private String name;
            private Double nominalFee;
            private Double photoFee;
            private Double premium;
            private String servicePromise;
            private String shuttleType;
            private String takeTime;
            private Double totalPrice;
            private Double trainingFee;

            public String getBoardingTime() {
                return boardingTime;
            }

            public void setBoardingTime(String boardingTime) {
                this.boardingTime = boardingTime;
            }

            public String getCarBrand() {
                return carBrand;
            }

            public void setCarBrand(String carBrand) {
                this.carBrand = carBrand;
            }

            public Double getCardFee() {
                return cardFee;
            }

            public void setCardFee(Double cardFee) {
                this.cardFee = cardFee;
            }

            public String getClassesIntro() {
                return classesIntro;
            }

            public void setClassesIntro(String classesIntro) {
                this.classesIntro = classesIntro;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDrivingId() {
                return drivingId;
            }

            public void setDrivingId(int drivingId) {
                this.drivingId = drivingId;
            }

            public Double getExamFee() {
                return examFee;
            }

            public void setExamFee(Double examFee) {
                this.examFee = examFee;
            }

            public String getExclusive() {
                return exclusive;
            }

            public void setExclusive(String exclusive) {
                this.exclusive = exclusive;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public String getLicenseType() {
                return licenseType;
            }

            public void setLicenseType(String licenseType) {
                this.licenseType = licenseType;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Double getNominalFee() {
                return nominalFee;
            }

            public void setNominalFee(Double nominalFee) {
                this.nominalFee = nominalFee;
            }

            public Double getPhotoFee() {
                return photoFee;
            }

            public void setPhotoFee(Double photoFee) {
                this.photoFee = photoFee;
            }

            public Double getPremium() {
                return premium;
            }

            public void setPremium(Double premium) {
                this.premium = premium;
            }

            public String getServicePromise() {
                return servicePromise;
            }

            public void setServicePromise(String servicePromise) {
                this.servicePromise = servicePromise;
            }

            public String getShuttleType() {
                return shuttleType;
            }

            public void setShuttleType(String shuttleType) {
                this.shuttleType = shuttleType;
            }

            public String getTakeTime() {
                return takeTime;
            }

            public void setTakeTime(String takeTime) {
                this.takeTime = takeTime;
            }

            public Double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public Double getTrainingFee() {
                return trainingFee;
            }

            public void setTrainingFee(Double trainingFee) {
                this.trainingFee = trainingFee;
            }
        }
    }
}
