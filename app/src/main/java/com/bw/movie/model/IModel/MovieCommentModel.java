package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/16
 */
public class MovieCommentModel {
    public void getMovieCommentData(String movieId, String page, String count, final MovieCommentCallBack movieCommentCallBack){
        final Observable<MovieCommentBean> movieComment = HttpUtils.getHttpUtils().api.getMovieComment(movieId, page, count);
        movieComment.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieCommentBean movieCommentBean) {
                        movieCommentCallBack.callback(movieCommentBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface MovieCommentCallBack{
        void callback(MovieCommentBean movieCommentBean);
    }
}
