package com.example.myapplication.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import android.view.View;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.geolocation.LocationCallback;


public class CurrentViewModel extends ViewModel implements LocationCallback {

    DataController dataController;
    public MutableLiveData<CurrentWeatherDataConstructor> currentWeatherDataConstructor = new MutableLiveData<>();

    public void updateData(){
        dataController = new DataController(new WeatherDataCallback() {
            @Override
            public void onDataGet(CurrentWeatherDataConstructor currentWeatherData) {
            CurrentViewModel.this.currentWeatherDataConstructor.postValue(currentWeatherData);
            }

            @Override
            public void onDataGet(DailyWeatherDataConstructor dailyWeatherDataConstructor) {

            }
        });
    }

    public LiveData<CurrentWeatherDataConstructor> getData(){
        updateData();
        return this.currentWeatherDataConstructor;
    }

    @Override
    public void onLocationChanged(Location location) {
        updateData();
        getData();
    }
}
