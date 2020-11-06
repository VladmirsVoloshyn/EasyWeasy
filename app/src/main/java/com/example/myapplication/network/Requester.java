package com.example.myapplication.network;


import android.support.annotation.NonNull;

import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Requester {
    private String CityName;
    private double latitude;
    private double longitude;
    private final RequesterCallback callback;

    public Requester(String cityName, RequesterCallback callback) {
        this.callback = callback;
        this.CityName = cityName;
        this.requestWeatherByCityName();
    }

    public Requester(double latitude, double longitude, RequesterCallback callback) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.callback = callback;

        this.requestWeatherByLocation();
        this.requestWeatherBySevenDays();
    }


    public void requestWeatherByCityName() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherByCityName(this.CityName, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                        WeatherData weatherData = response.body();
                        callback.onResponse(weatherData);
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        callback.onFailure(t);
                    }
                });
    }

    public void requestWeatherByLocation() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherByLocation(this.latitude, this.longitude, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                        WeatherData weatherData = response.body();
                        callback.onResponse(weatherData);
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        callback.onFailure(t);
                    }
                });
    }

    public void requestWeatherBySevenDays() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherBySevenDays(this.latitude, this.longitude, NetworkService.EXCLUDING_PARAMETERS, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<Main>() {
                    @Override
                    public void onResponse(@NonNull Call<Main> call, @NonNull Response<Main> response) {
                        Main dailyWeather = response.body();
                        callback.onResponseBySevenDays(dailyWeather);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Main> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        callback.onFailure(t);
                    }
                });

    }
}
