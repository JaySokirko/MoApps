package com.jay.moappstest.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jay.moappstest.AppListContract;
import com.jay.moappstest.R;
import com.jay.moappstest.adapter.AppsListAdapter;
import com.jay.moappstest.presenter.AppListPresenter;

import java.util.ArrayList;
import java.util.Date;

public class AppsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        AppListContract.View, AppsListAdapter.OnItemClickListener {

    private ProgressDialog progressDialog;

    private AppListPresenter presenter;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout refreshLayout;

    private ArrayList<String> appUrlList = new ArrayList<>();

    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppsListActivity.this));

        setUpSwipeRefreshLayout();

        setUpProgressDialog();

        presenter = new AppListPresenter(this);

        userToken = getSharedPreferences("Settings", MODE_PRIVATE)
                .getString("userToken", "");

        getSharedPreferences("Settings", MODE_PRIVATE).edit()
                .putBoolean("signIn", true).apply();


        presenter.loadData(userToken);
    }


    private void setUpProgressDialog() {

        progressDialog = new ProgressDialog(AppsListActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.please_wait));
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

        presenter.onSwipeRefreshLayout(userToken);
    }


    @Override
    public void showProgress() {

        progressDialog.show();
    }


    @Override
    public void hideProgress() {

        progressDialog.dismiss();
    }


    @Override
    public void hideRefreshingProgress() {

        refreshLayout.setRefreshing(false);
    }


    @Override
    public void setDataToAdapter(ArrayList<String> appNames, ArrayList<String> appImages,
                                 ArrayList<Date> datePaid, ArrayList<Boolean> isComplete) {

        AppsListAdapter appsListAdapter = new AppsListAdapter(AppsListActivity.this, appImages,
                appNames, datePaid, isComplete, this);

        recyclerView.setAdapter(appsListAdapter);
    }


    @Override
    public void setUrlToViews(ArrayList<String> urlList) {
        appUrlList.addAll(urlList);
    }


    @Override
    public void onFailureResponse(Throwable throwable) {

        Toast.makeText(AppsListActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemClick(int position) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrlList.get(position)));
        startActivity(browserIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
