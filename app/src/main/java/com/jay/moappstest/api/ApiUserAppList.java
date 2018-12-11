package com.jay.moappstest.api;

import com.jay.moappstest.AppListContract;
import com.jay.moappstest.model.request.AppsListRequest;
import com.jay.moappstest.model.response.AppsList;
import com.jay.moappstest.model.response.AppsListResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUserAppList implements AppListContract.Model {

    private ArrayList<String> appImagesList = new ArrayList<>();
    private ArrayList<String> appNamesList = new ArrayList<>();
    private ArrayList<Date> datePaidList = new ArrayList<>();
    private ArrayList<Boolean> isCompleteList = new ArrayList<>();
    private ArrayList<String> urlsList = new ArrayList<>();

    @Override
    public void getUserAppList(onLoadFinishedListener listener, String token) {

        AppsListRequest userApps = new AppsListRequest();
        userApps.setOsType(0);
        userApps.setSkip(0);
        userApps.setTake(1000);
        userApps.setUserToken(token);

        appImagesList.clear();
        appNamesList.clear();
        datePaidList.clear();
        isCompleteList.clear();
        urlsList.clear();

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<AppsListResponse> call = apiService.getUserApps(userApps);
        call.enqueue(new Callback<AppsListResponse>() {
            @Override
            public void onResponse(Call<AppsListResponse> call, Response<AppsListResponse> response) {

                List<AppsList> appsList = response.body().getData();

                for (AppsList list : appsList) {

                    appNamesList.add(list.getApplicationName());
                    appImagesList.add(list.getApplicationIcoUrl());
                    datePaidList.add(list.getEndOfPayment());
                    isCompleteList.add(list.isPayment());
                    urlsList.add(list.getApplicationUrl());
                }

                listener.onLoadFinished(appNamesList, appImagesList, datePaidList, isCompleteList,
                        urlsList);
            }

            @Override
            public void onFailure(Call<AppsListResponse> call, Throwable t) {

                listener.onLoadFailure(t);
            }
        });
    }
}
