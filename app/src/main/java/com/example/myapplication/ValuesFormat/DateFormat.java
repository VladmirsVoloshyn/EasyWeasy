package com.example.myapplication.ValuesFormat;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormat {

    public static String formatToGMT(long unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }
}
