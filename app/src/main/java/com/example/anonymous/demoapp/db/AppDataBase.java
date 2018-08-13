package com.example.anonymous.demoapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anonymous.demoapp.modal.Contact;
import com.example.anonymous.demoapp.modal.Location;

@Database(entities = {Contact.class, Location.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "contact-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ContactDao contactDao();

    public abstract LocationDao locationDao();
}
