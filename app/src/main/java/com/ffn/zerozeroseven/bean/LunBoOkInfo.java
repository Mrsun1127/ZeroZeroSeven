package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2018/3/3.
 */

public class LunBoOkInfo implements Serializable {

    /**
     * code : 0
     * data : {"total":5,"pageIndex":0,"totalPage":1,"pageSize":10,"list":[{"orderNo":"151998149121490","phone":"13469236551","userId":74},{"orderNo":"151997663637686","phone":"13469236551","userId":74},{"orderNo":"151997522953779","phone":"13469236551","userId":74},{"orderNo":"151997267633275","phone":"13469236551","userId":74},{"orderNo":"151997098994372","phone":"18692846953","userId":85}]}
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
        /**
         * total : 5
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * list : [{"orderNo":"151998149121490","phone":"13469236551","userId":74},{"orderNo":"151997663637686","phone":"13469236551","userId":74},{"orderNo":"151997522953779","phone":"13469236551","userId":74},{"orderNo":"151997267633275","phone":"13469236551","userId":74},{"orderNo":"151997098994372","phone":"18692846953","userId":85}]
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
             * orderNo : 151998149121490
             * phone : 13469236551
             * userId : 74
             */

            private String orderNo;
            private String phone;
            private int userId;

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
