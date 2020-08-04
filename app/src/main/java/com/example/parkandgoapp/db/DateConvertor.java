package com.example.parkandgoapp.db;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * ParkAndGoApp created by test
 * Student ID: 991541369
 * on 2019-11-14
 */
public class DateConvertor {

    public static class DateConverter {
        @TypeConverter
        public static Date toDate(Long dateLong){
            return dateLong == null ? null : new Date(dateLong);
        }

        @TypeConverter
        public static Long fromDate(Date date){
            return date == null ? null : date.getTime();
        }
    }

}
