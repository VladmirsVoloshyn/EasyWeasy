package com.example.myapplication.ctrl;

import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;
import com.example.myapplication.geolocation.WeatherLocationListener;
import com.example.myapplication.network.Requester;
import com.example.myapplication.network.RequesterCallback;

public class DataController{
    public Requester requester;
    public CurrentWeatherData currentWeatherData;
    public ForecastWeatherData forecastWeatherData;
    private final WeatherDataCallback dataCallback;

    public DataController(WeatherDataCallback dataCallback) {
        this.currentWeatherData = new CurrentWeatherData();
        this.forecastWeatherData = new ForecastWeatherData();
        this.dataCallback = dataCallback;
        updateData();
    }

    public DataController(String cityName, WeatherDataCallback dataCallback) {
        this.currentWeatherData = new CurrentWeatherData();
        this.forecastWeatherData = new ForecastWeatherData();
        this.dataCallback = dataCallback;
        updateDataByCityName(cityName);
    }

    public void updateData() {
                requester = new Requester(WeatherLocationListener.getInstance().getLatitude(),
                        WeatherLocationListener.getInstance().getLongitude(),
                        new RequesterCallback() {
                    @Override
                    public void onResponse(WeatherData weatherData) {
                        dataCallback.onCurrentDataGet(currentWeatherData.build(weatherData));
                    }
                    @Override
                    public void onResponseBySevenDays(Main dailyWeather) {
                        dataCallback.onForecastDataGet(forecastWeatherData.build(dailyWeather));
                    }
                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
    public void updateDataByCityName(String cityName){
        requester = new Requester(cityName, new RequesterCallback() {
            @Override
            public void onResponse(WeatherData weatherData) {
                dataCallback.onCurrentDataGet(currentWeatherData.build(weatherData));
            }
            @Override
            public void onResponseBySevenDays(Main dailyWeather) {
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
}
