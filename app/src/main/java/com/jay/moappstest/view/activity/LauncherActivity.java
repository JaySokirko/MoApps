package com.jay.moappstest.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jay.moappstest.R;
import com.jay.moappstest.di.DaggerLauncherComponent;
import com.jay.moappstest.di.SharedPrefModule;
import com.jay.moappstest.utils.SharedPref;

import javax.inject.Inject;

public class LauncherActivity extends AppCompatActivity {

    @Inject
    SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DaggerLauncherComponent.builder()
                .sharedPrefModule(new SharedPrefModule(this))
                .build()
                .inject(this);

        boolean isSignedIn = sharedPref.getBooleanData("signIn");

        if (isSignedIn) {

            startActivity(new Intent(LauncherActivity.this, AppsListActivity.class));
            finish();

        } else {
            startActivity(new Intent(LauncherActivity.this, SignInActivity.class));
            finish();
        }
    }
}
