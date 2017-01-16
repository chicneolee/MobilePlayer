package com.atguigu.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.atguigu.mobileplayer.activity.MainActivity;

public class SplashActivity extends Activity {


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //两秒延迟进入主页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //主线程
                startMainActivity();
            }
        }, 2000);
    }

    //从源头控制
    private boolean isStartMain = false;

    private void startMainActivity() {
//        if(!isStartMain){
//            isStartMain = true;
            startActivity(new Intent(this,MainActivity.class));
            //关闭当前页面
            finish();
//        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //把所有的消息移除
        handler.removeCallbacksAndMessages(null);
    }
}
