package com.canny.renbinbin1.bluetooth_controller.module.parameters.debug.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canny.renbinbin1.bluetooth_controller.R;
import com.canny.renbinbin1.bluetooth_controller.module.parameters.debug.bean.DebugBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by renbinbin1 on 2017/8/21.
 */

public class DebugDetailAtapter extends RecyclerView.Adapter<DebugDetailAtapter.BaseViewHolder> {

    private List<DebugBean> mDebugBean = new ArrayList<>();
    private OnItemClickLitener mOnItemClickLitener;

    public DebugDetailAtapter(List<DebugBean> mDebugBean) {
        this.mDebugBean = mDebugBean;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debug, parent, false);
        DebugItemViewHolder debugItemViewHolder=new DebugItemViewHolder(view);
        return debugItemViewHolder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        DebugItemViewHolder debugItemViewHolder= (DebugItemViewHolder) holder;
        DebugBean debugBean=mDebugBean.get(position);
        debugItemViewHolder.tvDetailTitle.setText(debugBean.getTitle());
        debugItemViewHolder.tvDetailName.setText(debugBean.getName());
        debugItemViewHolder.tvNum.setText(debugBean.getNum());
        if(mOnItemClickLitener!=null){
            ((DebugItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(((DebugItemViewHolder) holder).itemView,pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDebugBean.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    class DebugItemViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_detail_title)
        TextView tvDetailTitle;
        @BindView(R.id.tv_detail_name)
        TextView tvDetailName;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.ll_detail_debug)
        LinearLayout llDetailDebug;
        public DebugItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //点击监听接口
    public interface OnItemClickLitener{
        void onItemClick(View view,int position);
    }

    //设置点击事件
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener=mOnItemClickLitener;
    }
}
