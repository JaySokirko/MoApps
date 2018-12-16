package com.jay.moappstest.presenter;

import com.jay.moappstest.AppListContract;
import com.jay.moappstest.model.network.api.ApiUserAppList;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

public class AppListPresenter implements AppListContract.Presenter,
        AppListContract.Model.onLoadFinishedListener {


    private AppListContract.View view;
    private AppListContract.Model model = new ApiUserAppList();


    @Inject
    public AppListPresenter(AppListContract.View view) {
        this.view = view;
    }


    @Override
    public void loadData(String token) {

        if (view != null) {

            view.showProgress();
            model.getUserAppList(this, token);
        }
    }

    @Override
    public void onSwipeRefreshLayout(String token) {

        if (view != null){

            model.getUserAppList(this, token);
        }
    }

    @Override
    public void onDestroy() {

        this.view = null;
    }


    @Override
    public void onLoadFinished(ArrayList<String> appNames, ArrayList<String> appImages,
                               ArrayList<Date> datePaid, ArrayList<Boolean> isComplete,
                               ArrayList<String> urls) {

        if (view != null){

            view.hideProgress();
            view.hideRefreshingProgress();
            view.setDataToAdapter(appNames, appImages, datePaid, isComplete);
            view.setUrlToViews(urls);
        }
    }


    @Override
    public void onLoadFailure(Throwable t) {

        if (view != null){

            view.hideProgress();
            view.hideRefreshingProgress();
            view.onFailureResponse(t);
        }
    }
}
