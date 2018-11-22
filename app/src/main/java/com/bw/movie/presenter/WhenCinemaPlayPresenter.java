package com.bw.movie.presenter;

import com.bw.movie.model.IModel.CinemaCommentModel;
import com.bw.movie.model.IModel.CinemaDetailPopModel;
import com.bw.movie.model.IModel.HotMovieModel;
import com.bw.movie.model.IModel.WhenCinemaPlayModel;
import com.bw.movie.model.MovieByCinemaId;
import com.bw.movie.model.bean.CinemaCommentBean;
import com.bw.movie.model.bean.CinemaDetailPop;
import com.bw.movie.model.bean.CinemawhentimeBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.MovieByCinemaIdBean;
import com.bw.movie.view.IView.WhenCinemaPlayView;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public class WhenCinemaPlayPresenter extends BasePresenter<WhenCinemaPlayView> {

    private final WhenCinemaPlayModel whenCinemaPlayModel;
    private final HotMovieModel hotMovieModel;
    private final CinemaDetailPopModel cinemaDetailPopModel;
    private final CinemaCommentModel cinemaCommentModel;
    private final MovieByCinemaId movieByCinemaId;


    public WhenCinemaPlayPresenter(){
        whenCinemaPlayModel = new WhenCinemaPlayModel();
        hotMovieModel = new HotMovieModel();
        cinemaDetailPopModel = new CinemaDetailPopModel();
        cinemaCommentModel = new CinemaCommentModel();
        movieByCinemaId = new MovieByCinemaId();

    }

    public void getWhenCinemaPlayData(String cinemasId,String movieId){
        whenCinemaPlayModel.getWhenCinemaData(cinemasId, movieId, new WhenCinemaPlayModel.WhenCinemaCallBack() {
            @Override
            public void callback(CinemawhentimeBean cinemawhentimeBean) {
                getView().whenCinemaSuccess(cinemawhentimeBean);
            }
        });
    }

    public void getCinemaDetailPopData(String cinemasId){
        cinemaDetailPopModel.getCinemaDetailData(cinemasId, new CinemaDetailPopModel.CinemaDetailCallBack() {
            @Override
            public void callback(CinemaDetailPop cinemaDetailPop) {
                getView().cinemaDetailPopsuccess(cinemaDetailPop);
            }
        });
    }

    public void getCinemaCommentData(String cinemaId,String page,String count){
        cinemaCommentModel.getCinemaData(cinemaId, page, count, new CinemaCommentModel.CinemaCommentCallBack() {
            @Override
            public void callback(CinemaCommentBean cinemaCommentBean) {
                getView().cinemaCommetSuccess(cinemaCommentBean);
            }
        });
    }

    public void getMovieByCinemaId(String cinemasId) {
        movieByCinemaId.getMovieByCinemaId(cinemasId, new MovieByCinemaId.MovieByCinemaCallBack() {
            @Override
            public void callback(MovieByCinemaIdBean movieByCinemaIdBean) {
                getView().movieSuccess(movieByCinemaIdBean);
            }
        });
    }
}
