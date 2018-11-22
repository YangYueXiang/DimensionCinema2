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

import com.bw.movie.model.bean.HotMovieBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/7
 */
public class HotMovieAdapter extends RecyclerView.Adapter<HotMovieAdapter.HotMovieHolder>{
    private Context context;
    private List<HotMovieBean.ResultBean> list;

    public HotMovieAdapter(Context context, List<HotMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HotMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_hotmovie, parent, false);
        HotMovieHolder hotMovieHolder = new HotMovieHolder(view);
        return hotMovieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotMovieHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getImageUrl());
        holder.adapter_simp_hotmovie.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotMovieHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView adapter_simp_hotmovie;


        public HotMovieHolder(View itemView) {
           super(itemView);
            adapter_simp_hotmovie = itemView.findViewById(R.id.adapter_simp_hotmovie);

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
