package com.example.nishanth.midtermprep;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nishanth on 3/19/2017.
 */

public class PersonParcelable implements Parcelable {

    String name,address;
    double age;

    public PersonParcelable(String name, String address, double age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(age);



    }


    public static final Parcelable.Creator<PersonParcelable> CREATOR = new Parcelable.Creator<PersonParcelable>(){

        @Override
        public PersonParcelable createFromParcel(Parcel source) {
            return new PersonParcelable(source);
        }

        @Override
        public PersonParcelable[] newArray(int size) {
            return new PersonParcelable[size];
        }
    };

    private PersonParcelable(Parcel in){
        this.name = in.readString();
        this.address = in.readString();
        this.age = in.readDouble();

    }

    @Override
    public String toString() {
        return "PersonParcelable{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
