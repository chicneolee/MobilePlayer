package com.atguigu.mobileplayer.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mobileplayer.R;
import com.atguigu.mobileplayer.bean.MediaItem;
import com.atguigu.mobileplayer.utils.Utils;

import java.util.ArrayList;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/19 15:29
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：本地视频的适配器
 */
public class LocalVideoAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<MediaItem> mediaItems;
    private  Utils utils;

    public LocalVideoAdapter(Context context,ArrayList<MediaItem> mediaItems){
        utils = new Utils();
        this.mContext = context;
        this.mediaItems  = mediaItems;
    }

    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_local_video,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //绑定数据
        //根据位置得到对应的数据
        MediaItem mediaItem = mediaItems.get(position);
        viewHolder.tv_name.setText(mediaItem.getName());
        //把byte单位转换成 MB
        viewHolder.tv_size.setText(Formatter.formatFileSize(mContext,mediaItem.getSize()));
        //把毫秒转换成-时分秒
        viewHolder.tv_duration.setText(utils.stringForTime((int) mediaItem.getDuration()));

        return convertView;
    }


    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_size;
        TextView tv_duration;
    }
}
