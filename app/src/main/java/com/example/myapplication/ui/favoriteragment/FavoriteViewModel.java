package com.example.myapplication.ui.favoriteragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.database.citiesfavoritebase.CitiesBaseManager;
import com.example.myapplication.ui.customize.city.City;

import java.util.ArrayList;

public class FavoriteViewModel extends ViewModel {


    private final MutableLiveData<ArrayList<City>> mutableLiveData = new MutableLiveData<>();
    protected DataController dataController;
    private CitiesBaseManager citiesBaseManager;

    public void init(Context context){
        this.citiesBaseManager = new CitiesBaseManager(context.getApplicationContext());
    }

    public void updateData(final String cityName){
        dataController = new DataController(cityName, new WeatherDataCallback() {
            @Override
            public void onCurrentDataGet(CurrentWeatherData currentWeatherData) {
                citiesBaseManager.addElement(currentWeatherData.getCityName(),currentWeatherData.getMainDescription(), currentWeatherData.getCurrentTemp());
            }
            @Override
            public void onForecastDataGet(ForecastWeatherData forecastWeatherData) {
            }
        });
    }

    public void setData(){
                mutableLiveData.setValue(citiesBaseManager.getCitiesList());
    }

    public void addData(String cityName){
        updateData(cityName);
    }

    public void deleteData(int chosenPosition){
        citiesBaseManager.deleteElement(chosenPosition);
        mutableLiveData.setValue(citiesBaseManager.getCitiesList());
    }

    public MutableLiveData<ArrayList<City>> getMutableLiveData(){
        setData();
        return mutableLiveData;
    }

}
