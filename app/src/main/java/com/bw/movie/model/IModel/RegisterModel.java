package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.net.HttpUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/14
 */
public class RegisterModel {
    public void getRegisterData(HashMap<String,String> map, final RegisterCallBack registerCallBack){
        Observable<RegisterBean> registerData = HttpUtils.getHttpUtils().api.getRegisterData(map);
        registerData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        registerCallBack.callback(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface RegisterCallBack{
        void callback(RegisterBean registerBean);
    }
}
