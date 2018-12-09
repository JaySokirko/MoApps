package com.jay.moappstest.di;

import com.jay.moappstest.api.ApiModule;
import com.jay.moappstest.view.activity.MainActivity;
import com.jay.moappstest.view.activity.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void injectSignInActivity(SignInActivity activity);

    void injectMainActivity(MainActivity activity);
}