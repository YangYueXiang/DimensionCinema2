package com.bw.movie.view.IView;

import com.bw.movie.model.bean.MyAttentionBean;

/**
 * Created by YangYueXiang
 * on 2018/11/21
 */
public interface MyAttentionView extends BaseView {
    void attentionsuccess(MyAttentionBean myAttentionBean);
    void attentionerror();
}
