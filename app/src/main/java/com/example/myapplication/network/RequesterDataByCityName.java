package com.example.myapplication.network;

import com.example.myapplication.data.CurrentData.WeatherData;

public interface RequesterDataByCityName {
    void onResponse(WeatherData currentData);
    void onFailure(Throwable t);
}
