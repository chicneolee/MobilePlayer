package com.atguigu.mobileplayer.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.activity.ShowImageAndGifActivity;
import com.atguigu.mobileplayer.base.BaseFragment;
import com.atguigu.mobileplayer.bean.NetAudioBean;

import java.util.List;

/**
 * 作者：NeoLee on 2016/11/19 11:12
 * 微信：chic_nel
 * QQ号：1761440258
 * 作用：网络音频Fragment
 */
public class NetAudioFragment extends BaseFragment {


    private static final String TAG = NetAudioFragment.class.getSimpleName();
    private ListView listview;
    private ProgressBar progressBar;
    private  TextView tv_nomedia;
    private List<NetAudioBean.ListBean> datas;

    @Override
    public View initView() {
        Log.e(TAG,"网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_net_audio, null);
        listview= (ListView) view.findViewById(R.id.listview);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);
        tv_nomedia= (TextView) view.findViewById(R.id.tv_nomedia);

        //设置点击事件
        //设置点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                NetAudioBean.ListBean listEntity = datas.get(position);
                if(listEntity !=null ){
                    //3.传递视频列表
                    Intent intent = new Intent(mContext,ShowImageAndGifActivity.class);
                    if(listEntity.getType().equals("gif")){
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url",url);
                        mContext.startActivity(intent);
                    }else if(listEntity.getType().equals("image")){
                        String url = listEntity.getImage().getBig().get(0);
                        intent.putExtra("url",url);
                        mContext.startActivity(intent);
                    }
                }


            }
        });

        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"网络音频数据初始化了");
    }
}
