package com.example.madassignment.education;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EducationItemDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
                // Prevent duplicates
        void insertFavorite(EducationItem educationItem);

        @Query("SELECT * FROM education_items")
        List<EducationItem> getAllItems();

        @Query("SELECT * FROM education_items WHERE name = :name")
        EducationItem getItemByName(String name);

        @Delete
        void deleteFavorite(EducationItem educationItem);
}