package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.MovieDetailBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/9
 */
public class DetailModel {
    public void getDetailData( String movieId,final DetailCallBack detailCallBack){
        Observable<MovieDetailBean> detailMovie = HttpUtils.getHttpUtils().api.getDetailMovie(movieId);
        detailMovie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        detailCallBack.callback(movieDetailBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface DetailCallBack{
        void callback(MovieDetailBean movieDetailBean);
    }
}
