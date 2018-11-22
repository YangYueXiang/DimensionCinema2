package com.bw.movie.model.IModel;

import android.util.Log;

import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.net.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YangYueXiang
 * on 2018/11/19
 */
public class FollowModel {
    public void getFollowData(String movieId, String userId, String sessionId, final FollowCallBack followCallBack){
        Observable<FollowBean> followData = HttpUtils.getHttpUtils().api.getFollowData(movieId, userId, sessionId);
        followData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        followCallBack.callback(followBean);
                        Log.i("wwwwwwwww",followBean+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("eeeeeeeee",e.getMessage()+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface FollowCallBack{
        void callback(FollowBean followBean);
    }
}
