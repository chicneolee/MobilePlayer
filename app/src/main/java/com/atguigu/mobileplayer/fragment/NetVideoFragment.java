package com.atguigu.mobileplayer.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.mobileplayer.base.BaseFragment;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/19 11:12
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：网络视频Fragment
 */
public class NetVideoFragment extends BaseFragment {


    private static final String TAG = NetVideoFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"网络视频UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"网络视频数据初始化了");
        textView.setText("网络视频内容");
    }
}
