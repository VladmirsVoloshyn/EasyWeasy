package com.example.myapplication.geolocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.myapplication.MainActivity;


public class WeatherLocationListener implements LocationListener {
    public static WeatherLocationListener mUniqueLocation;
    public static Location imHere = null;
    private LocationCallback callback;
    public LocationManager locationManager;
    public double longitude;
    public double latitude;
    public boolean isAccess = false;

    public boolean setPermissionCheck(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            return this.isAccess = true;
        return isAccess;

    }

    public static WeatherLocationListener getInstance() {
        if (mUniqueLocation == null) {
            mUniqueLocation = new WeatherLocationListener();
        }
        return mUniqueLocation;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setUpLocationListener(MainActivity context, LocationCallback callback) {
        this.callback = callback;
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        requestLocation();
    }

    public void setUpLocationListener(Context context, LocationCallback callback) {
        this.callback = callback;
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        requestLocation();
    }

    @SuppressLint("MissingPermission")
    public void requestLocation() {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                10,
                this);
        imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onLocationChanged(Location location) {
        imHere = location;
        this.latitude = imHere.getLatitude();
        this.longitude = imHere.getLongitude();
        callback.onLocationChanged(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public String latitudeToString() {
        return String.valueOf(this.getLatitude());
    }

    public String longitudeToString() {
        return String.valueOf(this.getLongitude());
    }
}
