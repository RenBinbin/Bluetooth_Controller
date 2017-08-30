package com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment;

import android.view.View;
import android.widget.TextView;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.BlueToothBean;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.DatabaseDao;

import java.util.ArrayList;

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

    private DatabaseDao databaseDao;
    private ArrayList<BlueToothBean> mBlueToothBean = new ArrayList<>();

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
        databaseDao = new DatabaseDao(getActivity());

        ArrayList<BlueToothBean> blueList=databaseDao.query();
        for (BlueToothBean bt:blueList){
            modelMotion.setText(bt+"");
            modelMotion.setTextSize(18);
        }
        blueList.clear();

    }

    @Override
    protected void getData() {

    }

//    @Subscribe
//    public void onEventMainThread(EventUtils event) {
//        ArrayList<BlueToothBean> list = databaseDao.query();
//        if (list != null){
//            beanArrayList.clear();
//            for (BlueToothBean noteBookBean : list){
//                beanArrayList.add(noteBookBean);
//            }
//        }
//    }

}
