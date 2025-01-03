package com.example.madassignment.general;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GeneralUser.class}, version = 1, exportSchema = false)
public abstract class GeneralAppDatabase extends RoomDatabase {

    public abstract GeneralUserDao generalUserDao();

    private static volatile GeneralAppDatabase INSTANCE;

    public static GeneralAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GeneralAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GeneralAppDatabase.class, "app_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}