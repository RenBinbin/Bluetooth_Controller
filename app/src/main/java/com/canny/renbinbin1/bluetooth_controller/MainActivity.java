package com.canny.renbinbin1.bluetooth_controller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.canny.renbinbin1.bluetooth_controller.adapter.MainViewPagerAdapter;
import com.canny.renbinbin1.bluetooth_controller.base.BaseActivity;
import com.canny.renbinbin1.bluetooth_controller.bean.TabBean;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.BluetoothChatService;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.Constants;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.DatabaseDao;
import com.canny.renbinbin1.bluetooth_controller.bluetooth.DeviceListActivity;
import com.canny.renbinbin1.bluetooth_controller.module.monitor.MonitorFragment;
import com.canny.renbinbin1.bluetooth_controller.module.parameters.ParametersFagment;
import com.canny.renbinbin1.bluetooth_controller.module.record.RecordFragment;
import com.canny.renbinbin1.bluetooth_controller.module.set.SetFagment;
import com.canny.renbinbin1.bluetooth_controller.module.test.TestFagment;
import com.canny.renbinbin1.bluetooth_controller.widge.MainBottomTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CONNECT_DEVICE=1;
    private static final int REQUEST_ENABLE_BT=2;
    private BluetoothChatService mChatService;
    private DatabaseDao databaseDao;
    private BluetoothAdapter mBluetoothAdapter;
    private String mConnectedDeviceName;

    @Override
    protected int getLayoutId(){
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

        databaseDao = new DatabaseDao(this);
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mChatService==null){
            setupChat();
        }
    }

    private void setupChat() {
        mChatService=new BluetoothChatService(this,mHandler);
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
//                case Constants.MESSAGE_STATE_CHANGE:
//                    switch (msg.arg1){
//                        case BluetoothChatService.STATE_CONNECTED:
//                            setStatus(getString(R.string.title_connected_to,mConnectedDeviceName));
//                            break;
//                        case BluetoothChatService.STATE_CONNECTING:
//                            setStatus(R.string.title_connecting);
//                            break;
//                        case BluetoothChatService.STATE_NONE:
//                            setStatus(R.string.title_not_connected);
//                            break;
//                    }
//                    break;
                case Constants.MESSAGE_READ:
                     byte[] readBuf= (byte[]) msg.obj;
                    String readMessage=byteToHexString(readBuf);
                    Log.e("Message01", readMessage);
                    databaseDao.insert(readMessage);
//                    if(l>0){
//                        Toast.makeText(MainActivity.this,"存储成功",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName=msg.getData().getString(Constants.DEVICE_NAME);
                    if(this!=null){
                        Toast.makeText(MainActivity.this,"Connected to"+mConnectedDeviceName,Toast.LENGTH_LONG).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (this!=null){
                        Toast.makeText(MainActivity.this,msg.getData().getString(Constants.TOAST),Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

//    private void setStatus(int title_connecting) {
//        if (null == this) {
//            return;
//        }
//        final ActionBar actionBar = getActionBar();
//        if (null == actionBar) {
//            return;
//        }
//        actionBar.setSubtitle(title_connecting);
//    }
//
//    //更新操作栏的状态
//    private void setStatus(CharSequence subTitle) {
//        if (null == this) {
//            return;
//        }
//        final ActionBar actionBar = getActionBar();
//        if (null == actionBar) {
//            return;
//        }
//        actionBar.setSubtitle(subTitle);
//    }

    private static String byteToHexString(byte[] bytes) {
        String result="";
        for (int i = 0; i <bytes.length ; i++) {
            String hexString=Integer.toHexString(bytes[i]&0xFF);
            if(hexString.length()==1){
                hexString='0'+hexString;
            }
            result+=hexString.toUpperCase();
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.blue_action:
                Intent intent=new Intent(this, DeviceListActivity.class);
                startActivityForResult(intent,REQUEST_CONNECT_DEVICE);
                break;
//            case R.id.blue_stop:
//                mChatService.stop();
//                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CONNECT_DEVICE:
                if(resultCode== Activity.RESULT_OK){
                    connectDevice(data,true);
                }
                break;
            case REQUEST_ENABLE_BT:
                if(resultCode==Activity.RESULT_OK){
                    setupChat();
                }
                break;
        }
    }

    //与其他设备建立连接
    private void connectDevice(Intent data, boolean secure) {
        String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        mChatService.connect(device, secure);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mChatService!=null){
            mChatService.stop();
        }
    }
}
