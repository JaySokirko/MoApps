package com.jay.moappstest.di;

import com.jay.moappstest.view.activity.LauncherActivity;

import dagger.Component;

@Component(modules = {SharedPrefModule.class})
public interface LauncherComponent {

    void inject(LauncherActivity launcherActivity);
}
