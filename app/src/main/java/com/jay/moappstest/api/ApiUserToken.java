package com.jay.moappstest.api;

import com.jay.moappstest.SignInContract;
import com.jay.moappstest.model.request.UserTokenRequest;
import com.jay.moappstest.model.response.UserTokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUserToken implements SignInContract.Model {


    @Override
    public void getToken(OnLoadFinishedListener onLoadFinishedListener, String email, String password) {

        UserTokenRequest user = new UserTokenRequest();
        user.setUserNick(email);
        user.setPassword(password);

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<UserTokenResponse> call = apiService.getUser(user);
        call.enqueue(new Callback<UserTokenResponse>() {
            @Override
            public void onResponse(Call<UserTokenResponse> call, Response<UserTokenResponse> response) {

                String token = response.body().getData();
                onLoadFinishedListener.onLoadFinished(token);
            }

            @Override
            public void onFailure(Call<UserTokenResponse> call, Throwable t) {
                onLoadFinishedListener.onLoadFailure(t);
            }
        });
    }
}
