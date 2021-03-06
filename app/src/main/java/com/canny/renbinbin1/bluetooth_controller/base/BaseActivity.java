package com.canny.renbinbin1.bluetooth_controller.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow();
        setContentView(getLayoutId());
        initData();
        getData();
    }

    protected abstract int getLayoutId();
    public abstract void initData();

    protected abstract void getData();
    protected  void setWindow(){

    }
}
