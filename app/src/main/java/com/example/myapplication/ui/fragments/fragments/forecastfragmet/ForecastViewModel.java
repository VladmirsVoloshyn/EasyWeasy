package com.example.myapplication.ui.fragments.fragments.forecastfragmet;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.ForecastDataCallback;
import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;

public class ForecastViewModel extends ViewModel implements LocationCallback {

    private final MutableLiveData<ForecastWeatherData> forecastLiveData = new MutableLiveData<>();
    protected DataController dataController;

    public void updateData(){
       dataController = new DataController(new ForecastDataCallback() {
           @Override
           public void onDataGet(ForecastWeatherData forecastWeatherData) {
            forecastLiveData.postValue(forecastWeatherData);
           }

           @Override
           public void onFailure(Throwable t) {

           }
       });
    }

    public LiveData<ForecastWeatherData> getData(){
        updateData();
        return this.forecastLiveData;
    }

    @Override
    public void onLocationChanged(Location location) {
        getData();
    }

    public void updateLocation() {
        WeatherLocationListener.getInstance().requestLocation();
    }
}


