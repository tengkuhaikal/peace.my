package com.example.madassignment.education;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EducationItem.class}, version = 1)
public abstract class EducationAppDatabase extends RoomDatabase {
    public abstract EducationItemDao educationItemDao();

    private static volatile EducationAppDatabase INSTANCE;

    public static EducationAppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (EducationAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EducationAppDatabase.class, "education_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


