package com.example.myapplication.ui.customize.city;

public class City {

    private String cityName;
    private String descriptionImage;
    private String temp;
    private int id;

    public String getCityName() {
        return cityName;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public void setTemp(String temp) {
        this.temp = temp;
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
