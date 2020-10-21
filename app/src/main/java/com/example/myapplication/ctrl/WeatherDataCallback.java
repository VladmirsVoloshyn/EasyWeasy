package com.example.myapplication.ctrl;

import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.data.CurrentWeatherDataConstructor;

public interface WeatherDataCallback {
        void onDataGet(CurrentWeatherDataConstructor currentWeatherData);
        void onDataGet(DailyWeatherDataConstructor dailyWeatherDataConstructor);
}
