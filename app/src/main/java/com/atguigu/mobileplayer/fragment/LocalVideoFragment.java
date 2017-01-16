package com.atguigu.mobileplayer.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.activity.SystemVideoPlayer;
import com.atguigu.mobileplayer.adapter.LocalVideoAdapter;
import com.atguigu.mobileplayer.base.BaseFragment;
import com.atguigu.mobileplayer.bean.MediaItem;
import com.atguigu.mobileplayer.utils.Utils;

import java.util.ArrayList;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/19 11:12
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：本地视频Fragment
 */
public class LocalVideoFragment extends BaseFragment {


    private static final String TAG = LocalVideoFragment.class.getSimpleName();

    private ListView lv_local_video;
    private TextView tv_local_no_video;
    private LocalVideoAdapter adapter;
    private Utils utils;
    /**
     * 数据集合
     */
    private ArrayList<MediaItem> mediaItems;

    @Override
    public View initView() {
        Log.e(TAG, "本地视频UI被初始化了");
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_local_video, null);
        lv_local_video = (ListView) view.findViewById(R.id.lv_local_video);
        tv_local_no_video = (TextView) view.findViewById(R.id.tv_local_no_video);
        //设置item的点击事件
        lv_local_video.setOnItemClickListener(new MyOnItemClickListener());
        return view;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //1.得到对应postion的数据
            MediaItem mediaItem = mediaItems.get(position);
            //2.传入播放器：先用系统自带的播放器播放;自己的播放器
            //调用系统的所有的播放器
//            Intent intent = new Intent();
//            intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
//            startActivity(intent);

            Intent intent = new Intent(mContext, SystemVideoPlayer.class);
            //传递url-数据
            intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
            startActivity(intent);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (mediaItems != null && mediaItems.size() > 0) {
                //有数据
                //隐藏文本
                tv_local_no_video.setVisibility(View.GONE);
                //设置适配器
                adapter = new LocalVideoAdapter(mContext, mediaItems);
                lv_local_video.setAdapter(adapter);

            } else {
                //没有数据
                //显示文本
                tv_local_no_video.setVisibility(View.VISIBLE);
            }
        }
    };


    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "本地视频数据初始化了");
        getDataFromLocal();
    }

    private void getDataFromLocal() {

        //在子线程加载数据
        new Thread() {
            @Override
            public void run() {
                super.run();
                mediaItems = new ArrayList<MediaItem>();//创建集合
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objects = {
                        MediaStore.Video.Media.DISPLAY_NAME,//在sdcard显示的文件名称
                        MediaStore.Video.Media.DURATION,//视频持续播放的时长，毫秒
                        MediaStore.Video.Media.SIZE,//视频的大小byte
                        MediaStore.Video.Media.DATA,//视频的播放路径
                        MediaStore.Video.Media.ARTIST//艺术家
                };
                Cursor cursor = mContext.getContentResolver().query(uri, objects, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {


                        String name = cursor.getString(0);
                        long duration = cursor.getLong(1);
                        long size = cursor.getLong(2);
                        String data = cursor.getString(3);
                        String artist = cursor.getString(4);
                        MediaItem mediaItem = new MediaItem(name, duration, size, data, artist);

                        //添加到集合中
                        mediaItems.add(mediaItem);

                    }

                }
                handler.sendEmptyMessage(0);
                //发消息-加载数据完成

            }
        }.start();

    }
}
