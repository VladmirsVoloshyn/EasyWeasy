package com.example.myapplication.ui.favoriteragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.ctrl.WeatherDataCallback;
import com.example.myapplication.data.CurrentWeatherData;
import com.example.myapplication.data.ForecastWeatherData;
import com.example.myapplication.database.citiesfavoritebase.CitiesBaseManager;
import com.example.myapplication.ui.customize.city.City;
import com.example.myapplication.ui.customize.city.CityAdapter;

import java.util.ArrayList;
import java.util.Objects;


public class FavoriteFragment extends Fragment {

    private ListView mCityListView;
    private Button deleteButton;
    private EditText mCityTextEdit;
    private int chosenPosition;
    private FavoriteViewModel favoriteViewModel;

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
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.init(getContext());

        favoriteViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<City>>() {
            @Override
            public void onChanged(@Nullable ArrayList<City> arrayList) {
                updateHud(arrayList);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteViewModel.addData(mCityTextEdit.getText().toString()).observe(FavoriteFragment.this, new Observer<ArrayList<City>>() {
                    @Override
                    public void onChanged(@Nullable ArrayList<City> arrayList) {
                        updateHud(arrayList);
                    }
                });
                mCityTextEdit.setText("");
                mCityTextEdit.setHint("city_name");
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteViewModel.deleteData(chosenPosition).observe(FavoriteFragment.this, new Observer<ArrayList<City>>() {
                    @Override
                    public void onChanged(@Nullable ArrayList<City> arrayList) {

                    }
                });
            }
        });
        mCityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteButton.setVisibility(Button.VISIBLE);
                chosenPosition = position;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return rootView;
    }


    void updateHud(ArrayList<City> cities) {
        CityAdapter cityAdapter = new CityAdapter(getContext(), cities);
        mCityListView.setAdapter(cityAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}