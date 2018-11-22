package com.bw.movie.view.IView;

import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.WxBean;

/**
 * Created by YangYueXiang
 * on 2018/11/14
 */
public interface LoginView extends BaseView{
    void loginsuccess(LoginBean loginBean);
    void loginerror(LoginBean loginBean);

    void wxloginsuccess(WxBean loginBean);
    void wxloginerror();
}
