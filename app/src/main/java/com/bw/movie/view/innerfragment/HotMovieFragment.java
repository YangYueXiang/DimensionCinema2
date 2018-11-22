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
import com.bw.movie.presenter.HotMoviePresenter;
import com.bw.movie.view.IView.MovieView;
import com.bw.movie.view.adapter.HotListAdapter;
import com.bw.movie.view.adapter.HotMovieAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by YangYueXiang
 * on 2018/11/8
 */
public class HotMovieFragment extends Fragment implements MovieView {
    @BindView(R.id.recycler_list_hotmovie)
    RecyclerView recyclerListHotmovie;
    Unbinder unbinder;
    private HotMoviePresenter hotMoviePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.innerfragment_hotmovie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hotMoviePresenter = new HotMoviePresenter();
        hotMoviePresenter.attachView(this);
        hotMoviePresenter.getHotMovieData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void hotmoviesuccess(final HotMovieBean result) {
      //  Toast.makeText(getActivity(), "result:" + result, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerListHotmovie.setLayoutManager(manager);
        HotListAdapter hotListAdapter = new HotListAdapter(getActivity(), result.getResult());
        recyclerListHotmovie.setAdapter(hotListAdapter);
        hotListAdapter.setOnItemClickListener(new HotMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                String movieId = result.getResult().get(position).getId();
                intent.putExtra("movieId",movieId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void hotmovieerror() {

    }

    @Override
    public void nowmoviesuccess(List<NowPlayingMovieBean.ResultBean> result) {

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
        hotMoviePresenter.detachView();
    }
}
