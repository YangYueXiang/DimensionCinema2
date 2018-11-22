package com.bw.movie.model.net;


import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.model.bean.CancelAttentionBean;
import com.bw.movie.model.bean.CinemaByMovieIdBean;
import com.bw.movie.model.bean.CinemaCommentBean;
import com.bw.movie.model.bean.CinemaDetailPop;
import com.bw.movie.model.bean.CinemawhentimeBean;
import com.bw.movie.model.bean.ComingMovieBean;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.MovieByCinemaIdBean;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.bean.MyAttentionBean;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.bean.ReommendCinemaBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.MovieDetailBean;
import com.bw.movie.model.bean.NowPlayingMovieBean;
import com.bw.movie.model.bean.WxBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by YangYueXiang
 * on 2018/10/31
 */
public interface Api {
    @GET("movie/v1/findHotMovieList?page=1&count=10")
    Observable<HotMovieBean> getHotMovie();

    @GET("movie/v1/findReleaseMovieList?page=1&count=10")
    Observable<NowPlayingMovieBean> getNowPlayingMovie();

    @GET("movie/v1/findComingSoonMovieList?page=1&count=10")
    Observable<ComingMovieBean> getComingMovie();

    @GET("movie/v1/findMoviesDetail")
    Observable<MovieDetailBean> getDetailMovie(@Query("movieId") String movieId);

    @GET("cinema/v1/findRecommendCinemas?page=1&count=10")
    Observable<ReommendCinemaBean> getRecommendCinema();

    @GET("cinema/v1/findAllCinemas?page=1&count=10")
    Observable<AllCinemaBean> getAllCinema();

    @GET("movie/v1/findMovieScheduleList")
    Observable<CinemawhentimeBean> getWhenCinemaPlay(@Query("cinemasId") String cinemasId, @Query("movieId") String movieId);

    //注册
    @FormUrlEncoded
    @POST("user/v1/registerUser")
    Observable<RegisterBean> getRegisterData(@FieldMap Map<String,String> map);
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> getLoginData(@FieldMap Map<String,String> map);
    //cinemaDetailpop详情
    @GET("cinema/v1/findCinemaInfo")
    Observable<CinemaDetailPop> getCinemaDetailPop(@Query("cinemaId") String cinemaId);

    @GET("cinema/v1/findAllCinemaComment")
    Observable<CinemaCommentBean> getCinemaComment(@Query("cinemaId") String cinemaId, @Query("page") String page, @Query("count") String count);
    @GET("movie/v1/findAllMovieComment")
    Observable<MovieCommentBean> getMovieComment(@Query("movieId") String movieId, @Query("page") String page, @Query("count") String count);
    @GET("movie/v1/verify/followMovie")
    Observable<FollowBean> getFollowData(@Query("movieId") String movieId, @Header("userId")String userId,@Header("sessionId") String sessionId);
    @GET("movie/v1/verify/findMoviePageList")
    Observable<MyAttentionBean> getMyAttentionData(@Header("userId")String userId, @Header("sessionId") String sessionId,@Query("page") String page,@Query("count") String count);
    @POST("user/v1/weChatBindingLogin")
    @FormUrlEncoded
    Observable<WxBean> getWXLogin(@Field("code") String code);
    //取消关注
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<CancelAttentionBean> getCancelAttention(@Query("movieId") String movieId,@Header("userId") String userId,@Header("sessionId") String sessionId);
    //根据影院Id查询电影
    @GET("movie/v1/findMovieListByCinemaId")
    Observable<MovieByCinemaIdBean> getMovieByCinemaId(@Query("cinemaId") String cinemaId);
    //根据电影Id查询影院
    @GET("movie/v1/findCinemasListByMovieId")
    Observable<CinemaByMovieIdBean> getCinemaByMovieId(@Query("movieId") String movieId);
}
