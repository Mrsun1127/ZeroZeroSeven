package com.ffn.zerozeroseven.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GT on 2017/12/2.
 */

public class UpdateAdrInfo implements Parcelable{
    public String name;
    public String phone;
    public String adr;
    public int id;

    protected UpdateAdrInfo(Parcel in) {
        name = in.readString();
        phone = in.readString();
        adr = in.readString();
        id = in.readInt();
    }

    public static final Creator<UpdateAdrInfo> CREATOR = new Creator<UpdateAdrInfo>() {
        @Override
        public UpdateAdrInfo createFromParcel(Parcel in) {
            return new UpdateAdrInfo(in);
        }

        @Override
        public UpdateAdrInfo[] newArray(int size) {
            return new UpdateAdrInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(adr);
        parcel.writeInt(id);
    }
}
