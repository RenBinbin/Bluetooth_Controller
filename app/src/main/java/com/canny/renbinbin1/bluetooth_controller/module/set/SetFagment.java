package com.canny.renbinbin1.bluetooth_controller.module.set;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.base.BaseFragment;
import com.canny.renbinbin1.bluetooth_controller.util.CalendarUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//设置时间
public class SetFagment extends BaseFragment {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ll_choose_date)
    LinearLayout llChooseDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_choose_time)
    LinearLayout llChooseTime;

    private String dateWeek;
    private Calendar calendar;

    public static SetFagment newInstance() {
        SetFagment fragment = new SetFagment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        initCalendar();//初始化日历
        initTimeData();//初始化时间
    }

    private void initTimeData() {
        String str=CalendarUtils.initTime();
        tvTime.setText(str);
    }

    //日历的初始化
    private void initCalendar() {
        String str = CalendarUtils.initCalendar();
        dateWeek = CalendarUtils.getWeek(str);
        tvDate.setText(str);
    }

    //获取日历
    class TrainDatePicker extends DatePickerDialog {
        public TrainDatePicker(Context context, OnDateSetListener listener, int year, int month,
                               int dayOfMonth) {
            super(context, listener, year, month, dayOfMonth);
        }
    }

    //获取日期
    private DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog
            .OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            int dayMonth = month + 1;
            String str = year + "-" + dayMonth + "-" + dayOfMonth;
            dateWeek = CalendarUtils.getWeek(str);
            tvDate.setText(year + "-" + dayMonth + "-" + dayOfMonth);
        }
    };

    //获取时间
    class TrainTimePicker extends TimePickerDialog {
        public TrainTimePicker(Context context, OnTimeSetListener listener, int hourOfDay,
                               int minute, boolean is24HourView) {
            super(context, listener, hourOfDay, minute, is24HourView);
        }
    }

    private TimePickerDialog.OnTimeSetListener timePickerDialog=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String str=hourOfDay+":"+minute;
            dateWeek = CalendarUtils.getWeek(str);
            tvTime.setText(hourOfDay+":"+minute);
        }
    };

    @OnClick({R.id.ll_choose_date,R.id.ll_choose_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_choose_date://获取日历
                calendar = Calendar.getInstance();
                TrainDatePicker pickerDialog = new TrainDatePicker(getActivity(), this
                        .datePickerDialog
                        , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                        (Calendar.DAY_OF_MONTH));
                //设置时间限制
//                pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
//
//                pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() +
//                        CalendarUtils.oneDay * 20);
                pickerDialog.show();
                break;

            case R.id.ll_choose_time:
                calendar = Calendar.getInstance();
                TrainTimePicker timeDialog=new TrainTimePicker(getActivity(),this.timePickerDialog,
                        calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),true);
                timeDialog.show();
                break;
        }
    }

    @Override
    protected void getData() {

    }
}
