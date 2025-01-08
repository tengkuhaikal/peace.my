package com.example.madassignment.symptom;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static long fromDate(Date date){
        return date == null ? null:date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long timestamp){
        return  timestamp == null ? null: new Date(timestamp);
    }
}