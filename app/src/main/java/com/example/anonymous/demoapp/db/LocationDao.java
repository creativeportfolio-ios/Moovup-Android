package com.example.anonymous.demoapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.anonymous.demoapp.modal.Contact;
import com.example.anonymous.demoapp.modal.Location;

@Dao
public interface LocationDao {

    @Insert
    void insert(Location... locations);

    @Query("DELETE FROM location")
    void delete();

    @Query("Select * FROM location")
    Location[] loadAll();
}