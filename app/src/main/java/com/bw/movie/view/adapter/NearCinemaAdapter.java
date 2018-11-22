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

import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import com.bw.movie.model.bean.AllCinemaBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public class NearCinemaAdapter extends RecyclerView.Adapter<NearCinemaAdapter.NearCinemaHolder>{
    private Context context;
    private List<AllCinemaBean.ResultBean> list;

    public NearCinemaAdapter(Context context, List<AllCinemaBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NearCinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommendcinema_layout, parent, false);
        NearCinemaHolder nearCinemaHolder = new NearCinemaHolder(view);
        return nearCinemaHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearCinemaHolder holder, final int position) {
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

    public class NearCinemaHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView CinemaIc;
        private final TextView CinemaName;
        private final TextView CinemaAddress;
        private final ImageView CinemaSelect;
        private final TextView CinemaJuli;
       public NearCinemaHolder(View itemView) {
           super(itemView);
           CinemaIc = itemView.findViewById(R.id.Cinema_ic);
           CinemaName = itemView.findViewById(R.id.Cinema_name);
           CinemaAddress = itemView.findViewById(R.id.Cinema_adress);
           CinemaSelect = itemView.findViewById(R.id.Cinema_select);
           CinemaJuli = itemView.findViewById(R.id.Cinema_juli);
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
