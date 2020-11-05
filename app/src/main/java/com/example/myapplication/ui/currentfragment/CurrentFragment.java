package com.example.myapplication.ui.currentfragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.R;
import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;

import static com.google.gson.reflect.TypeToken.get;

public class CurrentFragment extends Fragment {

    private TextView mCityName, mCurrentDate, mCurrentTemp, mMaxAndMinTemp, mWeatherDescription, mWindSpeed, mWindDestination, mPressure, mHumidity;
    private ImageView imageView;
    private CurrentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View rootView =
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

        viewModel = (CurrentViewModel) ViewModelProviders.of(this).get(CurrentViewModel.class);
        viewModel.setContext(getContext());
        viewModel.updateLocation();
        viewModel.getData().observe(this, new Observer<CurrentWeatherDataConstructor>() {
            @Override
            public void onChanged(@Nullable CurrentWeatherDataConstructor currentWeatherDataConstructor) {
                setData(currentWeatherDataConstructor);
            }
        });
        return rootView;
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
