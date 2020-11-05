package com.example.myapplication.ui.currentfragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.location.Location;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.data.ForecastData.Current;
import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;


public class CurrentViewModel extends ViewModel implements LocationCallback {

    @SuppressLint("StaticFieldLeak")
    private Context context;

    public MutableLiveData<CurrentWeatherDataConstructor> currentWeatherDataConstructor = new MutableLiveData<>();

    public void updateData(){

        DataController dataController = new DataController(new WeatherDataCallback() {
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

    public void setContext(Context context) {
        this.context = context;
    }
}
