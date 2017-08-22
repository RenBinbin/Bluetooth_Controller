package com.canny.renbinbin1.bluetooth_controller.module.monitor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.canny.renbinbin1.bluetooth_controller.module.monitor.bean.MonitorTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1006177 on 2017/8/17.
 */

public class MonitorFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<MonitorTabBean> mFragments = new ArrayList<>();
    public MonitorFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position ).getFragment();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

    public void addFragment(List<MonitorTabBean> fragments){
        mFragments.addAll(fragments);
    }
}
