package com.jay.moappstest.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.jay.moappstest.R;

public class InformationalDialog extends Dialog implements android.view.View.OnClickListener {


    public InformationalDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaolog_information);

        Button buttonDismiss = findViewById(R.id.dismiss_button);
        buttonDismiss.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.dismiss_button:
                dismiss();
                break;
        }
    }
}
