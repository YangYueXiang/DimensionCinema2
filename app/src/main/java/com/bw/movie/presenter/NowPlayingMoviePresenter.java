package com.bw.movie.presenter;

import com.bw.movie.model.IModel.NowPlayingMovieModel;
import com.bw.movie.model.bean.NowPlayingMovieBean;
import com.bw.movie.view.IView.MovieView;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public class NowPlayingMoviePresenter extends BasePresenter<MovieView>{


    private final NowPlayingMovieModel nowPlayingMovieModel;

    public NowPlayingMoviePresenter(){
        nowPlayingMovieModel = new NowPlayingMovieModel();
    }
    public void getNowPlayingMovieData(){
       nowPlayingMovieModel.getNowPlayingMovieData(new NowPlayingMovieModel.NowPlayingMovieCallBack() {
           @Override
           public void callback(List<NowPlayingMovieBean.ResultBean> result) {
                getView().nowmoviesuccess(result);
           }
    });
    }
}
