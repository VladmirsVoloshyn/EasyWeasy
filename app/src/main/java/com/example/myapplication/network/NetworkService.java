package com.example.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mUniqueNetwork;
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String BASE_APP_ID = "5519720a2cdc30061db98135d1369667";
    public static final String EXCLUDING_PARAMETERS = "current,minutely,hourly";
    private final Retrofit retrofit;


    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mUniqueNetwork == null) {
            mUniqueNetwork = new NetworkService();
        }
        return mUniqueNetwork;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return retrofit.create(JSONPlaceHolderApi.class);
    }

}

