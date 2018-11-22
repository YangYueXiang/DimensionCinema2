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

import com.bw.movie.model.bean.ReommendCinemaBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/12
 */
public class RecommendAdapter extends RecyclerView.Adapter <RecommendAdapter.RecommedHolder>{
   private Context context;
   private List<ReommendCinemaBean.ResultBean.NearbyCinemaListBean> list;

   public RecommendAdapter(Context context, List<ReommendCinemaBean.ResultBean.NearbyCinemaListBean> list) {
      this.context = context;
      this.list = list;
   }

   @NonNull
   @Override
   public RecommedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.recommendcinema_layout, parent, false);
      RecommedHolder recommedHolder = new RecommedHolder(view);
      return recommedHolder;
   }

   @Override
   public void onBindViewHolder(@NonNull RecommedHolder holder, int position) {
      Uri uri = Uri.parse(list.get(position).getLogo());
      holder.CinemaIc.setImageURI(uri);
      holder.CinemaName.setText(list.get(position).getName());
      holder.CinemaAddress.setText(list.get(position).getAddress());
   }

   @Override
   public int getItemCount() {
      return list.size();
   }

   public class RecommedHolder extends RecyclerView.ViewHolder{

      private final SimpleDraweeView CinemaIc;
      private final TextView CinemaName;
      private final TextView CinemaAddress;
      private final ImageView CinemaSelect;
      private final TextView CinemaJuli;

      public RecommedHolder(View itemView) {
         super(itemView);
         CinemaIc = itemView.findViewById(R.id.Cinema_ic);
         CinemaName = itemView.findViewById(R.id.Cinema_name);
         CinemaAddress = itemView.findViewById(R.id.Cinema_adress);
         CinemaSelect = itemView.findViewById(R.id.Cinema_select);
         CinemaJuli = itemView.findViewById(R.id.Cinema_juli);
      }
   }
}
