package com.canny.renbinbin1.bluetooth_controller.module.monitor.bean;

import android.support.v4.app.Fragment;

/**
 * Created by 1006177 on 2017/8/17.
 */

public class MonitorTabBean {
    private String title;
    private Fragment fragment;

    public MonitorTabBean(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
