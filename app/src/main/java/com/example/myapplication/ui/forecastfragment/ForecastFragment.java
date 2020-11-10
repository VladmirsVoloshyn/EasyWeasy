package com.example.myapplication.ui.forecastfragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.ui.customize.datetags.WeatherAdapter;

public class ForecastFragment extends Fragment {

    private TextView mCityName;
    private ListView mWeatherListView;
    private Button backButton;
    private Context forecastContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View rootView =
                inflater.inflate(R.layout.fragment_forecast, null);
        mWeatherListView = rootView.findViewById(R.id.weatherList);
        mCityName = rootView.findViewById(R.id.CityNameText);
        backButton = rootView.findViewById(R.id.buttonBack);

        forecastContext = getContext();

        ForecastViewModel forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);
        forecastViewModel.updateLocation();
        forecastViewModel.getData().observe(this, new Observer<ForecastWeatherData>() {
            @Override
            public void onChanged(@Nullable ForecastWeatherData forecastWeatherDataConstructor) {
                try {
                    if (forecastWeatherDataConstructor != null) {
                        setData(forecastWeatherDataConstructor);
                    }
                }catch (NullPointerException n){
                    Toast.makeText(getContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setVisibility(Button.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeatherListView.setVisibility(ListView.VISIBLE);
                backButton.setVisibility(Button.INVISIBLE);
            }
        });

        mWeatherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                mWeatherListView.setVisibility(ListView.INVISIBLE);
                backButton.setVisibility(Button.VISIBLE);
            }
        });
        return rootView;
    }

    public void setData(final ForecastWeatherData dailyWeatherDataConstructor) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(forecastContext, dailyWeatherDataConstructor.getWeatherListTagArrayList());
        mCityName.setText(dailyWeatherDataConstructor.getWeatherListTagArrayList().get(0).getTimeZone());
        mWeatherListView.setAdapter(weatherAdapter);

    }
}