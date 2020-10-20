package com.example.myapplication.ctrl;

import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.data.CurrentWeatherDataConstructor;

public interface WeatherDataCallback {
        void onDataGet(CurrentWeatherDataConstructor dataFromActivity);
        void onDataGet(DailyWeatherDataConstructor dailyWeatherDataConstructor);
}
