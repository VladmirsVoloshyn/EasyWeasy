package com.example.myapplication.database.citiesfavoritebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.ui.customize.city.City;

import java.util.ArrayList;

public class CitiesBaseManager {

    private final ContentValues contentValues;
    private final SQLiteDatabase sqLiteDatabase;
    private final ArrayList<City> citiesList;

    public CitiesBaseManager(Context context) {
        CityBase cityBase = new CityBase(context);
        sqLiteDatabase = cityBase.getWritableDatabase();
        contentValues = new ContentValues();
        citiesList = new ArrayList<>();
        fillDataToList();
    }

    public ArrayList<City> getCitiesList() {
        return citiesList;
    }


    public void fillDataToList() {
        Cursor cursor = sqLiteDatabase.query(CityBase.TABLE_CITIES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(CityBase.KEY_ID);
            int nameIndex = cursor.getColumnIndex(CityBase.KEY_NAME);
            int descriptionIndex = cursor.getColumnIndex(CityBase.KEY_DESCRIPTION);
            int temperatureIndex = cursor.getColumnIndex(CityBase.KEY_TEMPERATURE);
            do {
                citiesList.add(new City(cursor.getString(nameIndex), cursor.getString(descriptionIndex), cursor.getString(temperatureIndex), cursor.getInt(idIndex)));
            } while (cursor.moveToNext());
        } else Log.d("mLog", "0 row");
        cursor.close();
    }


    public void addElement(final String name, final String description, final String temperature) {
                contentValues.put(CityBase.KEY_NAME, name);
                contentValues.put(CityBase.KEY_DESCRIPTION, description);
                contentValues.put(CityBase.KEY_TEMPERATURE, temperature);
                sqLiteDatabase.insert(CityBase.TABLE_CITIES, null, contentValues);
        fillDataToList();
    }

    public void deleteElement(final int position) {
                int id = getCitiesList().get(position).getId();
                int delCount = sqLiteDatabase.delete(CityBase.TABLE_CITIES, CityBase.KEY_ID + "= " + id, null);
                Log.d("mLog", "delete rows count = " + delCount);
                fillDataToList();
            }

}
