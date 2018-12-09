package com.jay.moappstest.di;

import android.app.Application;

import com.jay.moappstest.api.ApiModule;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    private static final String BASE_URL = "https://html5.mo-apps.com";

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule(BASE_URL))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}
