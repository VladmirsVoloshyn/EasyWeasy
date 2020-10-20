package com.example.myapplication.network;



import com.example.myapplication.data.DailyWeatherData.Main;
import com.example.myapplication.data.WeatherData.WeatherData;

public interface RequesterCallback {
    void onResponse(WeatherData weatherData);
    void onResponseBySevenDays(Main dailyWeather);
    void onFailure();
}
