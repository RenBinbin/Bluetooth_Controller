package com.canny.renbinbin1.bluetooth_controller.module.test;

import android.view.View;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;

//系统测试
public class TestFagment extends BaseFragment {

    public static TestFagment newInstance() {
        TestFagment fragment = new TestFagment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }
}
