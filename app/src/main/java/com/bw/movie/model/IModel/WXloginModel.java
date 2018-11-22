package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.WxBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/21
 */
public class WXloginModel {
    public void getWXLogin(String code, final WXCallBack wxCallBack){
        Observable<WxBean> wxLogin = HttpUtils.getHttpUtils().api.getWXLogin(code);
        wxLogin.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WxBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WxBean wxBean) {
                        wxCallBack.callback(wxBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface WXCallBack{
        void callback(WxBean loginBean);
    }
}
