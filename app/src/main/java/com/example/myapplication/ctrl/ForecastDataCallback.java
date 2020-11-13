package com.example.myapplication.ctrl;

import com.example.myapplication.data.ForecastWeatherData;

public interface ForecastDataCallback {

    void onDataGet(ForecastWeatherData forecastWeatherData);

    void onFailure(Throwable t);
}
