package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.ui.about.AboutActivity;
import com.example.myapplication.ui.fragments.fragments.currentfragment.CurrentFragment;
import com.example.myapplication.ui.fragments.fragments.favoriteragment.FavoriteFragment;
import com.example.myapplication.ui.fragments.fragments.forecastfragmet.ForecastFragment;
import com.example.myapplication.ui.settings.SettingsActivity;

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

    @SuppressLint("NonConstantResourceId")
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(WeatherActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_About:
                Intent intent2 = new Intent(WeatherActivity.this, AboutActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}