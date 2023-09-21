package com.skyworth.smc.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FocusFaceInfo implements Parcelable {
    private int id;
    private int posLeft;
    private int posTop;
    private int posRight;
    private int posBottom;

    public FocusFaceInfo(int id, int posLeft, int posTop, int posRight, int posBottom) {
        this.id = id;
        this.posLeft = posLeft;
        this.posTop = posTop;
        this.posRight = posRight;
        this.posBottom = posBottom;
    }

    public FocusFaceInfo() {
    }

    protected FocusFaceInfo(Parcel in) {
        id = in.readInt();
        posLeft = in.readInt();
        posTop = in.readInt();
        posRight = in.readInt();
        posBottom = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(posLeft);
        dest.writeInt(posTop);
        dest.writeInt(posRight);
        dest.writeInt(posBottom);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FocusFaceInfo> CREATOR = new Creator<FocusFaceInfo>() {
        @Override
        public FocusFaceInfo createFromParcel(Parcel in) {
            return new FocusFaceInfo(in);
        }

        @Override
        public FocusFaceInfo[] newArray(int size) {
            return new FocusFaceInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosLeft() {
        return posLeft;
    }

    public void setPosLeft(int posLeft) {
        this.posLeft = posLeft;
    }

    public int getPosTop() {
        return posTop;
    }

    public void setPosTop(int posTop) {
        this.posTop = posTop;
    }

    public int getPosRight() {
        return posRight;
    }

    public void setPosRight(int posRight) {
        this.posRight = posRight;
    }

    public int getPosBottom() {
        return posBottom;
    }

    public void setPosBottom(int posBottom) {
        this.posBottom = posBottom;
    }
}
