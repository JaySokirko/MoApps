package com.jay.moappstest.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPref {

    private SharedPreferences preferences;

    @Inject
    public SharedPref(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void putData(String key, boolean isSignedIn){
        preferences.edit().putBoolean(key, isSignedIn).apply();
    }

    public void putData(String key, String token){
        preferences.edit().putString(key, token).apply();
    }

    public boolean getBooleanData(String key){
        return preferences.getBoolean(key, false);
    }
}
