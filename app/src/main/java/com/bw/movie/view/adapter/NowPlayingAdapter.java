package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import com.bw.movie.model.bean.NowPlayingMovieBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/7
 */
public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>{
    private Context context;
    private List<NowPlayingMovieBean.ResultBean> list;

    public NowPlayingAdapter(Context context, List<NowPlayingMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NowPlayingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_nowplayingmovie, parent, false);
        NowPlayingHolder nowPlayingHolder = new NowPlayingHolder(view);
        return nowPlayingHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingHolder holder, int position) {
        Uri parse = Uri.parse(list.get(position).getImageUrl());
        holder.simp_nowplayingmovie.setImageURI(parse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NowPlayingHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simp_nowplayingmovie;

        public NowPlayingHolder(View itemView) {
            super(itemView);
            simp_nowplayingmovie = itemView.findViewById(R.id.adapter_simp_nowplayingmovie);
        }
    }
}
