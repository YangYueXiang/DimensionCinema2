package com.bw.movie.presenter;

import com.bw.movie.model.IModel.AllCinemaModel;
import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.view.IView.AllCinemaView;

/**
 * Created by YangYueXiang
 * on 2018/11/13
 */
public class AllCinemaPresenter extends BasePresenter<AllCinemaView>{

    private final AllCinemaModel allCinemaModel;

    public AllCinemaPresenter(){
        allCinemaModel = new AllCinemaModel();
    }
    public void getAllCinemaData(){
        allCinemaModel.getAllCinemaData(new AllCinemaModel.AllCinemaCallBack() {
            @Override
            public void callback(AllCinemaBean allCinemaBean) {
                getView().allcinemasuccess(allCinemaBean);
            }
        });
    }
}
