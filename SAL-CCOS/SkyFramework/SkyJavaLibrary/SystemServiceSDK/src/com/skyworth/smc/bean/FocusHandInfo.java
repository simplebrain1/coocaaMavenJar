package com.skyworth.smc.bean;


import android.os.Parcel;
import android.os.Parcelable;


public class FocusHandInfo implements Parcelable {

    private int personId;
    private int posLeft;
    private int posTop;
    private int posRight;
    private int posBottom;

    public FocusHandInfo(int personId, int posLeft, int posTop, int posRight, int posBottom) {
        this.personId = personId;
        this.posLeft = posLeft;
        this.posTop = posTop;
        this.posRight = posRight;
        this.posBottom = posBottom;
    }

    protected FocusHandInfo(Parcel in) {
        personId = in.readInt();
        posLeft = in.readInt();
        posTop = in.readInt();
        posRight = in.readInt();
        posBottom = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(personId);
        dest.writeInt(posLeft);
        dest.writeInt(posTop);
        dest.writeInt(posRight);
        dest.writeInt(posBottom);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FocusHandInfo> CREATOR = new Creator<FocusHandInfo>() {
        @Override
        public FocusHandInfo createFromParcel(Parcel in) {
            return new FocusHandInfo(in);
        }

        @Override
        public FocusHandInfo[] newArray(int size) {
            return new FocusHandInfo[size];
        }
    };

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int id) {
        this.personId = id;
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
