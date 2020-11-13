package com.example.myapplication.ctrl;

import com.example.myapplication.data.CurrentWeatherData;

public interface CurrentDataCallback {
    void onDataGet(CurrentWeatherData currentWeatherData);

    void onFailure(Throwable t);
}
