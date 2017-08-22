package com.canny.renbinbin1.bluetooth_controller.module.record;

import android.view.View;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;
//故障记录
public class RecordFragment extends BaseFragment {

    public RecordFragment() {
    }
    public static RecordFragment newInstance() {
        RecordFragment fragment = new RecordFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }
}
