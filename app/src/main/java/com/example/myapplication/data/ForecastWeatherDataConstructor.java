package com.example.myapplication.data;

import android.arch.lifecycle.MutableLiveData;

import com.example.myapplication.ValuesFormat.DateFormat;
import com.example.myapplication.ValuesFormat.DegreesFormat;
import com.example.myapplication.data.ForecastData.Daily;
import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.ui.customize.datetags.WeatherListTag;

import java.util.ArrayList;

public class ForecastWeatherDataConstructor extends MutableLiveData<ForecastWeatherDataConstructor> {

    public ArrayList<WeatherListTag> weatherListTagArrayList = new ArrayList<>();

    public ArrayList<WeatherListTag> getWeatherListTagArrayList() {
        return weatherListTagArrayList;
    }

    public ForecastWeatherDataConstructor() {
    }

    public void fillDataToList(Main forecastData){
        for(Daily data : forecastData.getDaily())
        {
            this.weatherListTagArrayList.add(new WeatherListTag(DateFormat.formatToGMT(data.getDt()),
                    Math.round(DegreesFormat.formatToCelsius(data.getTemp().getDay())) + " C°",
                    data.getWeather().get(0).getMain()));
        }
    }

    public void build(Main dailyWeather) {
        this.fillDataToList(dailyWeather);
    }
}
