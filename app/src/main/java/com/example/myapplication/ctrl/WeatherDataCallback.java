package com.example.myapplication.ctrl;

import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.data.CurrentWeatherData;

public interface WeatherDataCallback {
        void onCurrentDataGet(CurrentWeatherData currentWeatherData);
        void onForecastDataGet(ForecastWeatherData forecastWeatherData);
}
