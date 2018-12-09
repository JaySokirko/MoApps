package com.jay.moappstest.api;

import com.jay.moappstest.model.request.AppsListRequest;
import com.jay.moappstest.model.request.UserTokenRequest;
import com.jay.moappstest.model.response.AppsListResponse;
import com.jay.moappstest.model.response.UserTokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {

    @POST("/api/Account/Login")
    Call<UserTokenResponse> getUser(@Body UserTokenRequest userTokenRequest);

    @POST("/api/application")
    Call<AppsListResponse> getUserApps(@Body AppsListRequest appsList);
}
