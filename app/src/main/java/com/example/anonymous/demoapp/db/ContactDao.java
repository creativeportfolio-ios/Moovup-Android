package com.example.anonymous.demoapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.anonymous.demoapp.modal.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(List<Contact> contacts);

    @Query("DELETE FROM contact")
    void delete();

    @Query("Select * FROM contact")
    List<Contact> loadAll();
}