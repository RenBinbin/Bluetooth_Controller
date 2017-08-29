package com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.BlueToothBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

//运行状态
public class MotionFragment extends BaseFragment {

    @BindView(R.id.model_motion)
    TextView modelMotion;
    @BindView(R.id.condition_motion)
    TextView conditionMotion;
    @BindView(R.id.floor_motion)
    TextView floorMotion;
    @BindView(R.id.speed_motion)
    TextView speedMotion;
    @BindView(R.id.msg_motion)
    TextView msgMotion;

    public static MotionFragment newInstance() {
        MotionFragment fragment = new MotionFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_motion;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void onEventMainThread(BlueToothBean motionBean) {
        String msg=motionBean.getMessage();
        modelMotion.setText(msg);
        Log.e("MotionFragment",msg);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
