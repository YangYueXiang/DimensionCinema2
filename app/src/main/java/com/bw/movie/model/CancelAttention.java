package com.bw.movie.model;

import com.bw.movie.model.bean.CancelAttentionBean;
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
public class CancelAttention {
    public void getCancelData(String movieId, String userId, String sessionId, final CancelCallBack cancelCallBack){
        final Observable<CancelAttentionBean> cancelAttention = HttpUtils.getHttpUtils().api.getCancelAttention(movieId, userId, sessionId);
        cancelAttention.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancelAttentionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancelAttentionBean cancelAttentionBean) {
                        cancelCallBack.callback(cancelAttentionBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public interface CancelCallBack{
        void callback(CancelAttentionBean cancelAttentionBean);
    }
}
