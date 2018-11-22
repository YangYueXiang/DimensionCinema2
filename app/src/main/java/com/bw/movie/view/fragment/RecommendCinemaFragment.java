package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.ReommendCinemaBean;
import com.bw.movie.presenter.RecommendCinemaPresenter;
import com.bw.movie.view.IView.RecommendCinemaView;
import com.bw.movie.view.adapter.RecommendAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YangYueXiang
 * on 2018/11/12
 */
public class RecommendCinemaFragment extends Fragment implements RecommendCinemaView {
    @BindView(R.id.recommendcinema_recycler)
    RecyclerView recommendcinemaRecycler;
    Unbinder unbinder;
    private RecommendCinemaPresenter recommendCinemaPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendcinema, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recommendCinemaPresenter = new RecommendCinemaPresenter();
        recommendCinemaPresenter.attachView(this);
        recommendCinemaPresenter.getRecommendCinemaData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void recommendSuccess(ReommendCinemaBean reommendCinemaBean) {

        List<ReommendCinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = reommendCinemaBean.getResult().getNearbyCinemaList();
       // Toast.makeText(getActivity(), "nearbyCinemaList:" + nearbyCinemaList, Toast.LENGTH_SHORT).show();
        recommendcinemaRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        RecommendAdapter recommendAdapter = new RecommendAdapter(getActivity(),nearbyCinemaList);
        recommendcinemaRecycler.setAdapter(recommendAdapter);
    }

    @Override
    public void error() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recommendCinemaPresenter.detachView();
    }
}
