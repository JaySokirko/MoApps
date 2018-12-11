package com.jay.moappstest;

import java.util.ArrayList;
import java.util.Date;

public interface AppListContract {

    interface View{

        void showProgress();

        void hideProgress();

        void hideRefreshingProgress();

        void setDataToAdapter(ArrayList<String> appNames, ArrayList<String> appImages,
                              ArrayList<Date> datePaid, ArrayList<Boolean> isComplete);

        void setUrlToViews(ArrayList<String> urlList);

        void onFailureResponse(Throwable throwable);
    }

    interface Presenter{

        void loadData(String token);

        void onSwipeRefreshLayout(String token);

        void onDestroy();
    }

    interface Model{

        interface onLoadFinishedListener{

            void onLoadFinished(ArrayList<String> appNames, ArrayList<String> appImages,
                                ArrayList<Date> datePaid, ArrayList<Boolean> isComplete,
                                ArrayList<String> urls);

            void onLoadFailure(Throwable t);
        }

        void getUserAppList(onLoadFinishedListener listener, String token);
    }
}
