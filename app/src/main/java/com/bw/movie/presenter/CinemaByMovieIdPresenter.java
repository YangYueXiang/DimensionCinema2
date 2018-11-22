package com.bw.movie.presenter;

import com.bw.movie.model.IModel.CinemaByMovieIdModel;
import com.bw.movie.model.bean.CinemaByMovieIdBean;
import com.bw.movie.view.IView.CinemaByMovieView;

/**
 * Created by YangYueXiang
 * on 2018/11/22
 */
public class CinemaByMovieIdPresenter extends BasePresenter<CinemaByMovieView>{

    private final CinemaByMovieIdModel cinemaByMovieIdModel;

    public CinemaByMovieIdPresenter(){
        cinemaByMovieIdModel = new CinemaByMovieIdModel();
    }
    public void getCinemaByMovieId(String movieId){
        cinemaByMovieIdModel.getCinemaByMovie(movieId, new CinemaByMovieIdModel.CinemaByMovie() {
            @Override
            public void callback(CinemaByMovieIdBean cinemaByMovieIdBean) {
                getView().cinemaByMovieSuccess(cinemaByMovieIdBean);
            }
        });
    }
}
