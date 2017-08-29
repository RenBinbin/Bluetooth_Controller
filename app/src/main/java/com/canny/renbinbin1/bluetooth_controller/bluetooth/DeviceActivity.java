package com.canny.renbinbin1.bluetooth_controller.bluetooth;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseActivity;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.canny.renbinbin1.bluetooth_controller.bluetooth.DeviceListActivity.EXTRA_DEVICE_ADDRESS;

public class DeviceActivity extends BaseActivity {

    @BindView(R.id.title_paried_device)
    TextView titlePariedDevice;
    @BindView(R.id.paired_device)
    ListView pairedDevice;
    @BindView(R.id.title_new_device)
    TextView titleNewDevice;
    @BindView(R.id.new_device)
    ListView newDevice;
    @BindView(R.id.bt_scan)
    Button btScan;
    @BindView(R.id.activity_device)
    LinearLayout activityDevice;

    private BluetoothAdapter mBtAdapter;
    private static final int REQUEST_ENABLE_BT=2;
    private BluetoothChatService mChatService;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    private String mConnectedDeviceName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_device;
    }

    @Override
    public void initData() {
        ButterKnife.bind(this);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        setResult(Activity.RESULT_CANCELED);
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDiscovery();//搜索设备
                v.setVisibility(View.GONE);
            }
        });
        //初始化数组适配器。一个用于已经成对的设备，一个用于新发现的设备
        ArrayAdapter<String> pairedDevicesArrayAdapter =
                new ArrayAdapter<>(this, R.layout.item_device_name);
        mNewDevicesArrayAdapter = new ArrayAdapter<>(this, R.layout.item_device_name);

        //查找并设置成对设备的ListView
        pairedDevice.setAdapter(pairedDevicesArrayAdapter);
        pairedDevice.setOnItemClickListener(mDeviceClickListener);

        //查找并设置新发现设备的ListView
        newDevice.setAdapter(mNewDevicesArrayAdapter);
        newDevice.setOnItemClickListener(mDeviceClickListener);

        //当设备被发现时，注册广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        //获取一组当前配对的设备
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        //如果有配对设备，将每个设备添加到ArrayAdapter中
        if (pairedDevices.size() > 0) {
            titlePariedDevice.setVisibility(View.VISIBLE);
            findViewById(R.id.title_paried_device).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                pairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            pairedDevicesArrayAdapter.add(noDevices);
        }

        mChatService=new BluetoothChatService(this,mHandler);
    }

    private void doDiscovery() {
        //显示扫描的结果
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);

        //打开新设备的子标题
        titleNewDevice.setVisibility(View.VISIBLE);

        // If we're already discovering, stop it
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();
    }

    /**
     * The on-click listener for all devices in the ListViews
     * 在列表视图中的所有设备的on - click侦听器
     */
    private AdapterView.OnItemClickListener mDeviceClickListener
            = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Cancel discovery because it's costly and we're about to connect
            mBtAdapter.cancelDiscovery();

            //  获取设备MAC地址，这是视图中最后的17个字符
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_DEVICE_ADDRESS, address);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1){
                        case BluetoothChatService.STATE_CONNECTED:
                            setStatus(getString(R.string.title_connected_to,mConnectedDeviceName));
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            setStatus(R.string.title_connecting);
                            break;
                        case BluetoothChatService.STATE_NONE:
                            setStatus(R.string.title_not_connected);
                            break;
                    }
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != this) {
                        Toast.makeText(DeviceActivity.this, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }


        }
    };

    private void setStatus(int title_connecting) {
        if (null == this) {
            return;
        }
        final ActionBar actionBar = getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(title_connecting);
    }

    private void setStatus(String subTitle) {
        if (null == this) {
            return;
        }
        final ActionBar actionBar = getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(subTitle);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(!mBtAdapter.isEnabled()){
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,REQUEST_ENABLE_BT);
        }
    }

    /**
     * 当发现完成时，监听器监听已发现的设备并更改标题
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 当发现找到了一个装置
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 如果它已经配对了，跳过它，因为它已经被列出了
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
                // 当发现完成后，更改活动标题
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(R.string.none_found).toString();
                    mNewDevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };

    @Override
    protected void getData() {

    }
}
