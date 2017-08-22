package com.canny.renbinbin1.bluetooth_controller;

import android.app.Application;

/**
 * Created by renbinbin1 on 2017/8/18.
 */

public class App extends Application {
    public static App application;
    public static App getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;

    }
}
