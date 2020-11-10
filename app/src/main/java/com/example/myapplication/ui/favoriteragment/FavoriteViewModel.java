package com.example.myapplication.ui.favoriteragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.ctrl.DataController;
import com.example.myapplication.database.citiesfavoritebase.CitiesBaseManager;

public class FavoriteViewModel {

    private DataController dataController;
    private ListView mCityListView;
    private Button deleteButton;
    private EditText mCityTextEdit;
    private String cityName;
    private CitiesBaseManager citiesBaseManager;
    private int chosenPosition;

}
