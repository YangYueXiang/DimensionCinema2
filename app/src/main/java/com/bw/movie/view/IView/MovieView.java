package com.bw.movie.view.IView;

import com.bw.movie.model.bean.ComingMovieBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.NowPlayingMovieBean;

import java.util.List;

/**
 * Created by YangYueXiang
 * on 2018/11/6
 */
public interface MovieView extends BaseView {
    void hotmoviesuccess(HotMovieBean result);
    void hotmovieerror();

    void nowmoviesuccess(List<NowPlayingMovieBean.ResultBean> result);
    void nowmovieerror();

    void comingmoviesuccess(List<ComingMovieBean.ResultBean> result);
    void comingmovieerror();
}
