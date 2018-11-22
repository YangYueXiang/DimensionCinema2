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


import com.bw.movie.model.bean.NowPlayingMovieBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/9
 */
public class NowPlayingListAdapter extends RecyclerView.Adapter<NowPlayingListAdapter.NowHolder> {
    private Context context;
    private List<NowPlayingMovieBean.ResultBean> list;

    public NowPlayingListAdapter(Context context, List<NowPlayingMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_nowplaying, parent, false);
        NowHolder nowHolder = new NowHolder(view);
        return nowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NowHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.list_item_simp.setImageURI(uri);
        holder.list_item_name.setText(list.get(position).getName());
        holder.list_item_details.setText(list.get(position).getSummary());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NowHolder extends RecyclerView.ViewHolder{


        private final SimpleDraweeView list_item_simp;
        private final TextView list_item_name;
        private final TextView list_item_details;

        public NowHolder(View itemView) {
            super(itemView);
            list_item_simp = itemView.findViewById(R.id.list_item_playsimp);
            list_item_name = itemView.findViewById(R.id.list_item_playname);
            list_item_details = itemView.findViewById(R.id.list_item_playdetils);

        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
