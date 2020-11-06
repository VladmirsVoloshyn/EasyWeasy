package com.example.myapplication.network;





import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.data.CurrentData.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("weather?")
    Call<WeatherData> getWeatherByCityName(@Query("q") String CityName,
                                           @Query("appid") String appId);

    @GET("weather?")
    Call<WeatherData> getWeatherByLocation(@Query("lat") double latitude,
                                           @Query("lon") double Longitude, @Query("appid") String appId);
    @GET("onecall?")
    Call<Main> getWeatherBySevenDays(@Query("lat") double latitude,
                                     @Query("lon") double Longitude, @Query("exclude") String excludeParam, @Query("appid") String appId);

}
