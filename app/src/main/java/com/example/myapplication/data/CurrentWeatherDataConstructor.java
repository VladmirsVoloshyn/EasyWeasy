package com.example.myapplication.data;


import com.example.myapplication.ValuesFormat.DateFormat;
import com.example.myapplication.ValuesFormat.DegreesFormat;
import com.example.myapplication.data.WeatherData.WeatherData;

public class CurrentWeatherDataConstructor {
    private String cityName;
    private String currentTemp;
    private String maxDailyTemp;
    private String minDailyTemp;
    private String windDestination;
    private String humidity;
    private String pressure;
    private String windSpeed;


    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }


    public String getMaxDailyTemp() {
        return maxDailyTemp;
    }

    public void setMaxDailyTemp(String maxDailyTemp) {
        this.maxDailyTemp = maxDailyTemp;
    }

    public String getMinDailyTemp() {
        return minDailyTemp;
    }

    public void setMinDailyTemp(String minDailyTemp) {
        this.minDailyTemp = minDailyTemp;
    }


    public String getCurrentTemp() {
        return currentTemp;
    }

    public String getWindDestination() {
        return windDestination;
    }

    public void setWindDestination(String windDestination) {
        this.windDestination = windDestination;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public String getMainDescription() {
        return mainDescription;
    }

    public void setMainDescription(String mainDescription) {
        this.mainDescription = mainDescription;
    }

    private String mainDescription;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CurrentWeatherDataConstructor() {
    }

    public void build(WeatherData weatherData) {
        this.setCityName(weatherData.getName());
        this.setMainDescription(weatherData.getWeather().get(0).getMain());
        this.setDate(DateFormat.formatToGMT(weatherData.getDt()));
        this.setCurrentTemp(String.valueOf(Math.round(DegreesFormat.formatToCelsius(weatherData.getMain().getTemp()))));
        this.setMaxDailyTemp(String.valueOf(Math.round(DegreesFormat.formatToCelsius(weatherData.getMain().getTempMax()))));
        this.setMinDailyTemp(String.valueOf(Math.round(DegreesFormat.formatToCelsius(weatherData.getMain().getTempMin()))));
        this.setWindSpeed(String.valueOf(weatherData.getWind().getSpeed()));
        this.setWindDestination(String.valueOf(weatherData.getWind().getDeg()));
        this.setHumidity(String.valueOf(weatherData.getMain().getHumidity()));
        this.setPressure(String.valueOf(weatherData.getMain().getPressure()));
    }

}
