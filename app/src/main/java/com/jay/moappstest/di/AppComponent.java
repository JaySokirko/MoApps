package com.jay.moappstest.di;

import com.jay.moappstest.view.activity.AppsListActivity;
import com.jay.moappstest.view.activity.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, SharedPrefModule.class})
public interface AppComponent {

    void inject(SignInActivity signInActivity);

    void inject(AppsListActivity appsListActivity);
}
