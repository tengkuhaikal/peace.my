package com.example.madassignment.symptom;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "symptoms")
public class SymptomEntity implements Parcelable {

    @NonNull
    @PrimaryKey
    private String name;
    private String description;
    private Date date;

    // Constructor
    public SymptomEntity(String name, Date date){
        this.name = name;
        this.date = date;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate(){
        return date;
    }

    // Parcelable implementation
    protected SymptomEntity(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;  // No special objects
    }

    public static final Creator<SymptomEntity> CREATOR = new Creator<SymptomEntity>() {
        @Override
        public SymptomEntity createFromParcel(Parcel in) {
            return new SymptomEntity(in);
        }

        @Override
        public SymptomEntity[] newArray(int size) {
            return new SymptomEntity[size];
        }
    };
}