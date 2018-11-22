package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bw.movie.R;
import com.bw.movie.model.bean.CinemawhentimeBean;
import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public class WhenTimeAdapter extends RecyclerView.Adapter<WhenTimeAdapter.WhenTimeHolder>{
    private Context context;
    private List<CinemawhentimeBean.ResultBean> list;

    public WhenTimeAdapter(Context context, List<CinemawhentimeBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WhenTimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.whentime_layout, parent, false);
        WhenTimeHolder whenTimeHolder = new WhenTimeHolder(view);
        return whenTimeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WhenTimeHolder holder, final int position) {
        holder.dating.setText(list.get(position).getScreeningHall());
        holder.begintime.setText(list.get(position).getBeginTime());
        holder.endtime.setText(list.get(position).getEndTime());
        holder.price.setText(list.get(position).getPrice()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WhenTimeHolder extends RecyclerView.ViewHolder{

        private final TextView dating;
        private final TextView howD;
        private final TextView begintime;
        private final TextView endtime;
        private final TextView price;
        private final ImageView img_right;

        public WhenTimeHolder(View itemView) {
           super(itemView);
            dating = itemView.findViewById(R.id.dating);
            howD = itemView.findViewById(R.id.howD);
            begintime = itemView.findViewById(R.id.begintime);
            endtime = itemView.findViewById(R.id.endtime);
            price = itemView.findViewById(R.id.price);
            img_right = itemView.findViewById(R.id.img_right);
        }
   }
   public interface OnItemClickListener{
        void OnItemClick(int position);
   }
   private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
