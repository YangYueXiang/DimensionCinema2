package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.CinemaCommentBean;
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
public class CinemaCommentModel {
    public void getCinemaData(String cinemaId, String page, String count, final CinemaCommentCallBack cinemaCommentCallBack){
        Observable<CinemaCommentBean> cinemaComment = HttpUtils.getHttpUtils().api.getCinemaComment(cinemaId, page, count);
        cinemaComment.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaCommentBean cinemaCommentBean) {
                        cinemaCommentCallBack.callback(cinemaCommentBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface CinemaCommentCallBack{
        void callback(CinemaCommentBean cinemaCommentBean);
    }
}
