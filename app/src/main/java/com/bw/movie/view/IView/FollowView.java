package com.bw.movie.view.IView;

import com.bw.movie.model.bean.FollowBean;

/**
 * Created by YangYueXiang
 * on 2018/11/19
 */
public interface FollowView extends BaseView{
    void followsuccess(FollowBean followBean);
    void followerror(FollowBean followBean);

}
