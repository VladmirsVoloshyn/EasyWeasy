package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.myapplication.R;

public class WeatherActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        BottomNavigationView view = findViewById(R.id.navigation);
        view.setOnNavigationItemSelectedListener(this);
        CurrentFragment homeFragment  = new CurrentFragment();
        loadFragment(homeFragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new CurrentFragment();
                break;
            case R.id.navigation_dashboard:
                fragment = new ForecastFragment();
                break;
            case R.id.navigation_notifications:
                fragment = new FavoriteFragment();
                break;
        }

        return loadFragment(fragment);
    }


}