package com.bw.movie.view.IView;

import com.bw.movie.model.bean.ReommendCinemaBean;

/**
 * Created by YangYueXiang
 * on 2018/11/12
 */
public interface RecommendCinemaView extends BaseView{
    void recommendSuccess(ReommendCinemaBean reommendCinemaBean);
    void error();
}
