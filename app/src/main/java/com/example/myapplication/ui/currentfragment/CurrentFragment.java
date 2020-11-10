package com.example.myapplication.ui.currentfragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.R;

public class CurrentFragment extends Fragment {

    private TextView mCityName, mCurrentDate,
            mCurrentTemp, mMaxAndMinTemp, mWeatherDescription,
            mWindSpeed, mWindDestination, mPressure, mHumidity;
    private ImageView imageView;

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

        CurrentViewModel viewModel = ViewModelProviders.of(this).get(CurrentViewModel.class);
        viewModel.setContext(getContext());
        viewModel.updateLocation();
        viewModel.getData().observe(this,
                new Observer<CurrentWeatherData>() {
                    @Override
                    public void onChanged(@Nullable CurrentWeatherData currentWeatherDataConstructor) {
                        try {
                            assert currentWeatherDataConstructor != null;
                            setData(currentWeatherDataConstructor);
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                });
        return rootView;
    }

    @SuppressLint("SetTextI18n")
    public void setData(CurrentWeatherData weatherDataConstructor) {
        mCityName.setText(weatherDataConstructor.getCityName());
        mCurrentDate.setText(weatherDataConstructor.getDate());
        mCurrentTemp.setText(weatherDataConstructor.getCurrentTemp() + " C°");
        mWindSpeed.setText("Скорость ветра " + weatherDataConstructor.getWindSpeed() + "М/с");
        mWindDestination.setText("Направление ветра " + weatherDataConstructor.getWindDestination() + "°");
        mPressure.setText("Давление " + weatherDataConstructor.getPressure() + " Ртутного столбца");
        mHumidity.setText("Влажность " + weatherDataConstructor.getHumidity());
        mMaxAndMinTemp.setText("Максимальная за день: " + weatherDataConstructor.getMaxDailyTemp() + " C°\n" + "Минимальная за день: " + weatherDataConstructor.getMinDailyTemp() + " C°");

        switch (weatherDataConstructor.getMainDescription()) {
            case (TagWEaterDescription.CLEAR):
                imageView.setImageResource(R.drawable.clear);
                mWeatherDescription.setText(R.string.ClearDescript);
                break;
            case (TagWEaterDescription.RAIN):
                imageView.setImageResource(R.drawable.rain);
                mWeatherDescription.setText(R.string.Rain);
                break;
            case (TagWEaterDescription.SNOW):
                imageView.setImageResource(R.drawable.snow);
                mWeatherDescription.setText(R.string.Snow);
                break;
            case (TagWEaterDescription.CLOUDS):
                imageView.setImageResource(R.drawable.clouds);
                mWeatherDescription.setText(R.string.Clouds);
                break;
            case (TagWEaterDescription.THUNDERSTORM):
                imageView.setImageResource(R.drawable.thunder);
                mWeatherDescription.setText(R.string.Thunderstorm);
                break;
            case (TagWEaterDescription.MIST):
                imageView.setImageResource(R.drawable.mist);
                mWeatherDescription.setText(R.string.Mist);
                break;
            case (TagWEaterDescription.DRIZZLE):
                imageView.setImageResource(R.drawable.showerrain);
                mWeatherDescription.setText(R.string.Drizzle);
                break;
            case (TagWEaterDescription.FOG):
                imageView.setImageResource(R.drawable.mist);
                mWeatherDescription.setText(R.string.Fog);
        }
    }

    static final class TagWEaterDescription{
        static final String CLEAR = "Clear";
        static final String RAIN = "Rain";
        static final String SNOW = "Snow";
        static final String CLOUDS = "Clouds";
        static final String THUNDERSTORM = "Thunderstorm";
        static final String MIST = "Mist";
        static final String DRIZZLE = "Drizzle";
        static final String FOG = "Fog";
    }
}
