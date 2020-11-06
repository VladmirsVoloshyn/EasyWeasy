package com.example.myapplication.ctrl;

import com.example.myapplication.data.ForecastWeatherDataConstructor;
import com.example.myapplication.data.CurrentWeatherDataConstructor;

public interface WeatherDataCallback {
        void onDataGet(CurrentWeatherDataConstructor currentWeatherData);
        void onDataGet(ForecastWeatherDataConstructor dailyWeatherDataConstructor);
}
