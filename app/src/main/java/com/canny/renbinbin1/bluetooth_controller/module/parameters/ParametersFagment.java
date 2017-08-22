package com.canny.renbinbin1.bluetooth_controller.module.parameters;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;
import com.canny.renbinbin1.bluetooth_controller.module.parameters.debug.DebugActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//系统参数
public class ParametersFagment extends BaseFragment {

    @BindView(R.id.jiben_param)
    LinearLayout jibenParam;
    @BindView(R.id.luoji_param)
    LinearLayout luojiParam;
    @BindView(R.id.qudong_param)
    LinearLayout qudongParam;
    @BindView(R.id.tiaoshi_param)
    LinearLayout tiaoshiParam;

    public static ParametersFagment newInstance() {
        ParametersFagment fragment = new ParametersFagment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_parameters;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);

    }

    @Override
    protected void getData() {

    }

    @OnClick({R.id.tiaoshi_param})
    public void onClick(View view){
        Intent intent=new Intent(getActivity(),DebugActivity.class);
        startActivity(intent);
    }
}
