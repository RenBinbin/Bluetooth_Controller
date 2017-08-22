package com.canny.renbinbin1.bluetooth_controller.module.parameters.debug;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseActivity;
import com.canny.renbinbin1.bluetooth_controller.module.parameters.debug.adapter.DebugDetailAtapter;
import com.canny.renbinbin1.bluetooth_controller.module.parameters.debug.bean.DebugBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//调试参数
public class DebugActivity extends BaseActivity {

    @BindView(R.id.rel_debug)
    RecyclerView relDebug;
    @BindView(R.id.activity_debug)
    RelativeLayout activityDebug;

    ArrayList<DebugBean> mDebugBean=new ArrayList<>();
    DebugDetailAtapter atapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_debug;
    }

    @Override
    public void initData() {
        ButterKnife.bind(this);
        atapter=new DebugDetailAtapter(mDebugBean);
        relDebug.setLayoutManager(new LinearLayoutManager(this));
        relDebug.setAdapter(atapter);

        atapter.setOnItemClickLitener(new DebugDetailAtapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DebugActivity.this,"点击了",Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    protected void getData() {

    }
}
