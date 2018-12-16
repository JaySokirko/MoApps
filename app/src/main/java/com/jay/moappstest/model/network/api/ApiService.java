package com.jay.moappstest.model.network.api;

import com.jay.moappstest.model.network.entity.request.AppsListRequest;
import com.jay.moappstest.model.network.entity.request.UserTokenRequest;
import com.jay.moappstest.model.network.entity.response.AppsListResponse;
import com.jay.moappstest.model.network.entity.response.UserTokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {

    @POST("/api/Account/Login")
    Call<UserTokenResponse> getUser(@Body UserTokenRequest userTokenRequest);

    @POST("/api/application")
    Call<AppsListResponse> getUserApps(@Body AppsListRequest appsList);
}
