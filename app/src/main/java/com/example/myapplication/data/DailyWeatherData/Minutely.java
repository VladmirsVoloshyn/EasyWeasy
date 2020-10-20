package com.example.myapplication.data.DailyWeatherData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Minutely {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("precipitation")
    @Expose
    private Integer precipitation;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

}