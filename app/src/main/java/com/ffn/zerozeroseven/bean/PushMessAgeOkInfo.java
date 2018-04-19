package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2018/2/3.
 */

public class PushMessAgeOkInfo implements Serializable {
    /**
     * code : 0
     * data : {"total":5,"pageIndex":0,"totalPage":1,"pageSize":10,"list":[{"summary":"阿荣法国阿斯蒂芬个","createTime":"2018-01-27 21:48:12","id":1,"title":"矮人国阿拉贡","type":"01","status":1},{"summary":"龙哥尿床了","createTime":"2018-01-30 10:07:14","id":4,"title":"龙哥尿床了","type":"01","status":1},{"summary":"888888","createTime":"2018-01-30 13:18:29","id":5,"title":"有奖活动","type":"01","status":1},{"summary":"阿斯蒂芬","createTime":"2018-02-01 12:11:47","id":6,"title":"告诉人为善","type":"01","status":1},{"summary":"与","createTime":"2018-02-01 12:31:28","id":7,"title":"结婚的","type":"01","status":1}]}
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
         * total : 5
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * list : [{"summary":"阿荣法国阿斯蒂芬个","createTime":"2018-01-27 21:48:12","id":1,"title":"矮人国阿拉贡","type":"01","status":1},{"summary":"龙哥尿床了","createTime":"2018-01-30 10:07:14","id":4,"title":"龙哥尿床了","type":"01","status":1},{"summary":"888888","createTime":"2018-01-30 13:18:29","id":5,"title":"有奖活动","type":"01","status":1},{"summary":"阿斯蒂芬","createTime":"2018-02-01 12:11:47","id":6,"title":"告诉人为善","type":"01","status":1},{"summary":"与","createTime":"2018-02-01 12:31:28","id":7,"title":"结婚的","type":"01","status":1}]
         */

        private int total;
        private int pageIndex;
        private int totalPage;
        private int pageSize;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * summary : 阿荣法国阿斯蒂芬个
             * createTime : 2018-01-27 21:48:12
             * id : 1
             * title : 矮人国阿拉贡
             * type : 01
             * status : 1
             */

            private String summary;
            private String createTime;
            private int id;
            private String title;
            private String type;
            private int status;

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
