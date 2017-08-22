package com.canny.renbinbin1.bluetooth_controller.widge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.bean.TabBean;


/**
 * 底部导航【图标+文字】
 */
public class BottomTab extends LinearLayout {
    private static final String TAG = BottomTab.class.toString();
    //文字
//    private String mTitleTxt;
    //图标
//    private int mIconRes;

    TextView tvTitle;
    ImageView ivIcon;

    TabBean mTabBean;

    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomTab(Context context, TabBean tabBean) {
        super(context);
        //设置当前布局是垂直的
        setOrientation(HORIZONTAL);
        mTabBean = tabBean;
//        this.mTitleTxt = title;
//        this.mIconRes = iconRes;
        init();
    }

    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //初始化控件，这里会创建两个控件【图标+文字】，并且用一个LinearLayout包裹起来
    private void init() {

        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams lllayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(lllayoutParams);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        ivIcon = new ImageView(getContext());

        //非标准的单位转换成标准的  px  dp
        //dp --> px
        //in --> px
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
                getContext().getResources().getDisplayMetrics());

        if (mTabBean.getTabType() == 0 || mTabBean.getTabType() == 1) {
            //图标的布局参数

            if(mTabBean.getTabType() == 1){

                int imageSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                        getContext().getResources().getDisplayMetrics());
                LayoutParams iconLayoutParams = new LayoutParams(imageSize, imageSize);
                ivIcon.setLayoutParams(iconLayoutParams);
                ivIcon.setScaleType(ImageView.ScaleType.CENTER);

//                ivIcon.setImageResource(mTabBean.getIconChooseResId());

                Selector selector = new Selector();
                Drawable normal = ContextCompat.getDrawable(getContext(),mTabBean.getIconNormalResId());
                Drawable choose = ContextCompat.getDrawable(getContext(),mTabBean.getIconChooseResId());
                ivIcon.setBackgroundDrawable(selector.newSelector(normal,choose));

            }else{

                LayoutParams iconLayoutParams = new LayoutParams(size, size);
                ivIcon.setLayoutParams(iconLayoutParams);
                ivIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ivIcon.setImageResource(mTabBean.getIconNormalResId());
            }
            //SVG
//            ivIcon.setImageResource();
//            ivIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.unselect));
            //添加到最外层LinearLayout
//            LayoutParams iconADDLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            iconADDLayoutParams.gravity = Gravity.CENTER;
            linearLayout.addView(ivIcon);
        }

        if (mTabBean.getTabType() == 0 || mTabBean.getTabType() == 2) {
            tvTitle = new TextView(getContext());
            tvTitle.setGravity(Gravity.CENTER);
            LayoutParams titlelayoutParams = new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvTitle.setLayoutParams(titlelayoutParams);
            tvTitle.setText(mTabBean.getTitle() == null ? "默认" : mTabBean.getTitle());
            tvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.main_gray));

            //添加到最外层LinearLayout
            linearLayout.addView(tvTitle);
        }
        //将最外层LinearLayout 添加到当前的View里面
        addView(linearLayout);
    }

    public class Selector{

        public StateListDrawable newSelector(Drawable normal,Drawable choose){
            StateListDrawable listDrawable = new StateListDrawable();
            listDrawable.addState(new int[]{android.R.attr.state_pressed,
                    android.R.attr.state_enabled},choose);
            listDrawable.addState(new int[]{android.R.attr.state_enabled},normal);
            listDrawable.addState(new int[]{},normal);
            return listDrawable;
        }
    }
    /**
     * 改变图片颜色，改变文字颜色
     *
     * @param selected true 选择  false 未选中
     */
    @Override
    public void setSelected(boolean selected) {
        if (selected) {
            if (null != tvTitle) {
                tvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.main_blue));
            }
            if (null != ivIcon) {
//                ivIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.main_green));
                ivIcon.setImageResource(mTabBean.getIconChooseResId());
            }
        } else {
            if (null != tvTitle) {
                tvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.main_gray));
            }
            //必须配合setImageResource 才有效果
            //一般改变图标的方式是直接替换掉选择的图片
            // imageView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.unselect));
            if (null != ivIcon) {
//                ivIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.unselect));
                ivIcon.setImageResource(mTabBean.getIconNormalResId());
            }
        }
    }
}
