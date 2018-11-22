package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.CinemaByMovieActivity;
import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaByMovieIdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/22
 */
public class CinemaByMovieIdAdapter extends RecyclerView.Adapter<CinemaByMovieIdAdapter.CinemaByMovieHolder> {
    private Context context;
    private  List<CinemaByMovieIdBean.ResultBean> list;

    public CinemaByMovieIdAdapter(Context context, List<CinemaByMovieIdBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CinemaByMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommendcinema_layout, parent, false);
        CinemaByMovieHolder cinemaByMovieHolder = new CinemaByMovieHolder(view);
        return  cinemaByMovieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaByMovieHolder holder, final int position) {
        Uri uri = Uri.parse(list.get(position).getLogo());
        holder.CinemaIc.setImageURI(uri);
        holder.CinemaName.setText(list.get(position).getName());
        holder.CinemaAddress.setText(list.get(position).getAddress());
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

    public class CinemaByMovieHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView CinemaIc;
        private final TextView CinemaName;
        private final TextView CinemaAddress;
        private final ImageView CinemaSelect;
        private final TextView CinemaJuli;
        public CinemaByMovieHolder(View itemView) {
            super(itemView);
            CinemaIc = itemView.findViewById(R.id.Cinema_ic);
            CinemaName = itemView.findViewById(R.id.Cinema_name);
            CinemaAddress = itemView.findViewById(R.id.Cinema_adress);
            CinemaSelect = itemView.findViewById(R.id.Cinema_select);
            CinemaJuli = itemView.findViewById(R.id.Cinema_juli);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    private  OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
