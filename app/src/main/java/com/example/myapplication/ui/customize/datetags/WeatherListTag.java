package com.example.myapplication.ui.customize.datetags;

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

}
