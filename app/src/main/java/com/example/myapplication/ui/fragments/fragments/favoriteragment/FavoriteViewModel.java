package com.example.myapplication.ui.fragments.fragments.favoriteragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.myapplication.ctrl.CurrentDataCallback;
import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.database.citiesfavoritebase.CitiesBaseManager;
import com.example.myapplication.ui.customize.city.City;

import java.util.ArrayList;

public class FavoriteViewModel extends ViewModel {


    private final MutableLiveData<ArrayList<City>> mutableLiveData = new MutableLiveData<>();
    protected DataController dataController;
    private CitiesBaseManager citiesBaseManager;

    public void init(Context context) {
        this.citiesBaseManager = new CitiesBaseManager(context.getApplicationContext());
    }

    public void updateData(final String cityName) {
        if (cityName.equals("invalid string")) {
            return;
        } else
            dataController = new DataController(cityName, new CurrentDataCallback() {
                @Override
                public void onDataGet(CurrentWeatherData currentWeatherData) {
                    citiesBaseManager.addElement(currentWeatherData.getCityName(), currentWeatherData.getMainDescription(), currentWeatherData.getCurrentTemp());
                    mutableLiveData.setValue(citiesBaseManager.getCitiesList());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
    }

    public MutableLiveData<ArrayList<City>> addData(String cityName) {
        updateData(cityName);
        return this.mutableLiveData;
    }

    public MutableLiveData<ArrayList<City>> deleteData(int chosenPosition) {
        citiesBaseManager.deleteElement(chosenPosition);
        mutableLiveData.setValue(citiesBaseManager.getCitiesList());
        return this.mutableLiveData;
    }

    public MutableLiveData<ArrayList<City>> getMutableLiveData() {
        mutableLiveData.setValue(citiesBaseManager.getCitiesList());
        return this.mutableLiveData;
    }

    public void refreshData() {
        ArrayList<City> citiesList = citiesBaseManager.getCitiesList();
        citiesBaseManager.clearBase();
        for (City city : citiesList) {
            dataController = new DataController(city.getCityName(), new CurrentDataCallback() {
                @Override
                public void onDataGet(CurrentWeatherData currentWeatherData) {
                    citiesBaseManager.addElement(currentWeatherData.getCityName(), currentWeatherData.getMainDescription(), currentWeatherData.getCurrentTemp());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
        mutableLiveData.setValue(citiesBaseManager.getCitiesList());
    }
}

//citiesBaseManager.addElement(currentWeatherData.getCityName(),currentWeatherData.getMainDescription(), currentWeatherData.getCurrentTemp());
//        mutableLiveData.setValue(citiesBaseManager.getCitiesList());