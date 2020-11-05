package com.example.myapplication.data;

import com.example.myapplication.ValuesFormat.DateFormat;
import com.example.myapplication.ValuesFormat.DegreesFormat;
import com.example.myapplication.data.ForecastData.Main;
import com.example.myapplication.ui.customize.datetags.WeatherListTag;

import java.util.ArrayList;

public class DailyWeatherDataConstructor {

    public ArrayList<WeatherListTag> weatherListTagArrayList = new ArrayList<>();

    public ArrayList<WeatherListTag> getWeatherListTagArrayList() {
        return weatherListTagArrayList;
    }

    public DailyWeatherDataConstructor() {
    }

    public void fillListData(Main dailyWeather) {
        this.weatherListTagArrayList.clear();
        for (int i = 1; i < dailyWeather.getDaily().size(); i++) {
            this.weatherListTagArrayList.add(new WeatherListTag(DateFormat.formatToGMT(dailyWeather.getDaily().get(i).getDt()), Math.round(DegreesFormat.formatToCelsius(dailyWeather.getDaily().get(i).getTemp().getDay())) + " C°", dailyWeather.getDaily().get(i).getWeather().get(0).getMain()));
        }
    }

    public void build(Main dailyWeather) {
        this.fillListData(dailyWeather);
    }
}
