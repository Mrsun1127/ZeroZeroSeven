package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2018/2/6.
 */

public class ShouyiOkInfo implements Serializable {
    /**
     * code : 0
     * data : {"total":3,"pageIndex":0,"totalPage":1,"pageSize":10,"list":[{"income":1.01,"createTime":"2018-02-05 17:59:33","incomeType":"跑腿收益","id":4},{"income":10,"createTime":"2018-01-30 11:27:58","incomeType":"跑腿收益","id":1},{"income":11,"createTime":"2018-01-30 11:27:58","incomeType":"分销收益","id":2}]}
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
         * total : 3
         * pageIndex : 0
         * totalPage : 1
         * pageSize : 10
         * list : [{"income":1.01,"createTime":"2018-02-05 17:59:33","incomeType":"跑腿收益","id":4},{"income":10,"createTime":"2018-01-30 11:27:58","incomeType":"跑腿收益","id":1},{"income":11,"createTime":"2018-01-30 11:27:58","incomeType":"分销收益","id":2}]
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
             * income : 1.01
             * createTime : 2018-02-05 17:59:33
             * incomeType : 跑腿收益
             * id : 4
             */

            private Double income;
            private String createTime;
            private String incomeType;
            private int id;

            public Double getIncome() {
                return income;
            }

            public void setIncome(Double income) {
                this.income = income;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getIncomeType() {
                return incomeType;
            }

            public void setIncomeType(String incomeType) {
                this.incomeType = incomeType;
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
