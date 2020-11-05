package com.example.myapplication.network;



import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;

public interface RequesterCallback {
    void onResponse(WeatherData weatherData);
    void onResponseBySevenDays(Main dailyWeather);
    void onFailure();
}
