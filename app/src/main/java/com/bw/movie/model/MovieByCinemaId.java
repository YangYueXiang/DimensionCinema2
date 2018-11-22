package com.bw.movie.model;

import com.bw.movie.model.bean.MovieByCinemaIdBean;
import com.bw.movie.model.net.HttpUtils;

import javax.security.auth.callback.Callback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/22
 */
public class MovieByCinemaId {
    public void getMovieByCinemaId(String cinemaId, final MovieByCinemaCallBack movieByCinemaCallBack){
        Observable<MovieByCinemaIdBean> movieByCinemaId = HttpUtils.getHttpUtils().api.getMovieByCinemaId(cinemaId);
        movieByCinemaId.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieByCinemaIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieByCinemaIdBean movieByCinemaIdBean) {
                        movieByCinemaCallBack.callback(movieByCinemaIdBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface MovieByCinemaCallBack{
        void callback(MovieByCinemaIdBean movieByCinemaIdBean);
    }
}
