package com.example.myapplication.geolocation;


class LocationPreference {
    private double prefLatitude = 0;
    private double prefLongitude = 0;
    private LocationPreference mUniqueLocationPreference;

    public double getPrefLatitude() {
        return prefLatitude;
    }

    public void setPrefLatitude(double prefLatitude) {
        this.prefLatitude = prefLatitude;
    }

    public double getPrefLongitude() {
        return prefLongitude;
    }

    public void setPrefLongitude(double prefLongitude) {
        this.prefLongitude = prefLongitude;
    }

    private LocationPreference() {

    }

    ;

    public LocationPreference getInstance() {
        if (mUniqueLocationPreference == null) {
            mUniqueLocationPreference = new LocationPreference();
        }
        return mUniqueLocationPreference;
    }
}

