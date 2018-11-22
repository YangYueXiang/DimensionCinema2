package com.bw.movie.presenter;

import com.bw.movie.model.IModel.RegisterModel;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.view.IView.RegisterView;

import java.util.HashMap;

/**
 * Created by YangYueXiang
 * on 2018/11/14
 */
public class RegisterPresenter extends BasePresenter<RegisterView>{

    private final RegisterModel registerModel;

    public RegisterPresenter(){
        registerModel = new RegisterModel();
    }
    public void getRegisterData(HashMap<String,String> map){
        registerModel.getRegisterData(map, new RegisterModel.RegisterCallBack() {
            @Override
            public void callback(RegisterBean registerBean) {
                getView().registersuccess(registerBean);
            }
        });
    }
}
