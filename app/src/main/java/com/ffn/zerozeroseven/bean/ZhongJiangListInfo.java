package com.ffn.zerozeroseven.bean;

import java.util.List;

public class ZhongJiangListInfo {
    /**
     * code : 0
     * data : {"participateList":{"total":13,"pageIndex":0,"totalPage":3,"pageSize":6,"list":[{"accept":true,"issuePrizeId":144,"issuePrizeStatus":3,"prizeIssue":2,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"},{"accept":true,"issuePrizeId":126,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"solove素乐 小风扇usb迷你静音学生电风扇","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780411377.png"},{"accept":true,"issuePrizeId":125,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雅韵仕 V7无线蓝牙小音箱","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780376505.jpg"},{"accept":true,"issuePrizeId":124,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雷迪凯 七彩机械手感键盘","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780336911.jpg"},{"accept":true,"issuePrizeId":123,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"},{"accept":true,"issuePrizeId":123,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"}]},"pointPrizeList":[{"accept":true,"awardAddress":{"contactAddress":"火车站","contactName":"dfd","contactPhone":"17388933063","id":43},"issuePrizeId":102,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"华硕E203NA3350 ","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png"},{"accept":true,"awardAddress":{"contactAddress":"只有下午","contactName":"哦哦哦","contactPhone":"17636965856","id":49},"issuePrizeId":124,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雷迪凯 七彩机械手感键盘","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780336911.jpg"},{"accept":true,"awardAddress":{"contactAddress":"图","contactName":"吧啦","contactPhone":"17389636965","id":50},"issuePrizeId":125,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雅韵仕 V7无线蓝牙小音箱","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780376505.jpg"},{"accept":true,"awardAddress":{"contactAddress":"兔兔","contactName":"龙","contactPhone":"17388933063","id":47},"issuePrizeId":126,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"solove素乐 小风扇usb迷你静音学生电风扇","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780411377.png"},{"accept":true,"awardAddress":{"contactAddress":"兔兔","contactName":"哦哦哦","contactPhone":"17388933696","id":45},"issuePrizeId":144,"issuePrizeStatus":3,"prizeIssue":2,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"}]}
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
         * participateList : {"total":13,"pageIndex":0,"totalPage":3,"pageSize":6,"list":[{"accept":true,"issuePrizeId":144,"issuePrizeStatus":3,"prizeIssue":2,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"},{"accept":true,"issuePrizeId":126,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"solove素乐 小风扇usb迷你静音学生电风扇","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780411377.png"},{"accept":true,"issuePrizeId":125,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雅韵仕 V7无线蓝牙小音箱","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780376505.jpg"},{"accept":true,"issuePrizeId":124,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雷迪凯 七彩机械手感键盘","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780336911.jpg"},{"accept":true,"issuePrizeId":123,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"},{"accept":true,"issuePrizeId":123,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"}]}
         * pointPrizeList : [{"accept":true,"awardAddress":{"contactAddress":"火车站","contactName":"dfd","contactPhone":"17388933063","id":43},"issuePrizeId":102,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"华硕E203NA3350 ","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png"},{"accept":true,"awardAddress":{"contactAddress":"只有下午","contactName":"哦哦哦","contactPhone":"17636965856","id":49},"issuePrizeId":124,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雷迪凯 七彩机械手感键盘","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780336911.jpg"},{"accept":true,"awardAddress":{"contactAddress":"图","contactName":"吧啦","contactPhone":"17389636965","id":50},"issuePrizeId":125,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雅韵仕 V7无线蓝牙小音箱","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780376505.jpg"},{"accept":true,"awardAddress":{"contactAddress":"兔兔","contactName":"龙","contactPhone":"17388933063","id":47},"issuePrizeId":126,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"solove素乐 小风扇usb迷你静音学生电风扇","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780411377.png"},{"accept":true,"awardAddress":{"contactAddress":"兔兔","contactName":"哦哦哦","contactPhone":"17388933696","id":45},"issuePrizeId":144,"issuePrizeStatus":3,"prizeIssue":2,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"}]
         */

        private ParticipateListBean participateList;
        private List<PointPrizeListBean> pointPrizeList;

        public ParticipateListBean getParticipateList() {
            return participateList;
        }

        public void setParticipateList(ParticipateListBean participateList) {
            this.participateList = participateList;
        }

        public List<PointPrizeListBean> getPointPrizeList() {
            return pointPrizeList;
        }

        public void setPointPrizeList(List<PointPrizeListBean> pointPrizeList) {
            this.pointPrizeList = pointPrizeList;
        }

