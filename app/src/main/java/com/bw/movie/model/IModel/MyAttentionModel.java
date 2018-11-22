package com.bw.movie.model.IModel;

import com.bw.movie.model.bean.MyAttentionBean;
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
public class MyAttentionModel {
    public void getAttentionModel(String userId, String sessionId,String page,String count, final AttentionCallBack attentionCallBack){
        Observable<MyAttentionBean> myAttentionData = HttpUtils.getHttpUtils().api.getMyAttentionData(userId, sessionId,page,count);
        myAttentionData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyAttentionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyAttentionBean myAttentionBean) {
                        attentionCallBack.callback(myAttentionBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public interface AttentionCallBack{
        void callback(MyAttentionBean myAttentionBean);
    }
}
