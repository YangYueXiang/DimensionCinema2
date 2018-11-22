package com.bw.movie.presenter;

import com.bw.movie.model.CancelAttention;
import com.bw.movie.model.IModel.DetailModel;
import com.bw.movie.model.IModel.FollowModel;
import com.bw.movie.model.IModel.MovieCommentModel;
import com.bw.movie.model.bean.CancelAttentionBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.bean.MovieDetailBean;
import com.bw.movie.view.IView.DetailMovieView;

/**
 * Created by YangYueXiang
 * on 2018/11/9
 */
public class DetailPresenter extends BasePresenter<DetailMovieView>{

    private final DetailModel detailModel;
    private final MovieCommentModel movieCommentModel;
    private final FollowModel followModel;
    private final CancelAttention cancelAttention;

    public DetailPresenter(){
        detailModel = new DetailModel();
        movieCommentModel = new MovieCommentModel();
        followModel = new FollowModel();
        cancelAttention = new CancelAttention();
    }
    public void getDetailData(String movieId){
        detailModel.getDetailData(movieId,new DetailModel.DetailCallBack() {
            @Override
            public void callback(MovieDetailBean movieDetailBean) {
                getView().detailsuccess(movieDetailBean);
            }
        });
    }

    public void getMovieCommentData(String movieId,String page,String count){
        movieCommentModel.getMovieCommentData(movieId, page, count, new MovieCommentModel.MovieCommentCallBack() {
            @Override
            public void callback(MovieCommentBean movieCommentBean) {
                getView().moviecommentsuccess(movieCommentBean);
            }
        });
    }

    public void getFollowData(String movieId,String userId,String sessionId){
        followModel.getFollowData(movieId, userId, sessionId, new FollowModel.FollowCallBack() {
            @Override
            public void callback(FollowBean followBean) {
                if (followBean.getMessage().equals("关注成功")){
                    getView().followsuccess(followBean);
                }else{
                    getView().followerror(followBean);
                }

            }
        });
    }

    public void calcelAttention(String movieId, String userId, String sessionId) {
        cancelAttention.getCancelData(movieId, userId, sessionId, new CancelAttention.CancelCallBack() {
            @Override
            public void callback(CancelAttentionBean cancelAttentionBean) {
                getView().cancelfollowsuccess(cancelAttentionBean);
            }
        });
    }
}
