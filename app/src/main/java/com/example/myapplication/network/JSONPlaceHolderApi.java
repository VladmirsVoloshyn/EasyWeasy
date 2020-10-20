package com.example.myapplication.network;





import com.example.myapplication.data.DailyWeatherData.Main;
import com.example.myapplication.data.WeatherData.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("weather?")
    public Call<WeatherData> getWeatherForCityName(@Query("q") String CityName,
                                                   @Query("appid") String appid);

    @GET("weather?")
    public Call<WeatherData> getWeatherForLocation(@Query("lat") double latitude,
                                                   @Query("lon") double Longitude, @Query("appid") String appid);
    @GET("onecall?")
    public  Call<Main> getWeatherBySevenDays(@Query("lat") double latitude,
                                             @Query("lon") double Longitude, @Query("exclude") String excludeparam, @Query("appid") String appid);

}
