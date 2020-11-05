package com.example.myapplication.database.citiesfavoritebase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CityBase extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "CitiesDataBase";
    static final String TABLE_CITIES = "cities";

    static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_TEMPERATURE = "temperature";


    public CityBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CITIES + "("
                + KEY_ID + " integer primary key,"
                + KEY_NAME + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_TEMPERATURE + " text" +  ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CITIES);

        onCreate(db);
    }
}
