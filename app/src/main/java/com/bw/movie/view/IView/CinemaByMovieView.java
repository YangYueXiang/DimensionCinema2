package com.bw.movie.view.IView;

import com.bw.movie.model.bean.CinemaByMovieIdBean;

/**
 * Created by YangYueXiang
 * on 2018/11/22
 */
public interface CinemaByMovieView extends BaseView{
     void cinemaByMovieSuccess(CinemaByMovieIdBean cinemaByMovieIdBean);
     void cinemaByMovieError();
}