        public static class ParticipateListBean {
            /**
             * total : 13
             * pageIndex : 0
             * totalPage : 3
             * pageSize : 6
             * list : [{"accept":true,"issuePrizeId":144,"issuePrizeStatus":3,"prizeIssue":2,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"},{"accept":true,"issuePrizeId":126,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"solove素乐 小风扇usb迷你静音学生电风扇","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780411377.png"},{"accept":true,"issuePrizeId":125,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雅韵仕 V7无线蓝牙小音箱","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780376505.jpg"},{"accept":true,"issuePrizeId":124,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"雷迪凯 七彩机械手感键盘","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780336911.jpg"},{"accept":true,"issuePrizeId":123,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"},{"accept":true,"issuePrizeId":123,"issuePrizeStatus":3,"prizeIssue":1,"prizeName":"夏季冰丝记忆棉坐垫办公室椅垫","prizePic":"http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png"}]
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
                 * accept : true
                 * issuePrizeId : 144
                 * issuePrizeStatus : 3
                 * prizeIssue : 2
                 * prizeName : 夏季冰丝记忆棉坐垫办公室椅垫
                 * prizePic : http://192.168.0.199/lingling7-res/image/20180705/1530780296373.png
                 */

                private boolean accept;
                private int issuePrizeId;
                private int issuePrizeStatus;
                private int prizeIssue;

                public String getLotteryTime() {
                    return lotteryTime;
                }

                public void setLotteryTime(String lotteryTime) {
                    this.lotteryTime = lotteryTime;
                }

                private String lotteryTime;
                private String prizeName;
                private String prizePic;

                public boolean isAccept() {
                    return accept;
                }

                public void setAccept(boolean accept) {
                    this.accept = accept;
                }

                public int getIssuePrizeId() {
                    return issuePrizeId;
                }

                public void setIssuePrizeId(int issuePrizeId) {
                    this.issuePrizeId = issuePrizeId;
                }

                public int getIssuePrizeStatus() {
                    return issuePrizeStatus;
                }

                public void setIssuePrizeStatus(int issuePrizeStatus) {
                    this.issuePrizeStatus = issuePrizeStatus;
                }

                public int getPrizeIssue() {
                    return prizeIssue;
                }

                public void setPrizeIssue(int prizeIssue) {
                    this.prizeIssue = prizeIssue;
                }

                public String getPrizeName() {
                    return prizeName;
                }

                public void setPrizeName(String prizeName) {
                    this.prizeName = prizeName;
                }

                public String getPrizePic() {
                    return prizePic;
                }

                public void setPrizePic(String prizePic) {
                    this.prizePic = prizePic;
                }
            }
        }

        public static class PointPrizeListBean {
            /**
             * accept : true
             * awardAddress : {"contactAddress":"火车站","contactName":"dfd","contactPhone":"17388933063","id":43}
             * issuePrizeId : 102
             * issuePrizeStatus : 3
             * prizeIssue : 1
             * prizeName : 华硕E203NA3350
             * prizePic : http://192.168.0.199/lingling7-res/image/20180705/1530756743069.png
             */

            private boolean accept;
            private AwardAddressBean awardAddress;
            private int issuePrizeId;
            private int issuePrizeStatus;
            private int prizeIssue;
            private String prizeName;
            private String prizePic;
            private String lotteryTime;

            public String getLotteryTime() {
                return lotteryTime;
            }

            public void setLotteryTime(String lotteryTime) {
                this.lotteryTime = lotteryTime;
            }

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
            }

            public AwardAddressBean getAwardAddress() {
                return awardAddress;
            }

            public void setAwardAddress(AwardAddressBean awardAddress) {
                this.awardAddress = awardAddress;
            }

            public int getIssuePrizeId() {
                return issuePrizeId;
            }

            public void setIssuePrizeId(int issuePrizeId) {
                this.issuePrizeId = issuePrizeId;
            }

            public int getIssuePrizeStatus() {
                return issuePrizeStatus;
            }

            public void setIssuePrizeStatus(int issuePrizeStatus) {
                this.issuePrizeStatus = issuePrizeStatus;
            }

            public int getPrizeIssue() {
                return prizeIssue;
            }

            public void setPrizeIssue(int prizeIssue) {
                this.prizeIssue = prizeIssue;
            }

            public String getPrizeName() {
                return prizeName;
            }

            public void setPrizeName(String prizeName) {
                this.prizeName = prizeName;
            }

            public String getPrizePic() {
                return prizePic;
            }

            public void setPrizePic(String prizePic) {
                this.prizePic = prizePic;
            }

            public static class AwardAddressBean {
                /**
                 * contactAddress : 火车站
                 * contactName : dfd
                 * contactPhone : 17388933063
                 * id : 43
                 */

                private String contactAddress;
                private String contactName;
                private String contactPhone;
                private int id;

                public String getContactAddress() {
                    return contactAddress;
                }

                public void setContactAddress(String contactAddress) {
                    this.contactAddress = contactAddress;
                }

                public String getContactName() {
                    return contactName;
                }

                public void setContactName(String contactName) {
                    this.contactName = contactName;
                }

                public String getContactPhone() {
                    return contactPhone;
                }

                public void setContactPhone(String contactPhone) {
                    this.contactPhone = contactPhone;
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
