package com.jay.moappstest.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jay.moappstest.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        boolean isSignedIn = getSharedPreferences("Settings", MODE_PRIVATE)
                .getBoolean("signIn",false);

        if (isSignedIn){

            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
            finish();

        } else {
            startActivity(new Intent(LauncherActivity.this, SignInActivity.class));
            finish();
        }
    }
}
