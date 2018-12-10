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

        interface OnFinishedListener {
            void onFinished(String token);

            void onFailure(Throwable t);
        }

        void getToken(OnFinishedListener onFinishedListener, String email, String password);
    }
}
