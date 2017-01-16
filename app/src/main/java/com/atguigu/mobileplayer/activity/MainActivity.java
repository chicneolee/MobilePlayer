package com.atguigu.mobileplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.base.BaseFragment;
import com.atguigu.mobileplayer.fragment.LocalAudioFragment;
import com.atguigu.mobileplayer.fragment.LocalVideoFragment;
import com.atguigu.mobileplayer.fragment.NetAudioFragment;
import com.atguigu.mobileplayer.fragment.NetVideoFragment;
import com.atguigu.mobileplayer.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup rg_main;

    /**
     * 装多个Fragment
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * 下标位置
     */
    private int position;

    /**
     * 缓存的Fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        initFragemnt();
        //设置RadioGroup状态的监听
        initListener();


    }

    private void initListener() {

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_local_video:
                        position = 0;
                        break;
                    case R.id.rb_local_audio:
                        position = 1;
                        break;
                    case R.id.rb_net_audio:
                        position = 2;
                        break;
                    case R.id.rb_net_video:
                        position = 3;
                        break;
                    case R.id.rb_recyclerview:
                        position = 4;
                        break;
                }
                //当前要显示的Fragemnt
                Fragment currentFragemnt = getFragment(position);
                //切换
//                swichFragment(tempFragment, currentFragemnt);
                switchFragment(currentFragemnt);


            }
        });

        //默认选中本地视频
        rg_main.check(R.id.rb_local_video);
    }

    private void switchFragment(Fragment currentFragemnt) {
        if(tempFragment != currentFragemnt){

            FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
            //没有添加
            if (!currentFragemnt.isAdded()) {
                //把上一个隐藏
                if (tempFragment != null) {
                    tf.hide(tempFragment);
                }
                //添加
                tf.add(R.id.frameLayout, currentFragemnt).commit();
                //事务提交
//                tf.commit();
            } else {
                //把上一个隐藏
                if (tempFragment != null) {
                    tf.hide(tempFragment);
                }
                //显示
                tf.show(currentFragemnt).commit();
                //事务提交
            }
            //在下面
            tempFragment = currentFragemnt;

        }
    }

    /**
     * 根据位置获取对应位置的Fragment
     *
     * @param position
     * @return
     */
    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    /**
     * 初始化Fragment
     */
    private void initFragemnt() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoFragment());//本地视频
        fragments.add(new LocalAudioFragment());//本地音频
        fragments.add(new NetAudioFragment());//网络音频
        fragments.add(new NetVideoFragment());//网络视频
        fragments.add(new RecyclerViewFragment());//RecyclerView
        //默认显示本地视频
//        swichFragment(position);
        defultFragemtn(fragments.get(position));

    }

    //设置默认的Fragemnt
    private void defultFragemtn(Fragment to) {
        tempFragment = to;
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, to).commit();
    }

//    /**
//     * 切换Fragemnt
//     *
//     * @param preFragment
//     * @param nextFragment
//     */
//    private void swichFragment(Fragment preFragment, Fragment nextFragment) {
//        //不同的Fragment
//        if (tempFragment != nextFragment) {
//            //变为缓存的
//            tempFragment = nextFragment;
//            FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
//            //没有添加
//            if (!nextFragment.isAdded()) {
//                //把上一个隐藏
//                if (preFragment != null) {
//                    tf.hide(preFragment);
//                }
//                //添加
//                tf.add(R.id.frameLayout, nextFragment).commit();
//                //事务提交
////                tf.commit();
//            } else {
//                //把上一个隐藏
//                if (preFragment != null) {
//                    tf.hide(preFragment);
//                }
//                //显示
//                tf.show(nextFragment).commit();
//                //事务提交
//            }
//
//        }
//
//
//    }

//    private void swichFragment(int position) {
//        //1.得到FragmengManger
//        FragmentManager manager = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction ft = manager.beginTransaction();
//        //3.替换 - 先是移除，在添加
//        ft.replace(R.id.frameLayout,fragments.get(position));
//        //4.提交
//        ft.commit();
//    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}
