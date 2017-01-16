package com.atguigu.mobileplayer.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * 作者：Neolee on 2017-01-16 15:36 *
 * 微信：chic_nel
 * QQ  ：1761440258
 */

public class RecyclerViewFragment extends BaseFragment {

    private ProgressBar progressBar;
    private TextView tv_nomedia;
    @Override
    public View initView() {
        Log.e(TAG, "网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_recyclerview, null);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        tv_nomedia= (TextView) view.findViewById(R.id.tv_nomedia);
        return view;
    }
}
