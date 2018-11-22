package com.bw.movie.presenter;

import com.bw.movie.model.IModel.RecommendCinemaModel;
import com.bw.movie.model.bean.ReommendCinemaBean;
import com.bw.movie.view.IView.RecommendCinemaView;

/**
 * Created by YangYueXiang
 * on 2018/11/12
 */
public class RecommendCinemaPresenter extends BasePresenter<RecommendCinemaView>{

    private final RecommendCinemaModel recommendCinemaModel;

    public RecommendCinemaPresenter(){
        recommendCinemaModel = new RecommendCinemaModel();
    }
    public void getRecommendCinemaData(){
        recommendCinemaModel.getRecommendCinemaData(new RecommendCinemaModel.RecommendCinemaCallBack() {
            @Override
            public void callback(ReommendCinemaBean reommendCinemaBean) {
                getView().recommendSuccess(reommendCinemaBean);
            }
        });
    }
}
