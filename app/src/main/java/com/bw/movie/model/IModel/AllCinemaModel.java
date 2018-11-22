package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.AllCinemaBean;
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
public class AllCinemaModel {
    public void getAllCinemaData(final AllCinemaCallBack allCinemaCallBack){
        Observable<AllCinemaBean> allCinema = HttpUtils.getHttpUtils().api.getAllCinema();
        allCinema.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AllCinemaBean allCinemaBean) {
                        allCinemaCallBack.callback(allCinemaBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface AllCinemaCallBack{
        void callback(AllCinemaBean allCinemaBean);
    }
}
