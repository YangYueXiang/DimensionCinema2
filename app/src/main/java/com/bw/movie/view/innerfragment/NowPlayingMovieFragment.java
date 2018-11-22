package com.bw.movie.view.innerfragment;

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

import com.bw.movie.DetailActivity;
import com.bw.movie.R;


import com.bw.movie.model.bean.ComingMovieBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.NowPlayingMovieBean;
import com.bw.movie.presenter.NowPlayingMoviePresenter;
import com.bw.movie.view.IView.MovieView;
import com.bw.movie.view.adapter.NowPlayingListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YangYueXiang
 * on 2018/11/8
 */
public class NowPlayingMovieFragment extends Fragment implements MovieView {
    @BindView(R.id.recycler_list_nowplaying)
    RecyclerView recyclerListNowplaying;
    Unbinder unbinder;
    private NowPlayingMoviePresenter nowPlayingMoviePresenter;
    private NowPlayingListAdapter nowPlayingListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.innerfragment_nowplayingmovie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nowPlayingMoviePresenter = new NowPlayingMoviePresenter();
        nowPlayingMoviePresenter.attachView(this);
        nowPlayingMoviePresenter.getNowPlayingMovieData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void hotmoviesuccess(HotMovieBean result) {

    }

    @Override
    public void hotmovieerror() {

    }

    @Override
    public void nowmoviesuccess(final List<NowPlayingMovieBean.ResultBean> result) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerListNowplaying.setLayoutManager(manager);
        nowPlayingListAdapter = new NowPlayingListAdapter(getActivity(), result);
        recyclerListNowplaying.setAdapter(nowPlayingListAdapter);
        nowPlayingListAdapter.setOnItemClickListener(new NowPlayingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                String movieId = result.get(position).getId();
                intent.putExtra("movieId",movieId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void nowmovieerror() {

    }

    @Override
    public void comingmoviesuccess(List<ComingMovieBean.ResultBean> result) {

    }

    @Override
    public void comingmovieerror() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nowPlayingMoviePresenter.detachView();
    }
}
