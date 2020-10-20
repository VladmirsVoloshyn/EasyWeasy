package com.example.myapplication.ctrl;

import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.data.DailyWeatherData.Main;
import com.example.myapplication.data.WeatherData.WeatherData;
import com.example.myapplication.geolocation.WeatherLocationListener;
import com.example.myapplication.network.Requester;
import com.example.myapplication.network.RequesterCallback;

public class DataController{
    public Requester requester;
    public CurrentWeatherDataConstructor dataConstructor;
    public DailyWeatherDataConstructor dailyWeatherDataConstructor;
    private final WeatherDataCallback dataCallback;

    public DataController(WeatherDataCallback dataCallback) {
        this.dataConstructor = new CurrentWeatherDataConstructor();
        this.dailyWeatherDataConstructor = new DailyWeatherDataConstructor();
        this.dataCallback = dataCallback;
        updateData();
    }

    public DataController(String cityName, WeatherDataCallback dataCallback) {
        this.dataConstructor = new CurrentWeatherDataConstructor();
        this.dailyWeatherDataConstructor = new DailyWeatherDataConstructor();
        this.dataCallback = dataCallback;
        updateDataByCityName(cityName);
    }

    public void updateData() {
        requester = new Requester(WeatherLocationListener.getInstance().getLatitude(), WeatherLocationListener.getInstance().getLongitude(), new RequesterCallback() {
            @Override
            public void onResponse(WeatherData weatherData) {
                DataController.this.dataConstructor.build(weatherData);
                dataCallback.onDataGet(dataConstructor);
            }

            @Override
            public void onResponseBySevenDays(Main dailyWeather) {
                DataController.this.dailyWeatherDataConstructor.build(dailyWeather);
                dataCallback.onDataGet(dailyWeatherDataConstructor);
            }
            @Override
            public void onFailure() {
            }
        });
    }
    public void updateDataByCityName(String cityName){
        requester = new Requester(cityName, new RequesterCallback() {
            @Override
            public void onResponse(WeatherData weatherData) {
                DataController.this.dataConstructor.build(weatherData);
                dataCallback.onDataGet(dataConstructor);
            }

            @Override
            public void onResponseBySevenDays(Main dailyWeather) {

            }

            @Override
            public void onFailure() {

            }
        });
    }
}
