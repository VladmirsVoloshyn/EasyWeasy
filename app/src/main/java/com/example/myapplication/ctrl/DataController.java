package com.example.myapplication.ctrl;

import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;
import com.example.myapplication.geolocation.WeatherLocationListener;
import com.example.myapplication.network.Requester;
import com.example.myapplication.network.RequesterCurrentDataCallback;
import com.example.myapplication.network.RequesterDataByCityName;
import com.example.myapplication.network.RequesterForecastDataCallback;

public class DataController {
    public Requester requester;
    public CurrentWeatherData currentWeatherData;
    public ForecastWeatherData forecastWeatherData;
    private CurrentDataCallback currentDataCallback;
    private ForecastDataCallback forecastDataCallback;

    public DataController(CurrentDataCallback currentDataCallback) {
        this.currentWeatherData = new CurrentWeatherData();
        this.currentDataCallback = currentDataCallback;

        updateCurrentData();
    }

    public DataController(ForecastDataCallback forecastDataCallback) {
        this.forecastWeatherData = new ForecastWeatherData();
        this.forecastDataCallback = forecastDataCallback;

        updateForecastData();
    }

    public DataController(String cityName, CurrentDataCallback dataCallback) {
        this.currentWeatherData = new CurrentWeatherData();
        this.forecastWeatherData = new ForecastWeatherData();
        currentDataCallback = dataCallback;

        updateDataByCityName(cityName);
    }

    public void updateCurrentData() {
        requester = new Requester(WeatherLocationListener.getInstance().getLatitude(),
                WeatherLocationListener.getInstance().getLongitude(),
                new RequesterCurrentDataCallback() {
                    @Override
                    public void onResponse(WeatherData currentData) {
                        currentDataCallback.onDataGet(currentWeatherData.build(currentData));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        currentDataCallback.onFailure(t);
                        t.printStackTrace();
                    }
                });
    }

    public void updateForecastData() {
        requester = new Requester(WeatherLocationListener.getInstance().getLatitude(),
                WeatherLocationListener.getInstance().getLongitude(),
                new RequesterForecastDataCallback() {
                    @Override
                    public void onResponse(Main forecastData) {
                        forecastDataCallback.onDataGet(forecastWeatherData.build(forecastData));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        forecastDataCallback.onFailure(t);
                        t.printStackTrace();
                    }
                });
    }


    public void updateDataByCityName(final String cityName) {
        requester = new Requester(cityName, new RequesterDataByCityName() {
            @Override
            public void onResponse(WeatherData weatherData) {
                currentDataCallback.onDataGet(currentWeatherData.build(weatherData));
            }

            @Override
            public void onFailure(Throwable t) {
                currentDataCallback.onFailure(t);
                t.printStackTrace();
            }
        });
    }
}
