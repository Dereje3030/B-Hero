package com.ethioptech.b_hero.helper;

import android.os.Parcel;
import android.os.Parcelable;

public class UserA implements Parcelable {
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String city;
    private String blood;

    public UserA() {
    }

    public UserA(String fullName, String email, String password, String gender, String city, String blood) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.city = city;
        this.blood = blood;
    }

    protected UserA(Parcel in) {
        fullName = in.readString();
        email = in.readString();
        password = in.readString();
        gender = in.readString();
        city = in.readString();
        blood = in.readString();
    }

    public static final Creator<UserA> CREATOR = new Creator<UserA>() {
        @Override
        public UserA createFromParcel(Parcel in) {
            return new UserA(in);
        }

        @Override
        public UserA[] newArray(int size) {
            return new UserA[size];
        }
    };

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(gender);
        dest.writeString(city);
        dest.writeString(blood);
    }
}
