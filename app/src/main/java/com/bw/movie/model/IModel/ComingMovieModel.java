package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.ComingMovieBean;
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
public class ComingMovieModel {
    public void getComingMovieData(final ComingMovieCallBack comingMovieCallBack){
        final Observable<ComingMovieBean> hotMovie = HttpUtils.getHttpUtils().api.getComingMovie();
        hotMovie.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingMovieBean comingMovieBean) {
                        List<ComingMovieBean.ResultBean> result = comingMovieBean.getResult();
                        comingMovieCallBack.callback(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ComingMovieCallBack{
        void callback(List<ComingMovieBean.ResultBean> result);
    }
}
