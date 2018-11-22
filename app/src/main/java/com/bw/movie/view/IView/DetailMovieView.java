package com.bw.movie.view.IView;

import com.bw.movie.model.bean.CancelAttentionBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.bean.MovieDetailBean;

/**
 * Created by YangYueXiang
 * on 2018/11/1
 */
public interface DetailMovieView extends BaseView{
    void detailsuccess(MovieDetailBean result);
    void detailerror();
    void moviecommentsuccess(MovieCommentBean movieCommentBean);
    void commenterror();
    void followsuccess(FollowBean followBean);
    void followerror(FollowBean followBean);
    void cancelfollowsuccess(CancelAttentionBean cancelAttentionBean);
    void cancelfollowerror();
}
