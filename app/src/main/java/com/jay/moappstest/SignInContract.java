package com.jay.moappstest;

public interface SignInContract {


    interface View {

        void showProgress();

        void hideProgress();

        void onEmailError();

        void onPasswordError();

        void onSuccessResponse(String token);

        void onFailureResponse(Throwable throwable);
    }


    interface Presenter{

        void onDestroy();

        void onSignInClick(String userNick, String password);
    }


    interface Model{

        interface OnLoadFinishedListener {

            void onLoadFinished(String token);

            void onLoadFailure(Throwable t);
        }

        void getToken(OnLoadFinishedListener listener, String email, String password);
    }
}
