package com.bw.movie.presenter;

import com.bw.movie.model.IModel.HotMovieModel;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.view.IView.MovieView;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public class HotMoviePresenter extends BasePresenter<MovieView>{

    private final HotMovieModel hotMovieModel;

    public HotMoviePresenter(){
        hotMovieModel = new HotMovieModel();
    }
    public void getHotMovieData(){
        hotMovieModel.getHotMovieData(new HotMovieModel.HotMovieCallBack() {
            @Override
            public void callback(HotMovieBean result) {
                getView().hotmoviesuccess(result);
            }
        });
    }
}
