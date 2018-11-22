package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.CinemawhentimeBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public class WhenCinemaPlayModel {
    public void getWhenCinemaData(String cinemasId, String movieId, final WhenCinemaCallBack whenCinemaCallBack){
        Observable<CinemawhentimeBean> whenCinemaPlay = HttpUtils.getHttpUtils().api.getWhenCinemaPlay(cinemasId,movieId);
        whenCinemaPlay.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemawhentimeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemawhentimeBean cinemawhentimeBean) {
                        whenCinemaCallBack.callback(cinemawhentimeBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface WhenCinemaCallBack{
        void callback(CinemawhentimeBean cinemawhentimeBean);
    }
}
