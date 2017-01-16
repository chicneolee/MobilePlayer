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
 * 作用：本地音频Fragment
 */
public class LocalAudioFragment extends BaseFragment {


    private static final String TAG = LocalAudioFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"本地音频UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"本地音频数据初始化了");
        textView.setText("本地音频内容");
    }
}
