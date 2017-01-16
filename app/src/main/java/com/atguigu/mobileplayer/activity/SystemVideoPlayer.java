package com.atguigu.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.atguigu.mobileplayer.R;

public class SystemVideoPlayer extends Activity {

    private VideoView videoview;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        videoview = (VideoView) findViewById(R.id.videoview);

        //得到播放地址
        uri = getIntent().getData();//播放地址

        //设置相关监听
        setListener();

        if(uri != null){
            videoview.setVideoURI(uri);
        }
    }

    private void setListener() {

        //设置控制面板
        videoview.setMediaController(new MediaController(this));
        //1.准备好的监听，播放
        videoview.setOnPreparedListener(new MyOnPreparedListener());
        //2.播放出错的监听
        videoview.setOnErrorListener(new MyOnErrorListener());
        //3.播放完成监听
        videoview.setOnCompletionListener(new MyOnCompletionListener());
    }
    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener{

        /**
         * 当播放完成的时候回调
         * 播放下一个或者退出
         * @param mp
         */
        @Override
        public void onCompletion(MediaPlayer mp) {
            finish();
        }
    }

    class MyOnErrorListener implements MediaPlayer.OnErrorListener{

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(SystemVideoPlayer.this, "播放出错了", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {

        /**
         * 当底层准备好的时候回调
         * @param mp
         */
        @Override
        public void onPrepared(MediaPlayer mp) {
            //开始播放
//            videoview.start();
            mp.start();

        }
    }
}
