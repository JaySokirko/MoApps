package com.jay.moappstest.di;

import com.jay.moappstest.api.ApiClient;
import com.jay.moappstest.view.activity.AppsListActivity;
import com.jay.moappstest.view.activity.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;


public interface ApiComponent {

    void injectSignInActivity(SignInActivity activity);

    void injectMainActivity(AppsListActivity activity);
}