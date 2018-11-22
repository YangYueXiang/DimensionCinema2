package com.bw.movie.presenter;

import com.bw.movie.model.IModel.LoginModel;
import com.bw.movie.model.IModel.WXloginModel;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.WxBean;
import com.bw.movie.view.IView.LoginView;

import java.util.HashMap;

/**
 * Created by YangYueXiang
 * on 2018/11/14
 */
public class LoginPresenter extends BasePresenter<LoginView>{

    private final LoginModel loginModel;
    private final WXloginModel wXloginModel;

    public LoginPresenter(){
        loginModel = new LoginModel();
        wXloginModel = new WXloginModel();
    }
    public void getLoginData(HashMap<String,String> map){
        loginModel.getLoginData(map, new LoginModel.LoginCallBack() {
            @Override
            public void callback(LoginBean loginBean) {
                LoginView view = getView();
                view.loginsuccess(loginBean);

            }
        });
    }
    public void getWXLoginData(String code){
        wXloginModel.getWXLogin(code, new WXloginModel.WXCallBack() {
            @Override
            public void callback(WxBean loginBean) {
                getView().wxloginsuccess(loginBean);
            }
        });
    }
}
