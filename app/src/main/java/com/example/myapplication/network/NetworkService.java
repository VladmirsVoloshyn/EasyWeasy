package com.example.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    public static NetworkService mUniqueNetwork;
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String BASE_APPID = "56882f34de7c6d69857a27e5288c52c8";
    public static final String EXCLUDING_PARAMETERS = "current,minutely,hourly";
    private Retrofit retrofit;


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

