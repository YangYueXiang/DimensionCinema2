package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.NowPlayingMovieBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public class NowPlayingMovieModel {
    public void getNowPlayingMovieData(final NowPlayingMovieCallBack nowPlayingMovieCallBack){
        final Observable<NowPlayingMovieBean> hotMovie = HttpUtils.getHttpUtils().api.getNowPlayingMovie();
        hotMovie.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NowPlayingMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NowPlayingMovieBean nowPlayingMovieBean) {
                        List<NowPlayingMovieBean.ResultBean> result = nowPlayingMovieBean.getResult();
                        nowPlayingMovieCallBack.callback(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface NowPlayingMovieCallBack{
        void callback(List<NowPlayingMovieBean.ResultBean> result);
    }
}
