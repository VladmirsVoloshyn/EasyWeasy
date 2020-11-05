package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WeatherPushService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
