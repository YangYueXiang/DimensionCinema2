package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bw.movie.CinemaDetailActivity;
import com.bw.movie.R;
import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.presenter.AllCinemaPresenter;
import com.bw.movie.view.IView.AllCinemaView;
import com.bw.movie.view.adapter.NearCinemaAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YangYueXiang
 * on 2018/11/12
 */
public class FirstCinemaFragment extends Fragment implements AllCinemaView {

    @BindView(R.id.nearcinema_recycler)
    RecyclerView nearcinemaRecycler;
    Unbinder unbinder;
    private AllCinemaPresenter allCinemaPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearcinema, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allCinemaPresenter = new AllCinemaPresenter();
        allCinemaPresenter.attachView(this);
        allCinemaPresenter.getAllCinemaData();
    }

    @Override
    public void allcinemasuccess(final AllCinemaBean allCinemaBean) {
        //Toast.makeText(getActivity(), "allCinemaBean.getResult():" + allCinemaBean.getResult(), Toast.LENGTH_SHORT).show();
        nearcinemaRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        NearCinemaAdapter nearCinemaAdapter = new NearCinemaAdapter(getActivity(),allCinemaBean.getResult());
        nearcinemaRecycler.setAdapter(nearCinemaAdapter);
        nearCinemaAdapter.setOnItemClickListener(new NearCinemaAdapter.OnItemClickListener() {

            private String logo;
            private String address;
            private String name;
            private String cinemasId;

            @Override
            public void onItemClick(int position) {
                logo = allCinemaBean.getResult().get(position).getLogo();
                Intent intent=new Intent(getActivity(),CinemaDetailActivity.class);
                cinemasId = String.valueOf(allCinemaBean.getResult().get(position).getId());
                name = allCinemaBean.getResult().get(position).getName();
                address = allCinemaBean.getResult().get(position).getAddress();
                intent.putExtra("cinemasId",cinemasId);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                intent.putExtra("logo",logo);
              /*  logo.setDrawingCacheEnabled(Boolean.TRUE);
                intent.putExtra("BITMAP", logo.getDrawingCache()); //这里可以放一个bitmap*/
                startActivity(intent);
            }
        });
    }

    @Override
    public void error() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        allCinemaPresenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
