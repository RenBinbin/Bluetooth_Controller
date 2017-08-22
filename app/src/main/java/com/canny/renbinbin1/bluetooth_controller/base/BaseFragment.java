package com.canny.renbinbin1.bluetooth_controller.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canny.renbinbin1.bluetooth_controller.R;


public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (getLayoutId()==0){
            return inflater.inflate(R.layout.fragment_base,container,false);
        }else{
            return inflater.inflate(getLayoutId(),container,false);

        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        Log.e("BaseFragment","initView执行了");
    }


    protected void initData(){

    }

    /**
     * 返回布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView(View view);

    /**
     * 获取数据（网络）
     */
    protected abstract void getData();


}
