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

import com.bw.movie.model.bean.ComingMovieBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/7
 */
public class SooningAdapter extends RecyclerView.Adapter<SooningAdapter.SooningHolder>{
   private Context context;
   private List<ComingMovieBean.ResultBean> list;

    public SooningAdapter(Context context, List<ComingMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SooningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sooning, parent, false);
        SooningHolder sooningHolder = new SooningHolder(view);
        return sooningHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SooningHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.simp_sooning.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SooningHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp_sooning;

        public SooningHolder(View itemView) {
            super(itemView);
            simp_sooning = itemView.findViewById(R.id.adapter_simp_sooning);
        }
    }
}
