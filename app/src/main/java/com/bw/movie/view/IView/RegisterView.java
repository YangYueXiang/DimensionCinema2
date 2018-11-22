package com.bw.movie.view.IView;

import com.bw.movie.model.bean.RegisterBean;

/**
 * Created by YangYueXiang
 * on 2018/11/14
 */
public interface RegisterView extends BaseView{
    void registersuccess(RegisterBean registerBean);
    void registererror();
}
