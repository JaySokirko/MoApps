package com.jay.moappstest.presenter;

import com.jay.moappstest.SignInContract;
import com.jay.moappstest.model.network.api.ApiUserToken;

import javax.inject.Inject;


public class SignInPresenter implements SignInContract.Presenter, SignInContract.Model.OnLoadFinishedListener {

    private SignInContract.View view;

    private SignInContract.Model model = new ApiUserToken();


    @Inject
    SignInPresenter(SignInContract.View view) {
        this.view = view;
    }


    @Override
    public void onDestroy() {

        this.view = null;
        this.model = null;
    }


    @Override
    public void onSignInClick(String email, String password) {

        if (view != null) {

            if (email.isEmpty()) {
                view.showEmailError();

            } else if (password.length() < 6) {
                view.showPasswordError();

            } else {
                view.showProgress();
                model.getToken(this, email, password);
            }
        }
    }


    @Override
    public void onLoadFinished(String token) {

        if (view != null) {
            view.hideProgress();
            view.onSuccessResponse(token);
        }
    }


    @Override
    public void onLoadFailure(Throwable t) {

        if (view != null) {
            view.hideProgress();
            view.onFailureResponse(t);
        }
    }
}
