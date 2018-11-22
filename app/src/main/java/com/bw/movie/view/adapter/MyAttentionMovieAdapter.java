package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import com.bw.movie.model.bean.MyAttentionBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/21
 */
public class MyAttentionMovieAdapter extends RecyclerView.Adapter<MyAttentionMovieAdapter.AttentionHolder> {
    private Context context;
    private  List<MyAttentionBean.ResultBean> list;

    public MyAttentionMovieAdapter(Context context, List<MyAttentionBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AttentionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_movie_item, parent, false);
        AttentionHolder attentionHolder = new AttentionHolder(view);
        return attentionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AttentionHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.icon.setImageURI(uri);
        holder.name.setText(list.get(position).getName());
        holder.jianjie.setText(list.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AttentionHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView icon;
        private final TextView name;
        private final TextView jianjie;

        public AttentionHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.my_movie_item_icon);
            name = itemView.findViewById(R.id.my_movie_item_name);
            jianjie = itemView.findViewById(R.id.my_movie_item_jianjie);
        }
    }
}
