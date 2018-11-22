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
import com.bw.movie.model.bean.HotMovieBean;

/**
 * Created by YangYueXiang
 * on 2018/11/9
 */
public class MyCoverAdapter extends RecyclerView.Adapter<MyCoverAdapter.CdViewHolder> {
    private Context context;
    private HotMovieBean list;

    public MyCoverAdapter(Context context, HotMovieBean list) {
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
    public MyCoverAdapter.CdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cover_layout, null);
        CdViewHolder cdViewHolder = new CdViewHolder(inflate);
        return cdViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCoverAdapter.CdViewHolder holder, int position) {
        Glide.with(context)
                .load(list.getResult().get(position % list.getResult().size()).getImageUrl())
                .into(holder.simp_tu_view);
    }

    @Override
    public int getItemCount() {
        return list.getResult().size();
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
