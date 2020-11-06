package com.example.myapplication.ui.forecastfragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.location.Location;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;

public class ForecastViewModel extends ViewModel implements LocationCallback {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private final MutableLiveData<ForecastWeatherData> forecastLiveData = new MutableLiveData<>();
    protected DataController dataController;

    public void updateData(){
        dataController = new DataController(new WeatherDataCallback() {
            @Override
            public void onCurrentDataGet(CurrentWeatherData currentWeatherData) {
            }

            @Override
            public void onForecastDataGet(ForecastWeatherData forecastWeatherData) {
                forecastLiveData.postValue(forecastWeatherData);
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

    public void setContext(Context context) {
        this.context = context;
    }

    public void updateLocation() {
        WeatherLocationListener.getInstance().requestLocation();
    }
}


