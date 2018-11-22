package com.bw.movie.presenter;

import com.bw.movie.view.IView.BaseView;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public class BasePresenter<V extends BaseView> {
    private   V v;
  public void attachView(V iv){
        this.v=iv;
    }
    public void detachView(){
        this.v=null;
    }
   public   V getView(){
        return v;
    }
}
