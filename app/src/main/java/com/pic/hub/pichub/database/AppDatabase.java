package com.pic.hub.pichub.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pic.hub.pichub.model.Photo;

@Database(entities = {Photo.class}, version = 1) //Entities listed here
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "wallpapers_database";
    private static AppDatabase INSTANCE;

    public abstract PhotoDao photoDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
