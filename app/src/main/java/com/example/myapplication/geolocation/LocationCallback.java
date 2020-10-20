package com.example.myapplication.geolocation;

import android.location.Location;

public interface LocationCallback {
    void onLocationChanged(Location location);
}
