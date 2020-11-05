package com.example.myapplication.geolocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class LocationPreferencesBase extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "LocationPreferencesBase";
    static final String TABLE_PREFERENCES = "locationPreferences";

    static final String KEY_ID = "_id";
    static final String KEY_LATITUDE = "latitude";
    static final String KEY_LONGITUDE = "longitude";

    public LocationPreferencesBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PREFERENCES + "("
                + KEY_ID + " integer primary key,"
                + KEY_LATITUDE + " text,"
                + KEY_LONGITUDE+ " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PREFERENCES);
        onCreate(db);
    }
}
