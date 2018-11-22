package com.bw.movie.view.IView;

import com.bw.movie.model.bean.AllCinemaBean;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public interface AllCinemaView extends BaseView{
    void allcinemasuccess(AllCinemaBean allCinemaBean);
    void error();
}
