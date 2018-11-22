package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.CinemaByMovieIdBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/22
 */
public class CinemaByMovieIdModel {
    public void getCinemaByMovie(String movieId, final CinemaByMovie cinemaByMovie){
        Observable<CinemaByMovieIdBean> cinemaByMovieId = HttpUtils.getHttpUtils().api.getCinemaByMovieId(movieId);
        cinemaByMovieId.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaByMovieIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaByMovieIdBean cinemaByMovieIdBean) {
                        cinemaByMovie.callback(cinemaByMovieIdBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public interface CinemaByMovie{
        void callback(CinemaByMovieIdBean cinemaByMovieIdBean);
    }
}
