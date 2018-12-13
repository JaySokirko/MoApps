package com.jay.moappstest.di;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    private Context context;

    public SharedPrefModule(Context context) {
        this.context = context;
    }


    @Provides
    SharedPreferences provideSharedPreferences(){
        return context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }
}
