package com.bw.movie.model.IModel;

import android.util.Log;

import com.bw.movie.model.bean.CinemaDetailPop;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/15
 */
public class CinemaDetailPopModel {
    public void getCinemaDetailData(String cinemasId, final CinemaDetailCallBack cinemaDetailCallBack){
        Observable<CinemaDetailPop> cinemaDetailPop = HttpUtils.getHttpUtils().api.getCinemaDetailPop(cinemasId);
        cinemaDetailPop.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaDetailPop>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaDetailPop cinemaDetailPop) {
                        Log.i("xixixixi",cinemaDetailPop+"");
                        cinemaDetailCallBack.callback(cinemaDetailPop);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface CinemaDetailCallBack{
        void callback(CinemaDetailPop cinemaDetailPop);
    }
}
