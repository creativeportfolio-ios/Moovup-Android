package com.example.anonymous.demoapp.modal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomWarnings;

import java.io.Serializable;

@Entity
public class Location implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int locationId;

    @ColumnInfo(name = "latitude")
    public Double latitude;
    @ColumnInfo(name = "longitude")
    public Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}