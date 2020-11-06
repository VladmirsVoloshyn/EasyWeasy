package com.example.myapplication.ui.customize.datetags;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class WeatherListTag {
    private final String date;
    private final String temp;
    private final String descriptionImage;

    public  WeatherListTag(String date, String temp, String descriptionImage){
    this.date = date;
    this.temp = temp;
    this.descriptionImage = descriptionImage;
    }

    public String getDate() {
        return date;
    }

    public String getTemp() {
        return temp;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherListTag that = (WeatherListTag) o;
        return Objects.equals(date, that.date);
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
