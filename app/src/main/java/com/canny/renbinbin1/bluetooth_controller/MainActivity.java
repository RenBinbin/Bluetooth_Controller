package com.canny.renbinbin1.bluetooth_controller;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;

import com.canny.renbinbin1.bluetooth_controller.adapter.MainViewPagerAdapter;
import com.canny.renbinbin1.bluetooth_controller.base.BaseActivity;
import com.canny.renbinbin1.bluetooth_controller.bean.TabBean;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.MonitorFragment;
import com.canny.renbinbin1.bluetooth_controller.module.parameters.ParametersFagment;
import com.canny.renbinbin1.bluetooth_controller.module.set.SetFagment;
import com.canny.renbinbin1.bluetooth_controller.module.record.RecordFragment;
import com.canny.renbinbin1.bluetooth_controller.module.test.TestFagment;
import com.canny.renbinbin1.bluetooth_controller.widge.MainBottomTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        ViewPager viewPager= (ViewPager) findViewById(R.id.vp_main);
        MainBottomTabLayout mainBottomTabLayout= (MainBottomTabLayout) findViewById(R.id.tablayout_main);
        //底部导航
        ArrayList<TabBean> tabMain = new ArrayList<>();
        tabMain.add(new TabBean("系统监视", 0, R.mipmap.bottom_joke, R.mipmap
                .bottom_joke_1));
        tabMain.add(new TabBean("故障记录", 0, R.mipmap.bottom_joke, R.mipmap
                .bottom_joke_1));

        tabMain.add(new TabBean("系统测试", 0, R.mipmap.bottom_joke, R.mipmap
                .bottom_joke_1));
        tabMain.add(new TabBean("时间设置", 0, R.mipmap.bottom_joke, R.mipmap.bottom_joke_1));
        tabMain.add(new TabBean("系统参数", 0, R.mipmap.bottom_joke, R.mipmap.bottom_joke_1));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MonitorFragment.newInstance());
        fragments.add(RecordFragment.newInstance());
        fragments.add(TestFagment.newInstance());
        fragments.add(SetFagment.newInstance());
        fragments.add(ParametersFagment.newInstance());

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        mainBottomTabLayout.setViewPager(viewPager, tabMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void getData() {

    }

}
