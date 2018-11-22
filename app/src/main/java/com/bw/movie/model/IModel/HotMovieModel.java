package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public class HotMovieModel {
    public void getHotMovieData(final HotMovieCallBack hotMovieCallBack){
        final Observable<HotMovieBean> hotMovie = HttpUtils.getHttpUtils().api.getHotMovie();
        hotMovie.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotMovieBean hotMovieBean) {
                        hotMovieCallBack.callback(hotMovieBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface HotMovieCallBack{
        void callback(HotMovieBean result);
    }
}
