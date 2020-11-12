package com.example.myapplication.geolocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.network.NetworkService;

public class LocationPreferenceManager {

    private static LocationPreferenceManager singleton;
    private final SQLiteDatabase sqLiteDatabase;
    private final ContentValues contentValues;

    public static LocationPreferenceManager getInstance(Context context) {
        if (singleton == null) {
            singleton = new LocationPreferenceManager(context.getApplicationContext());
        }
        return singleton;
    }


    private LocationPreferenceManager(Context context) {
        LocationPreferencesBase locationPreferencesBase = new LocationPreferencesBase(context);
        sqLiteDatabase = locationPreferencesBase.getWritableDatabase();
        contentValues = new ContentValues();
    }

    public void addPreference(String latitude, String longitude) {
        contentValues.put(LocationPreferencesBase.KEY_LATITUDE, latitude);
        contentValues.put(LocationPreferencesBase.KEY_LONGITUDE, longitude);
        sqLiteDatabase.insert(LocationPreferencesBase.TABLE_PREFERENCES, null, contentValues);
    }
    public void clearPreference(){
        sqLiteDatabase.delete(LocationPreferencesBase.TABLE_PREFERENCES, null, null);
    }

    public void getSavedPreferences() {
        Cursor cursor = sqLiteDatabase.query(LocationPreferencesBase.TABLE_PREFERENCES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int latitudeIndex = cursor.getColumnIndex(LocationPreferencesBase.KEY_LATITUDE);
            int longitudeIndex = cursor.getColumnIndex(LocationPreferencesBase.KEY_LONGITUDE);
            do {
                WeatherLocationListener.getInstance().setLatitude(Double.parseDouble(cursor.getString(latitudeIndex)));
                WeatherLocationListener.getInstance().setLongitude(Double.parseDouble(cursor.getString(longitudeIndex)));
            } while (cursor.moveToNext());
        } else Log.d("mLog", "0 row");
        cursor.close();
    }


}
