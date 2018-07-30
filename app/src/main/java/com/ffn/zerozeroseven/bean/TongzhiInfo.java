package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TongzhiInfo implements Parcelable{
    /**
     * code : 0
     * data : {"list":[{"content":"dsfdgvfgbffffffffsdfscf","createTime":"2018-05-31 11:59:09","id":52,"link":"https://my.oschina.net/lizaizhong/blog/1814267","status":1,"title":"222222222222","type":"02"},{"content":"零零7温馨提示：童鞋们，最近下雨，天气冷，大家出行注意安全，注意保暖！！","createTime":"2018-05-31 11:45:14","id":51,"link":"1","status":1,"title":"零零7温馨提示：童鞋们，最近下雨，天气冷，大家出行注意安全，注意保暖！！","type":"02"}]}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

    public TongzhiInfo() {
    }

    protected TongzhiInfo(Parcel in) {
        code = in.readInt();
        success = in.readByte() != 0;
    }

    public static final Creator<TongzhiInfo> CREATOR = new Creator<TongzhiInfo>() {
        @Override
        public TongzhiInfo createFromParcel(Parcel in) {
            return new TongzhiInfo(in);
        }

        @Override
        public TongzhiInfo[] newArray(int size) {
            return new TongzhiInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeByte((byte) (success ? 1 : 0));
    }

    public static class DataBean implements Parcelable{
        private List<ListBean> list;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class ListBean implements Parcelable{
            public ListBean() {
            }

            /**
             * content : dsfdgvfgbffffffffsdfscf
             * createTime : 2018-05-31 11:59:09
             * id : 52
             * link : https://my.oschina.net/lizaizhong/blog/1814267
             * status : 1
             * title : 222222222222
             * type : 02
             */

            private String content;
            private String createTime;
            private int id;
            private String link;
            private int status;
            private String title;
            private String type;

            protected ListBean(Parcel in) {
                content = in.readString();
                createTime = in.readString();
                id = in.readInt();
                link = in.readString();
                status = in.readInt();
                title = in.readString();
                type = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(content);
                parcel.writeString(createTime);
                parcel.writeInt(id);
                parcel.writeString(link);
                parcel.writeInt(status);
                parcel.writeString(title);
                parcel.writeString(type);
            }
        }
    }
}
