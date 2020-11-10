package com.example.myapplication.ui.favoriteragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.database.citiesfavoritebase.CitiesBaseManager;
import com.example.myapplication.ui.customize.city.CityAdapter;


public class FavoriteFragment extends Fragment {
    private DataController dataController;
    private ListView mCityListView;
    private Button deleteButton;
    private EditText mCityTextEdit;
    private String cityName;
    private CitiesBaseManager citiesBaseManager;
    private int chosenPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") final View rootView =
                inflater.inflate(R.layout.fragment_favorite, null);
        TextView mCurrentDate = rootView.findViewById(R.id.currentDateTextView);
        mCityListView = rootView.findViewById(R.id.cityAddView);
        Button backButton = rootView.findViewById(R.id.btnBack);
        deleteButton = rootView.findViewById(R.id.btnDelete);
        mCityTextEdit = rootView.findViewById(R.id.editTextCityName);
        mCurrentDate.setText(R.string.favoriteListDesc);
        deleteButton.setVisibility(Button.INVISIBLE);

        Button addButton = rootView.findViewById(R.id.btnAdd);

        updateHud();



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = mCityTextEdit.getText().toString();
                dataController = new DataController(cityName, new WeatherDataCallback() {
                    @Override
                    public void onCurrentDataGet(CurrentWeatherData currentWeatherData) {
                        citiesBaseManager.addElement(currentWeatherData.getCityName(), currentWeatherData.getMainDescription(), currentWeatherData.getCurrentTemp());
                        updateHud();
                    }

                    @Override
                    public void onForecastDataGet(ForecastWeatherData forecastWeatherData) {

                    }
                });
                mCityTextEdit.setText("");
                mCityTextEdit.setHint("city_name");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                citiesBaseManager.deleteElement(chosenPosition);
                deleteButton.setVisibility(Button.INVISIBLE);
                updateHud();
            }
        });
        mCityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteButton.setVisibility(Button.VISIBLE);
                chosenPosition = position;
            }
        });
        return rootView;
    }

    void updateHud() {
        citiesBaseManager = new CitiesBaseManager(getContext());
        CityAdapter cityAdapter = new CityAdapter(getContext(), citiesBaseManager);
        mCityListView.setAdapter(cityAdapter);
    }
}