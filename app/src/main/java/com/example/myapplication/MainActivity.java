package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.geolocation.LocationCallback;
import com.example.myapplication.geolocation.WeatherLocationListener;
import com.example.myapplication.ui.WeatherActivity;

public class MainActivity extends AppCompatActivity implements LocationCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextAction();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    WeatherLocationListener.getInstance().setUpLocationListener(this, this);
                    WeatherLocationListener.getInstance().requestLocation();
                    if (WeatherLocationListener.getInstance().imHere != null) {
                        Intent intent = new Intent(this, WeatherActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    public void onStartClick(View view) {
        requestLocationPermission();
        nextAction();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void onLocationChanged(Location location) {
        WeatherLocationListener.getInstance().requestLocation();
    }

    void nextAction(){
        if (WeatherLocationListener.getInstance().setPermissionCheck(this)){
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        }
        else return;
    }

}
