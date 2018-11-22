package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.MovieByCinemaIdBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/9
 */
public class MyMovieByCinemaIdCoverAdapter extends RecyclerView.Adapter<MyMovieByCinemaIdCoverAdapter.CdViewHolder> {
    private Context context;
    private  List<MovieByCinemaIdBean.ResultBean>  list;

    public MyMovieByCinemaIdCoverAdapter(Context context, List<MovieByCinemaIdBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    class CdViewHolder extends RecyclerView.ViewHolder
    {


        private final ImageView simp_tu_view;

        public CdViewHolder(View itemView) {
            super(itemView);
            simp_tu_view = (ImageView)itemView.findViewById(R.id.simp_tu_view);
        }
    }

    private RecyclerItemListener listener;

    @NonNull
    @Override
    public MyMovieByCinemaIdCoverAdapter.CdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cover_layout, null);
        CdViewHolder cdViewHolder = new CdViewHolder(inflate);
        return cdViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMovieByCinemaIdCoverAdapter.CdViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position % list.size()).getImageUrl())
                .into(holder.simp_tu_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerItemListener
    {
        void onClick(int position);
    }

    public void setListener(RecyclerItemListener listener)
    {
        this.listener = listener;
    }
}
