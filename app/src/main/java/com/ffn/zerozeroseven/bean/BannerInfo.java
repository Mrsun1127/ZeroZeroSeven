package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

public class BannerInfo implements Serializable {

    /**
     * code : 0
     * data : {"total":1,"pageIndex":0,"totalPage":1,"pageSize":10,"list":[{"picUrl":"http://www.lingling7.com/lingling7-res/image/20180410/1523332672858.png","intro":"007","link":"www.baidu.com","id":1,"title":"007"}]}
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
         * total : 1
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * list : [{"picUrl":"http://www.lingling7.com/lingling7-res/image/20180410/1523332672858.png","intro":"007","link":"www.baidu.com","id":1,"title":"007"}]
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

        public static class ListBean {
            /**
             * picUrl : http://www.lingling7.com/lingling7-res/image/20180410/1523332672858.png
             * intro : 007
             * link : www.baidu.com
             * id : 1
             * title : 007
             */
            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            private String picUrl;
            private String intro;
            private String link;
            private int id;
            private String title;

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
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
        }
    }
}
