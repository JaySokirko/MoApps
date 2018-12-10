package com.jay.moappstest.di;

import android.app.Application;

import com.jay.moappstest.api.ApiClient;

public class MyApplication extends Application {

    private ApiComponent apiComponent;

    private static final String BASE_URL = "https://html5.mo-apps.com";

    @Override
    public void onCreate() {
        super.onCreate();

//        apiComponent = DaggerApiComponent.builder()
//                .apiClient(new ApiClient(BASE_URL))
//                .build();
    }

    public ApiComponent getNetComponent() {
        return apiComponent;
    }
}
