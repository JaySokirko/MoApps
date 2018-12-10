package com.jay.moappstest.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jay.moappstest.R;
import com.jay.moappstest.adapter.AppsListAdapter;
import com.jay.moappstest.api.ApiClient;
import com.jay.moappstest.api.ApiService;
import com.jay.moappstest.di.MyApplication;
import com.jay.moappstest.model.request.AppsListRequest;
import com.jay.moappstest.model.response.AppsList;
import com.jay.moappstest.model.response.AppsListResponse;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        AppsListAdapter.OnItemClickListener {

    @Inject
    Retrofit retrofit;

    private AppsListAdapter appsListAdapter;
    private SwipeRefreshLayout refreshLayout;
    private ProgressDialog progressDialog;

    private ArrayList<String> appImages = new ArrayList<>();
    private ArrayList<String> appNames = new ArrayList<>();
    private ArrayList<Date> isPaidList = new ArrayList<>();
    private ArrayList<Boolean> isCompleteList = new ArrayList<>();
    private ArrayList<String> appUrlList = new ArrayList<>();

    private String userToken;
    private String TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();

        setUpSwipeRefreshLayout();

        setUpProgressDialog();

        userToken = getSharedPreferences("Settings", MODE_PRIVATE)
                .getString("userToken", "");

        getSharedPreferences("Settings", MODE_PRIVATE).edit()
                .putBoolean("signIn", true).apply();

        getAppsList(userToken);
    }


    private void setUpProgressDialog() {

        progressDialog = new ProgressDialog(AppsListActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.please_wait));
        progressDialog.show();
    }

    private void setUpRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppsListActivity.this));
        appsListAdapter = new AppsListAdapter(AppsListActivity.this, appImages, appNames,
                isPaidList, isCompleteList, this);
        recyclerView.setAdapter(appsListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.log_out:

                getSharedPreferences("Settings", MODE_PRIVATE).edit()
                        .putBoolean("signIn", false).apply();

                startActivity(new Intent(this, SignInActivity.class));
                finish();
                break;
        }
        return true;
    }


    private void setUpSwipeRefreshLayout() {

        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }


    @Override
    public void onRefresh() {

        appImages.clear();
        appNames.clear();
        isPaidList.clear();
        isCompleteList.clear();

        getAppsList(userToken);
    }


    private void getAppsList(String userToken) {

        AppsListRequest userApps = new AppsListRequest();
        userApps.setOsType(0);
        userApps.setSkip(0);
        userApps.setTake(1000);
        userApps.setUserToken(userToken);

        Log.d(TAG, "getAppsList: " + userToken);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<AppsListResponse> call = apiService.getUserApps(userApps);
        call.enqueue(new Callback<AppsListResponse>() {
            @Override
            public void onResponse(Call<AppsListResponse> call, Response<AppsListResponse> response) {

                Log.d(TAG, "onResponse: " + response.body());

                for (AppsList appsList : response.body().getData()) {

                    appImages.add(appsList.getApplicationIcoUrl());
                    appNames.add(appsList.getApplicationName());
                    isPaidList.add(appsList.getEndOfPayment());
                    isCompleteList.add(appsList.isApplicationStatus());
                    appUrlList.add(appsList.getApplicationUrl());
                }

                appsListAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<AppsListResponse> call, Throwable t) {

                Toast.makeText(AppsListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onItemClick(int position) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrlList.get(position)));
        startActivity(browserIntent);
    }
}
