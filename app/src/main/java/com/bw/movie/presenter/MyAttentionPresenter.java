package com.bw.movie.presenter;

import com.bw.movie.model.IModel.MyAttentionModel;
import com.bw.movie.model.bean.MyAttentionBean;
import com.bw.movie.view.IView.MyAttentionView;

/**
 * Created by YangYueXiang
 * on 2018/11/21
 */
public class MyAttentionPresenter extends BasePresenter<MyAttentionView>{

    private final MyAttentionModel myAttentionModel;

    public MyAttentionPresenter(){
        myAttentionModel = new MyAttentionModel();
    }
    public void getAttentionData(String userId,String sessionId,String page,String count){
        myAttentionModel.getAttentionModel(userId, sessionId, page,count,new MyAttentionModel.AttentionCallBack() {
            @Override
            public void callback(MyAttentionBean myAttentionBean) {
                getView().attentionsuccess(myAttentionBean);
            }
        });
    }
}
