package com.example.parkandgoapp;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * ParkAndGoApp created by test
 * Student ID: 991541369
 * on 2019-11-18
 */
public class Utils {
    public static boolean isValidEmail(String target){
        return (!TextUtils.isEmpty(target) &&
                Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
