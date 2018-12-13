package com.jay.moappstest.di;

import com.jay.moappstest.SignInContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private SignInContract.View view;

    public PresenterModule(SignInContract.View view) {
        this.view = view;
    }

    @Provides
    SignInContract.View provideSignInView(){
        return view;
    }
}
