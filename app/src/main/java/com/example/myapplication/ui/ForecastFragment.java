package com.example.myapplication.ui;

import android.location.Location;
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

import com.example.myapplication.R;
import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.DailyWeatherDataConstructor;
import com.example.myapplication.data.CurrentWeatherDataConstructor;
import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;
import com.example.myapplication.ui.customize.WeatherAdapter;

public class ForecastFragment extends Fragment implements LocationCallback {
    private TextView mCityName;
    private DataController dataController;
    private ListView mWeatherListView;
    private WeatherAdapter weatherAdapter;
    private Button backButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_forecast, null);
        mWeatherListView = rootView.findViewById(R.id.weatherList);
        mCityName = rootView.findViewById(R.id.CityNameText);
        backButton = rootView.findViewById(R.id.buttonBack);
        backButton.setVisibility(Button.INVISIBLE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeatherListView.setVisibility(ListView.VISIBLE);
                backButton.setVisibility(Button.INVISIBLE);
            }
        });
        WeatherLocationListener.getInstance().setUpLocationListener(getContext(), this);
        WeatherLocationListener.getInstance().requestLocation();
        mWeatherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                mWeatherListView.setVisibility(ListView.INVISIBLE);
                backButton.setVisibility(Button.VISIBLE);
            }
        });
        dataController = new DataController(new WeatherDataCallback() {
            @Override
            public void onDataGet(CurrentWeatherDataConstructor dataFromActivity) {
                mCityName.setText(dataFromActivity.getCityName());
            }

            @Override
            public void onDataGet(DailyWeatherDataConstructor dailyWeatherDataConstructor) {
                setData(dailyWeatherDataConstructor);
            }
        });
        return rootView;

    }

    public void setData(final DailyWeatherDataConstructor dailyWeatherDataConstructor) {
        weatherAdapter = new WeatherAdapter(getContext(), dailyWeatherDataConstructor.getWeatherListTagArrayList());
        mWeatherListView.setAdapter(weatherAdapter);

    }


    @Override
    public void onLocationChanged(Location location) {
        dataController.updateData();
    }


}