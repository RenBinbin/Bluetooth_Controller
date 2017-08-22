package com.canny.renbinbin1.bluetooth_controller.module.monitor;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.canny.renbinbin1.bluetooth_controller.util.CommonUtils;
import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.adapter.MonitorFragmentPagerAdapter;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.bean.MonitorTabBean;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment.DriveFragment;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment.HallFragment;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment.InputFragment;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment.MotionFragment;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.fragment.TerminalFragment;

import java.util.ArrayList;
import java.util.List;

import shanyao.tabpagerindictor.TabPageIndicator;
//运行状态界面
public class MonitorFragment extends BaseFragment {
    private List<MonitorTabBean> monitorTabBeen = new ArrayList<>();
    private TabPageIndicator indicator;

    public MonitorFragment() {
    }
    public static MonitorFragment newInstance() {
        MonitorFragment fragment = new MonitorFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_monitor;
    }

    @Override
    protected void initView(View view) {
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator_Monitor);
        ViewPager mViewPager= (ViewPager) view.findViewById(R.id.monitor_viewpager);
        monitorTabBeen.add(new MonitorTabBean("运行状态", MotionFragment.newInstance()));
        monitorTabBeen.add(new MonitorTabBean("驱动状态", DriveFragment.newInstance()));
        monitorTabBeen.add(new MonitorTabBean("呼梯状态", HallFragment.newInstance()));
        monitorTabBeen.add(new MonitorTabBean("输入输出", InputFragment.newInstance()));
        monitorTabBeen.add(new MonitorTabBean("终端开关", TerminalFragment.newInstance()));
        MonitorFragmentPagerAdapter adapter = new MonitorFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(monitorTabBeen);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        indicator.setViewPager(mViewPager);
        setTabPagerIndicator();
    }

    @Override
    protected void getData() {

    }

    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode. MODE_WEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#969696"));// 设置分割线的颜色
        indicator.setDividerPadding(CommonUtils.px2dip(getActivity(),3));//设置
        indicator.setIndicatorColor(Color.parseColor("#1485E5"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#137DD5"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#9A9A9A"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(CommonUtils.sp2px(getActivity(),16));// 设置字体大小
    }
}
