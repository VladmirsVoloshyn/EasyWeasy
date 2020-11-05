package com.example.myapplication.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.data.DailyWeatherDataConstructor;

public class CurrentViewModel extends ViewModel {

    DataController dataController;
    CurrentWeatherDataConstructor currentWeatherDataConstructor;

    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<String> cityName = new MutableLiveData<>();
    public MutableLiveData<String> temp = new MutableLiveData<>();
    public MutableLiveData<String> avgTemp = new MutableLiveData<>();
    public MutableLiveData<String> weatherDescription = new MutableLiveData<>();
    public MutableLiveData<String> pressure = new MutableLiveData<>();
    public MutableLiveData<String> humidity = new MutableLiveData<>();
    public MutableLiveData<String> windDestination = new MutableLiveData<>();
    public MutableLiveData<String> windSpeed = new MutableLiveData<>();

    public void updateData(){
        dataController = new DataController(new WeatherDataCallback() {
            @Override
            public void onDataGet(CurrentWeatherDataConstructor currentWeatherData) {
            }

            @Override
            public void onDataGet(DailyWeatherDataConstructor dailyWeatherDataConstructor) {

            }
        });
    }


}
