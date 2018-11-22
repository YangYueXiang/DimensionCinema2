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

import com.bw.movie.model.bean.MovieCommentBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/16
 */
public class MovieCommentAdapter extends RecyclerView.Adapter<MovieCommentAdapter.MovieCommentHolder> {
    private Context context;
    private List<MovieCommentBean.ResultBean> list;

    public MovieCommentAdapter(Context context, List<MovieCommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieCommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_comment, parent, false);
        MovieCommentHolder movieCommentHolder = new MovieCommentHolder(view);
        return movieCommentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCommentHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getCommentHeadPic());
        holder.simp_user.setImageURI(uri);
        holder.tv_commentcontent.setText(list.get(position).getCommentContent());
        holder.tv_username.setText(list.get(position).getCommentUserName());
        holder.tv_parisenum.setText(list.get(position).getGreatNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieCommentHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simp_user;
        private final TextView tv_username;
        private final TextView tv_commentcontent;
        private final TextView tv_parisenum;

        public MovieCommentHolder(View itemView) {
            super(itemView);
            simp_user = itemView.findViewById(R.id.simp_user);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_commentcontent = itemView.findViewById(R.id.tv_commentcontent);
            tv_parisenum = itemView.findViewById(R.id.tv_praisenum);
        }
    }
}
