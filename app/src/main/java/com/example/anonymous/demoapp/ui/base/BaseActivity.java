package com.example.anonymous.demoapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.anonymous.demoapp.api.ApiClient;
import com.example.anonymous.demoapp.api.ApiInterface;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public ApiInterface apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        initControl();
    }

    public abstract int getLayout();

    public abstract void initControl();
}
