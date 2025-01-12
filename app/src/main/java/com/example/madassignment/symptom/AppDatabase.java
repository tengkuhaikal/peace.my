package com.example.madassignment.symptom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SymptomEntity.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract SymptomDao symptomDao();

    // Migration object to migrate from version 1 to version 2
    public static final Migration MIGRATION_1_2 = new Migration(2, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Check if the table exists
            database.execSQL("CREATE TABLE IF NOT EXISTS `symptoms` ("
                    + "`name` TEXT NOT NULL, "
                    + "`description` TEXT, "
                    + "`date` INTEGER, "
                    + "PRIMARY KEY(`name`))");
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}