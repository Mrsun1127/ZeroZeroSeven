package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/11/30.
 */

public class UserLikeInfo implements Parcelable {

    public UserLikeInfo() {
    }

    /**
     * code : 0
     * data : {"posts":[{"userClazz":"1","isAnonymity":0,"like":false,"userCollege":"1","title":"1"}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    protected UserLikeInfo(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<UserLikeInfo> CREATOR = new Creator<UserLikeInfo>() {
        @Override
        public UserLikeInfo createFromParcel(Parcel in) {
            return new UserLikeInfo(in);
        }

        @Override
        public UserLikeInfo[] newArray(int size) {
            return new UserLikeInfo[size];
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(message);
    }

    public static class DataBean implements Parcelable{
        private List<PostsBean> posts;

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

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class PostsBean implements Parcelable{
            public PostsBean() {
            }

            /**
             * userClazz : 1
             * isAnonymity : 0
             * like : false
             * userCollege : 1
             * title : 1
             */

            private String userClazz;
            private int isAnonymity;
            private boolean like;
            private String userCollege;
            private String title;
            private int id;
            private String content;

            protected PostsBean(Parcel in) {
                userClazz = in.readString();
                isAnonymity = in.readInt();
                like = in.readByte() != 0;
                userCollege = in.readString();
                title = in.readString();
                id = in.readInt();
                content = in.readString();
            }

            public static final Creator<PostsBean> CREATOR = new Creator<PostsBean>() {
                @Override
                public PostsBean createFromParcel(Parcel in) {
                    return new PostsBean(in);
                }

                @Override
                public PostsBean[] newArray(int size) {
                    return new PostsBean[size];
                }
            };

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserClazz() {
                return userClazz;
            }

            public void setUserClazz(String userClazz) {
                this.userClazz = userClazz;
            }

            public int getIsAnonymity() {
                return isAnonymity;
            }

            public void setIsAnonymity(int isAnonymity) {
                this.isAnonymity = isAnonymity;
            }

            public boolean isLike() {
                return like;
            }

            public void setLike(boolean like) {
                this.like = like;
            }

            public String getUserCollege() {
                return userCollege;
            }

            public void setUserCollege(String userCollege) {
                this.userCollege = userCollege;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(userClazz);
                parcel.writeInt(isAnonymity);
                parcel.writeByte((byte) (like ? 1 : 0));
                parcel.writeString(userCollege);
                parcel.writeString(title);
                parcel.writeInt(id);
                parcel.writeString(content);
            }
        }
    }
}
