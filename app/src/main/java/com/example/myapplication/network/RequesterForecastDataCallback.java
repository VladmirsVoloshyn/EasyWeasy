package com.example.myapplication.network;

import com.example.myapplication.data.ForecastData.Main;

public interface RequesterForecastDataCallback {
     void onResponse(Main forecastData);
     void onFailure(Throwable t);
}
