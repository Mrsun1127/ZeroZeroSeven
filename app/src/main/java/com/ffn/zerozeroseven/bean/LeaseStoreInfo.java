package com.ffn.zerozeroseven.bean;

public class LeaseStoreInfo {
    /**
     * code : 0
     * data : {"leaseConfig":{"background":"http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png","id":2,"logo":"http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png","schoolId":1719,"servicePhone":"17621247369"}}
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
         * leaseConfig : {"background":"http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png","id":2,"logo":"http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png","schoolId":1719,"servicePhone":"17621247369"}
         */

        private LeaseConfigBean leaseConfig;

        public LeaseConfigBean getLeaseConfig() {
            return leaseConfig;
        }

        public void setLeaseConfig(LeaseConfigBean leaseConfig) {
            this.leaseConfig = leaseConfig;
        }

        public static class LeaseConfigBean {
            /**
             * background : http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png
             * id : 2
             * logo : http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png
             * schoolId : 1719
             * servicePhone : 17621247369
             */

            private String background;
            private int id;
            private String logo;
            private int schoolId;
            private String servicePhone;

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public String getServicePhone() {
                return servicePhone;
            }

            public void setServicePhone(String servicePhone) {
                this.servicePhone = servicePhone;
            }
        }
    }
}
