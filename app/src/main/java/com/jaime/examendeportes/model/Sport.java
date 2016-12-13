package com.jaime.examendeportes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jaime on 8/12/16.
 */

public class Sport implements Parcelable {
    private int image;
    private String name;
    private boolean like;

    public Sport(int image, String name, boolean like) {
        this.image = image;
        this.name = name;
        this.like = like;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }


    //Métodos parcelable

    protected Sport(Parcel in) {
        image = in.readInt();
        name = in.readString();
        like = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(name);
        parcel.writeByte(like ? (byte) 1 : (byte) 0);
    }

    public static final Parcelable.Creator<Sport> CREATOR = new Parcelable.Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

    //Fin parcelable
}
