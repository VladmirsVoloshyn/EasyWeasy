package com.example.myapplication.network;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Requester {
    private String CityName;
    private double latitude;
    private double longitude;
    private RequesterCurrentDataCallback currentCallback;
    private RequesterForecastDataCallback forecastDataCallback;
    private RequesterDataByCityName dataByCityName;

    public Requester(double latitude, double longitude, RequesterCurrentDataCallback callback){
        this.currentCallback = callback;
        this.latitude = latitude;
        this.longitude =  longitude;

        this.requestCurrentWeatherData();
    }
    public Requester(double latitude, double longitude, RequesterForecastDataCallback callback){
        this.forecastDataCallback = callback;
        this.latitude = latitude;
        this.longitude =  longitude;

        this.requestForecastWeatherData();
    }

    public Requester(String cityName, RequesterDataByCityName callback) {
        this.dataByCityName = callback;
        this.CityName = cityName;

        this.requestWeatherByCityName();
    }


    public void requestWeatherByCityName() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherByCityName(this.CityName, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response)  {
                        WeatherData weatherData = response.body();
                            if (weatherData!=null)
                            dataByCityName.onResponse(weatherData);
                            else return;
                        Log.d("Request:", "request by city name");
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        dataByCityName.onFailure(t);
                    }
                });
    }

    public void requestCurrentWeatherData() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherByLocation(this.latitude, this.longitude, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                        WeatherData weatherData = response.body();
                        if(weatherData!=null) {
                            currentCallback.onResponse(weatherData);
                        }
                        else return;
                        Log.d("Requester", "request by location");
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        currentCallback.onFailure(t);
                    }
                });
    }

    public void requestForecastWeatherData() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherBySevenDays(this.latitude, this.longitude, NetworkService.EXCLUDING_PARAMETERS, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<Main>() {
                    @Override
                    public void onResponse(@NonNull Call<Main> call, @NonNull Response<Main> response) {
                        Main forecastData = response.body();
                        if (forecastData!=null){
                        forecastDataCallback.onResponse(forecastData);}
                        else return;
                        Log.d("Requester", "request by seven days");
                    }

                    @Override
                    public void onFailure(@NonNull Call<Main> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        forecastDataCallback.onFailure(t);
                    }
                });

    }
}
