package com.jay.moappstest.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jay.moappstest.R;
import com.jay.moappstest.SignInContract;
import com.jay.moappstest.presenter.SignInPresenter;

public class SignInActivity extends AppCompatActivity implements SignInContract.View{

    private SignInPresenter presenter;

    private EditText emailEditText;
    private EditText passwordEditText;

    private Button loginButton;
    private Button infoButton;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.sign_in);
        infoButton = findViewById(R.id.information);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.please_wait));

        presenter = new SignInPresenter(this);

        onLoginBtnClickListener();

        onInfoBtnClickListener();
    }

    private void onLoginBtnClickListener() {

        loginButton.setOnClickListener(v -> {

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            presenter.onSignInClick(email, password);
        });
    }


    private void onInfoBtnClickListener() {

        infoButton.setOnClickListener(v -> {
            //todo show dialog
        });
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
    public void onEmailError() {
        //todo set background for email
    }

    @Override
    public void onPasswordError() {
        //todo set background for password
    }

    @Override
    public void onSuccessResponse(String token) {

        if (token == null){

            Toast.makeText(this, getResources().getString(R.string.no_user), Toast.LENGTH_LONG)
                    .show();
        } else {

            getSharedPreferences("Settings", MODE_PRIVATE).edit()
                    .putString("userToken",token).apply();

            startActivity(new Intent(SignInActivity.this, AppsListActivity.class));
        }
    }


    @Override
    public void onFailureResponse(Throwable throwable) {

        Toast.makeText(SignInActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
