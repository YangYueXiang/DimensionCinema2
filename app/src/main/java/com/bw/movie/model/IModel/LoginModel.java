package com.bw.movie.model.IModel;

import android.util.Log;

import com.bw.movie.model.bean.LoginBean;
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
public class LoginModel {
    public void getLoginData(HashMap<String,String> map, final LoginCallBack loginCallBack){
        Observable<LoginBean> loginData = HttpUtils.getHttpUtils().api.getLoginData(map);
        loginData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.i("xixixi",loginBean.getMessage());
                        loginCallBack.callback(loginBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface LoginCallBack{
        void callback(LoginBean loginBean);
    }
}
