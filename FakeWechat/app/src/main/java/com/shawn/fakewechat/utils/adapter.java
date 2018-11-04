package com.shawn.fakewechat.utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shawn.fakewechat.R;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {//适配器
    private List<xx> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
       // ImageView zuoimg, youimg;
        TextView zuotext, youtext;
        ViewGroup zuolin, youlin;

        public ViewHolder(View itemView) {
            super(itemView);
            zuolin = itemView.findViewById(R.id.zuo);
            //zuoimg = itemView.findViewById(R.id.zuoimg);
            zuotext = itemView.findViewById(R.id.zuotext);
            youlin = itemView.findViewById(R.id.you);
           // youimg = itemView.findViewById(R.id.youimg);
            youtext = itemView.findViewById(R.id.youtext);
        }
    }

    public adapter(List l) {
        list = l;
    }

    @Override
    public adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx, parent, false);
        ViewHolder h = new ViewHolder(v);
        Log.d("ServerActivity", "onCreate");
        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//滑动RecyclerView出发的事件
        xx x = list.get(position);
        if (x.zuo) {//判断该信息该信息是显示在左边还是右边，如果要在左边显示则把右边的部分隐藏
            holder.zuolin.setVisibility(View.VISIBLE);
            holder.youlin.setVisibility(View.GONE);//把右边的隐藏
           // holder.zuoimg.setImageResource(x.img);
            holder.zuotext.setText(x.text);
        }else{
            holder.youlin.setVisibility(View.VISIBLE);
            holder.zuolin.setVisibility(View.GONE);//把左边的隐藏
            //holder.youimg.setImageResource(x.img);
            holder.youtext.setText(x.text);
        }
        Log.d("ceshi","xx"+x.zuo);
        Log.d("ServerActivity", "onBind");
    }

    @Override
    public int getItemCount() {//这里要重写一下 不然不会显示任何信息
        Log.d("asdasd", "" + list.size());
        return list.size();
    }
}

class xx {//信息类
    public String text;//信息内容
    //public int img;//头像的图片id
    public boolean zuo = true;//控制信息显示在左边还有右边，默认左边

    public xx(String s) {
        text = s;
       // img = i;
    }

    public xx(String s,  boolean b) {
        text = s;
       // img = i;
        zuo = b;
    }
}