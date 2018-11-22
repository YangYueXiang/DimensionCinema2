package com.bw.movie.presenter;

import com.bw.movie.model.IModel.ComingMovieModel;
import com.bw.movie.model.bean.ComingMovieBean;
import com.bw.movie.view.IView.MovieView;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public class ComingMoviePresenter extends BasePresenter<MovieView>{


    private final ComingMovieModel comingMovieModel;

    public ComingMoviePresenter(){
        comingMovieModel = new ComingMovieModel();

    }
    public void getComingMovieData(){
        comingMovieModel.getComingMovieData(new ComingMovieModel.ComingMovieCallBack() {
            @Override
            public void callback(List<ComingMovieBean.ResultBean> result) {
                getView().comingmoviesuccess(result);
            }
        });
    }
}
