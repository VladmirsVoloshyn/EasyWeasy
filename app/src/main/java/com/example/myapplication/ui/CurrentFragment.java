package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.R;
import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;

public class CurrentFragment extends Fragment implements LocationCallback {

    private SharedPreferences locationPreferences;
    final String SAVED_LATITUDE_PREFERENCES = "saved latitude";
    final String SAVED_LONGITUDE_PREFERENCES = "saved longitude";
    double prefLat, prefLon;
    private TextView mCityName, mCurrentDate, mCurrentTemp, mMaxAndMinTemp, mWeatherDescription, mWindSpeed, mWindDestination, mPressure, mHumidity;
    public DataController dataController;
    public ImageView imageView;
    public ProgressBar progressBar;
    private TextView mLoadingView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_current, null);

        mCityName = rootView.findViewById(R.id.CityNameText);
        mCurrentDate = rootView.findViewById(R.id.CurrentDate);
        mCurrentTemp = rootView.findViewById(R.id.CurrentDegree);
        mWeatherDescription = rootView.findViewById(R.id.weatherTextDesctiption);
        mMaxAndMinTemp = rootView.findViewById(R.id.MaxAndMinDegree);
        mWindSpeed = rootView.findViewById(R.id.WindSpeedText);
        mWindDestination = rootView.findViewById(R.id.WindDestinationText);
        mPressure = rootView.findViewById(R.id.PressureText);
        mHumidity = rootView.findViewById(R.id.HumidityText);
        imageView = rootView.findViewById(R.id.weatherIconView);
        progressBar = rootView.findViewById(R.id.progressBar);
        mLoadingView = rootView.findViewById(R.id.LoadingText);

        hideHud();

        isOnline(getContext());

        WeatherLocationListener.getInstance().setUpLocationListener(getContext(), this);
        WeatherLocationListener.getInstance().requestLocation();

        locationPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (locationPreferences.getString(SAVED_LONGITUDE_PREFERENCES, "") != "" & locationPreferences.getString(SAVED_LATITUDE_PREFERENCES, "") != "") {

            locationPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            prefLon = Double.parseDouble(locationPreferences.getString(SAVED_LONGITUDE_PREFERENCES, ""));
            prefLat = Double.parseDouble(locationPreferences.getString(SAVED_LATITUDE_PREFERENCES, ""));

            WeatherLocationListener.getInstance().setLongitude(prefLon);
            WeatherLocationListener.getInstance().setLatitude(prefLat);
        }

        dataController = new DataController(new WeatherDataCallback() {
            @Override
            public void onDataGet(CurrentWeatherDataConstructor currentWeatherData) {
                if (locationPreferences.getString(SAVED_LONGITUDE_PREFERENCES, "") != "" & locationPreferences.getString(SAVED_LATITUDE_PREFERENCES, "") != "") {
                    setData(currentWeatherData);
                    showHud();
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    mLoadingView.setVisibility(TextView.INVISIBLE);
                } else {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    mLoadingView.setVisibility(TextView.VISIBLE);
                }
            }

            @Override
            public void onDataGet(DailyWeatherDataConstructor dailyWeatherDataConstructor) {

            }
        });

        return rootView;
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

        locationPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = locationPreferences.edit();
        editor.putString(SAVED_LATITUDE_PREFERENCES, String.valueOf(WeatherLocationListener.getInstance().getLatitude()));
        editor.putString(SAVED_LONGITUDE_PREFERENCES, String.valueOf(WeatherLocationListener.getInstance().getLongitude()));
        editor.apply();


        dataController.updateData();

    }

    public void showHud() {
        mCityName.setVisibility(TextView.VISIBLE);
        mCurrentDate.setVisibility(TextView.VISIBLE);
        mCurrentTemp.setVisibility(TextView.VISIBLE);
        mWeatherDescription.setVisibility(TextView.VISIBLE);
        mMaxAndMinTemp.setVisibility(TextView.VISIBLE);
        mWindSpeed.setVisibility(TextView.VISIBLE);
        mWindDestination.setVisibility(TextView.VISIBLE);
        mPressure.setVisibility(TextView.VISIBLE);
        mHumidity.setVisibility(TextView.VISIBLE);
        imageView.setVisibility(ImageView.VISIBLE);
    }

    public void hideHud() {
        mCityName.setVisibility(TextView.INVISIBLE);
        mCurrentDate.setVisibility(TextView.INVISIBLE);
        mCurrentTemp.setVisibility(TextView.INVISIBLE);
        mWeatherDescription.setVisibility(TextView.INVISIBLE);
        mMaxAndMinTemp.setVisibility(TextView.INVISIBLE);
        mWindSpeed.setVisibility(TextView.INVISIBLE);
        mWindDestination.setVisibility(TextView.INVISIBLE);
        mPressure.setVisibility(TextView.INVISIBLE);
        mHumidity.setVisibility(TextView.INVISIBLE);
        imageView.setVisibility(ImageView.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    public void setData(CurrentWeatherDataConstructor weatherDataConstructor) {
        mCityName.setText(weatherDataConstructor.getCityName());
        mCurrentDate.setText(weatherDataConstructor.getDate());
        mCurrentTemp.setText(weatherDataConstructor.getCurrentTemp() + " C°");
        mWindSpeed.setText("Скорость ветра " + weatherDataConstructor.getWindSpeed() + "М/с");
        mWindDestination.setText("Направление ветра " + weatherDataConstructor.getWindDestination() + "°");
        mPressure.setText("Давление " + weatherDataConstructor.getPressure() + " Ртутного столбца");
        mHumidity.setText("Влажность " + weatherDataConstructor.getHumidity());
        mMaxAndMinTemp.setText("Максимальная за день: " + weatherDataConstructor.getMaxDailyTemp() + " C°\n" + "Минимальная за день: " + weatherDataConstructor.getMinDailyTemp() + " C°");
        setIcon(weatherDataConstructor);
    }

    public void setIcon(CurrentWeatherDataConstructor weatherDataConstructor) {

        switch (weatherDataConstructor.getMainDescription()) {
            case ("Clear"):
                imageView.setImageResource(R.drawable.clear);
                mWeatherDescription.setText("Ясно");
                break;
            case ("Rain"):
                imageView.setImageResource(R.drawable.rain);
                mWeatherDescription.setText("Дождь");
                break;
            case ("Snow"):
                imageView.setImageResource(R.drawable.snow);
                mWeatherDescription.setText("Снег");
                break;
            case ("Clouds"):
                imageView.setImageResource(R.drawable.clouds);
                mWeatherDescription.setText("Облачно");
                break;
            case ("Thunderstorm"):
                imageView.setImageResource(R.drawable.thunder);
                mWeatherDescription.setText("Гроза");
                break;
            case ("Mist"):
                imageView.setImageResource(R.drawable.mist);
                mWeatherDescription.setText("Туман");
                break;
            case ("Drizzle"):
                imageView.setImageResource(R.drawable.showerrain);
                mWeatherDescription.setText("Изморось");
                break;
            case ("Fog"):
                imageView.setImageResource(R.drawable.mist);
                mWeatherDescription.setText("Сильный туман");
        }

    }
}
