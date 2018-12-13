package com.jay.moappstest.di;

import com.jay.moappstest.AppListContract;
import com.jay.moappstest.SignInContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private SignInContract.View signInView;

    private AppListContract.View appListView;


    public PresenterModule(SignInContract.View signInView) {
        this.signInView = signInView;
    }


    public PresenterModule(AppListContract.View appListView) {
        this.appListView = appListView;
    }


    @Provides
    SignInContract.View provideSignInView(){
        return signInView;
    }


    @Provides
    AppListContract.View provideAppListView(){
        return appListView;
    }
}
