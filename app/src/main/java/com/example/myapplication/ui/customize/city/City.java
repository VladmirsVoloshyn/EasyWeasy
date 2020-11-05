package com.example.myapplication.ui.customize.city;

public class City {

    private final String cityName;
    private final String descriptionImage;
    private final String temp;
    private final int id;

    public String getCityName() {
        return cityName;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }



    public int getId() {
        return id;
    }

    public String getTemp() {
        return temp;
    }

    public City(String cityName, String descriptionImage, String temp, int id) {
        this.cityName = cityName;
        this.descriptionImage = descriptionImage;
        this.temp = temp;
        this.id = id;
    }
}
