package com.bw.movie.view.IView;

import com.bw.movie.model.bean.CinemaCommentBean;
import com.bw.movie.model.bean.CinemaDetailPop;
import com.bw.movie.model.bean.CinemawhentimeBean;
import com.bw.movie.model.bean.MovieByCinemaIdBean;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public interface WhenCinemaPlayView extends BaseView{
    void whenCinemaSuccess(CinemawhentimeBean cinemawhentimeBean);
    void error();
    void movieSuccess(MovieByCinemaIdBean result);
    void movieerror();
    void cinemaDetailPopsuccess(CinemaDetailPop cinemaDetailPop);
    void cinemaerror();
    void cinemaCommetSuccess(CinemaCommentBean cinemaCommentBean);
    void cinemacommenterror();
}
