package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/10.
 */

public class SchoolListInfo implements Serializable {
    /**
     * code : 0
     * data : {"schools":[{"isRecommend":0,"province":"110000","city":"430300","name":"湖南科技大学","fullName":"湖南科技大学","id":1714},{"isRecommend":0,"province":"110000","city":"430300","name":"湘潭大学","fullName":"湘潭大学","id":1715},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南工程学院","fullName":"湖南工程学院","id":1724},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南国防工业职业技术学院","fullName":"湖南国防工业职业技术学院","id":1746},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南城建职业技术学院","fullName":"湖南城建职业技术学院","id":1772},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南电气职业技术学院","fullName":"湖南电气职业技术学院","id":1773},{"isRecommend":0,"province":"110000","city":"430300","name":"湘潭医卫职业技术学院","fullName":"湘潭医卫职业技术学院","id":1797},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南吉利汽车职业技术学院","fullName":"湖南吉利汽车职业技术学院","id":1810},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南科技大学潇湘学院","fullName":"湖南科技大学潇湘学院","id":1818},{"isRecommend":0,"province":"110000","city":"430300","name":"湘潭大学兴湘学院","fullName":"湘潭大学兴湘学院","id":1820},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南工程学院应用技术学院","fullName":"湖南工程学院应用技术学院","id":1825},{"isRecommend":0,"province":"110000","city":"430300","name":"湖南吉利汽车职业技术学院","fullName":"湖南吉利汽车职业技术学院","id":1838}]}
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
        private List<SchoolsBean> schools;

        public List<SchoolsBean> getSchools() {
            return schools;
        }

        public void setSchools(List<SchoolsBean> schools) {
            this.schools = schools;
        }

        public static class SchoolsBean implements Serializable{
            /**
             * isRecommend : 0
             * province : 110000
             * city : 430300
             * name : 湖南科技大学
             * fullName : 湖南科技大学
             * id : 1714
             */

            private int isRecommend;
            private String province;
            private String city;
            private String name;
            private String fullName;
            private int id;

            public int getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
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
