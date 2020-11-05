package com.example.myapplication.network;


import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Requester {
    private String CityName;
    private double latitude;
    private double longitude;
    private RequesterCallback callback;

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
                .getWeatherForCityName(this.CityName, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        WeatherData weatherData = response.body();
                        callback.onResponse(weatherData);
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                        t.printStackTrace();
                        callback.onFailure();
                    }
                });
    }

    public void requestWeatherByLocation() {
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherForLocation(this.latitude, this.longitude, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        WeatherData weatherData = response.body();
                        callback.onResponse(weatherData);
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                        t.printStackTrace();
                        callback.onFailure();
                    }
                });
    }
    public void requestWeatherBySevenDays(){
        NetworkService.getInstance()
                .getJSONApi()
                .getWeatherBySevenDays(this.latitude, this.longitude, NetworkService.EXCLUDING_PARAMETERS, NetworkService.BASE_APP_ID)
                .enqueue(new Callback<Main>() {
                    @Override
                    public void onResponse(Call<Main> call, Response<Main> response) {
                        Main dailyWeather= response.body();
                        callback.onResponseBySevenDays(dailyWeather);
                    }

                    @Override
                    public void onFailure(Call<Main> call, Throwable t) {
                        t.printStackTrace();
                        callback.onFailure();
                    }
                });

    }
}
