package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.ReommendCinemaBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/12
 */
public class RecommendCinemaModel {
    public void getRecommendCinemaData(final RecommendCinemaCallBack recommendCinemaCallBack){
        final Observable<ReommendCinemaBean> recommendCinema = HttpUtils.getHttpUtils().api.getRecommendCinema();
        recommendCinema.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReommendCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReommendCinemaBean reommendCinemaBean) {
                        recommendCinemaCallBack.callback(reommendCinemaBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface RecommendCinemaCallBack{
        void callback(ReommendCinemaBean reommendCinemaBean);
    }

}
