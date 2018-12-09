package com.jay.moappstest.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jay.moappstest.R;
import com.jay.moappstest.api.ApiClient;
import com.jay.moappstest.api.ApiService;
import com.jay.moappstest.model.request.UserTokenRequest;
import com.jay.moappstest.model.response.UserTokenResponse;
import com.jay.moappstest.utils.InternetCinnection;
import com.jay.moappstest.view.dialog.InformationalDialog;
import com.jay.moappstest.view.dialog.NoInternetConnectionDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ApiService apiService;

    private EditText emailET;
    private EditText passwordET;

    private ProgressDialog progressDialog;

    private final int DRAWABLE_RIGHT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_actiity);

        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);

        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.please_wait));

        onEditEmailListener();
        onEditPasswordListener();

        apiService = ApiClient.getClient().create(ApiService.class);
    }


    /**
     * @param view sign in button
     */
    public void onSignInClick(View view) {

        if (emailET.getText().toString().isEmpty()) {

            emailET.setBackground(getResources().getDrawable(R.drawable.rounded_view_red));

            Toast.makeText(SignInActivity.this,
                    getResources().getString(R.string.enter_email), Toast.LENGTH_LONG).show();

        } else if (passwordET.getText().toString().isEmpty()) {

            passwordET.setBackground(getResources().getDrawable(R.drawable.rounded_view_red));

            Toast.makeText(SignInActivity.this,
                    getResources().getString(R.string.enter_password), Toast.LENGTH_LONG).show();
        } else {

            String userNick = emailET.getText().toString();
            String password = passwordET.getText().toString();

            //Check the internet connection
            if (InternetCinnection.isOnline(SignInActivity.this)) {

                //if the internet enabled get current user token
                 progressDialog.show();
                getUserToken(userNick, password);
            } else {

                //Show the dialog to open the settings.
                progressDialog.dismiss();
                NoInternetConnectionDialog.buildDialog(SignInActivity.this);
            }
        }
    }


    /**
     * @param view informational button
     */
    public void onInformationClick(View view){

       InformationalDialog dialog = new InformationalDialog(SignInActivity.this);
       dialog.show();
    }


    private void getUserToken(String userNick, String password) {

        UserTokenRequest user = new UserTokenRequest();
        user.setUserNick(userNick);
        user.setPassword(password);

        Call<UserTokenResponse> call = apiService.getUser(user);
        call.enqueue(new Callback<UserTokenResponse>() {
            @Override
            public void onResponse(Call<UserTokenResponse> call, Response<UserTokenResponse> response) {

                String token = response.body().getData();

                if (token == null) {

                    Toast.makeText(SignInActivity.this,
                            getResources().getString(R.string.no_user), Toast.LENGTH_LONG).show();
                } else {

                    getSharedPreferences("Settings", MODE_PRIVATE).edit()
                            .putString("userToken",token).apply();

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));

                    finish();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserTokenResponse> call, Throwable t) {

                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }



    @SuppressLint("ClickableViewAccessibility")
    private void onEditEmailListener(){

        emailET.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                emailET.setBackground(getResources()
                        .getDrawable(R.drawable.rounded_view_semi_transparent));

                if (event.getRawX() >= (emailET.getRight() -
                        emailET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                    emailET.setText("");

                    return true;
                }
            }
            return false;
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    private void onEditPasswordListener() {

        passwordET.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                passwordET.setBackground(getResources()
                        .getDrawable(R.drawable.rounded_view_semi_transparent));

                if (event.getRawX() >= (passwordET.getRight() -
                        passwordET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                    passwordET.setText("");

                    return true;
                }
            }
            return false;
        });
    }
}
