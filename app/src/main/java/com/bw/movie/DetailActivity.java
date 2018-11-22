package com.bw.movie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.model.bean.CancelAttentionBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.model.bean.FollowBean;
import com.bw.movie.model.bean.MovieCommentBean;
import com.bw.movie.model.bean.MovieDetailBean;
import com.bw.movie.presenter.DetailPresenter;
import com.bw.movie.view.IView.DetailMovieView;
import com.bw.movie.view.adapter.DramaAdapter;
import com.bw.movie.view.adapter.MovieCommentAdapter;
import com.bw.movie.view.adapter.MovieReviewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailMovieView {
    @BindView(R.id.btn_action)
    Button btnAction;
    @BindView(R.id.text_action)
    TextView textAction;
    @BindView(R.id.btn_heartxiangqing)
    Button btnHeartxiangqing;
    @BindView(R.id.text_xiangname)
    TextView textXiangname;
    @BindView(R.id.simp_xiangview)
    SimpleDraweeView simpXiangview;
    @BindView(R.id.btn_yugao)
    Button btnYugao;
    @BindView(R.id.btn_photo)
    Button btnPhoto;
    @BindView(R.id.btn_ping)
    Button btnPing;
    @BindView(R.id.btn_fan)
    Button btnFan;
    @BindView(R.id.btn_gou)
    Button btnGou;
    @BindView(R.id.btn_xiangqing)
    Button btnXiangqing;

    private DetailPresenter detailPresenter;
    private SimpleDraweeView simp_pop_movie;
    private TextView tv_pop_title;
    private TextView tv_director;
    private TextView tv_length;
    private TextView tv_type;
    private TextView tv_placeoforigin;
    private TextView tv_jianjie;
    private String movieId;
    private MovieDetailBean.ResultBean result;
    private Uri uri;
    private RecyclerView recycler_photo;
    private PopupWindow window;
    private View view;
    private PopupWindow window1;
    private View view3;
    private PopupWindow window2;
    private View view2;
    private PopupWindow window3;
    private View view1;
    private RecyclerView recycler_moviecomment;
    private List<MovieCommentBean.ResultBean> result1;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId");
        sp = getSharedPreferences("user", MODE_PRIVATE);
        userId = sp.getString("userId", "");
        sessionId = sp.getString("sessionId", "");
        detailPresenter = new DetailPresenter();
        detailPresenter.attachView(this);
        detailPresenter.getDetailData(movieId);
        detailPresenter.getMovieCommentData(movieId,String.valueOf(1),String.valueOf(5));
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pup_detail, null);
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);

        LayoutInflater inflater3 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view3 = inflater3.inflate(R.layout.pup_previewa, null);
        window3 = new PopupWindow(view3,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window3.setFocusable(true);
        ColorDrawable dw3 = new ColorDrawable(0xffffffff);
        window3.setBackgroundDrawable(dw3);
        window3.setAnimationStyle(R.style.PopupAnimation);

        LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view1 = inflater1.inflate(R.layout.pup_film_review, null);
        window1 = new PopupWindow(view1,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window1.setFocusable(true);
        ColorDrawable dw1 = new ColorDrawable(0xffffffff);
        window1.setBackgroundDrawable(dw1);
        window1.setAnimationStyle(R.style.PopupAnimation);

        LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view2 = inflater2.inflate(R.layout.pup_dram, null);
        window2 = new PopupWindow(view2,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window2.setFocusable(true);
        ColorDrawable dw2 = new ColorDrawable(0xffffffff);
        window2.setBackgroundDrawable(dw2);
        window2.setAnimationStyle(R.style.PopupAnimation);
    }

    @Override
    public void detailsuccess(MovieDetailBean result) {
        //Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();
       // Log.i("xiang",result.getResult().toString()+"");
        final boolean followMovie = result.getResult().isFollowMovie();
        if (followMovie){
            btnHeartxiangqing.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite_default));

        }else {
            btnHeartxiangqing.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite_checked));
        }
        textXiangname.setText(result.getResult().getName());
        uri = Uri.parse(result.getResult().getImageUrl());
        this.result = result.getResult();
        simpXiangview.setImageURI(uri);

        btnHeartxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                if (sp.getBoolean("isLogin",false)){
                    if (followMovie){
                        detailPresenter.getFollowData(movieId,userId,sessionId);
                    }else{
                        detailPresenter.calcelAttention(movieId,userId,sessionId);

                    }

                }else{
                    Toast.makeText(DetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void detailerror() {

    }

    @Override
    public void moviecommentsuccess(MovieCommentBean movieCommentBean) {
        result1 = movieCommentBean.getResult();
    }

    @Override
    public void commenterror() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.detachView();
    }

    @OnClick({R.id.btn_xiangqing, R.id.btn_yugao, R.id.btn_photo, R.id.btn_ping, R.id.btn_fan, R.id.btn_gou})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_xiangqing:
                showPopwindow1();
                break;
            case R.id.btn_yugao:
                showPopwindow2();
                break;
            case R.id.btn_photo:
                showPopwindow3();
                break;
            case R.id.btn_ping:
                showPopwindow4();
                break;
            case R.id.btn_fan:
                finish();
                break;
            case R.id.btn_gou:
                Intent intent=new Intent(DetailActivity.this,CinemaByMovieActivity.class);
                intent.putExtra("movieId",movieId);
                startActivity(intent);
                break;
        }
    }

    private void showPopwindow1() {
        window.showAtLocation(DetailActivity.this.findViewById(R.id.btn_xiangqing),
                Gravity.BOTTOM, 0, 0);
        simp_pop_movie = view.findViewById(R.id.simp_pop_movie);
        tv_pop_title = view.findViewById(R.id.tv_pop_title);
        tv_director = view.findViewById(R.id.tv_director);
        tv_length = view.findViewById(R.id.tv_length);
        tv_type = view.findViewById(R.id.tv_type);
        tv_placeoforigin = view.findViewById(R.id.tv_placeoforigin);
        tv_jianjie = view.findViewById(R.id.tv_jianjie);

        simp_pop_movie.setImageURI(uri);
        tv_pop_title.setText(result.getName());
        tv_director.setText(result.getDirector());
        tv_type.setText(result.getMovieTypes());
        tv_length.setText(result.getDuration());
        tv_placeoforigin.setText(result.getPlaceOrigin());
        tv_jianjie.setText(result.getSummary());

    }

    private void showPopwindow2() {
        window1.showAtLocation(DetailActivity.this.findViewById(R.id.btn_yugao),
                Gravity.BOTTOM, 0, 0);
        RecyclerView recycler_movie_review = view1.findViewById(R.id.recycler_movie_review);
        recycler_movie_review.setLayoutManager(new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.VERTICAL,false));
        MovieReviewAdapter movieReviewAdapter = new MovieReviewAdapter(DetailActivity.this,result.getShortFilmList(),result.getName());
        recycler_movie_review.setAdapter(movieReviewAdapter);
    }

    private void showPopwindow3() {
        window2.showAtLocation(DetailActivity.this.findViewById(R.id.btn_photo),
                Gravity.BOTTOM, 0, 0);
        recycler_photo = view2.findViewById(R.id.recycler_photo);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycler_photo.setLayoutManager(layoutManager);
        DramaAdapter dramaAdapter = new DramaAdapter(DetailActivity.this, result.getPosterList());
        recycler_photo.setAdapter(dramaAdapter);
    }

    private void showPopwindow4() {
        window3.showAtLocation(DetailActivity.this.findViewById(R.id.btn_ping),
                Gravity.BOTTOM, 0, 0);
        recycler_moviecomment = view3.findViewById(R.id.recycler_moviecomment);
        recycler_moviecomment.setLayoutManager(new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.VERTICAL,false));
        MovieCommentAdapter movieCommentAdapter = new MovieCommentAdapter(DetailActivity.this, result1);
        recycler_moviecomment.setAdapter(movieCommentAdapter);
    }

    @Override
    public void followsuccess(FollowBean followBean) {
        Toast.makeText(this, followBean.getMessage(), Toast.LENGTH_SHORT).show();
        btnHeartxiangqing.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite_checked));
        detailPresenter.getDetailData(movieId);
    }

    @Override
    public void followerror(FollowBean followBean) {
        Toast.makeText(this, followBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelfollowsuccess(CancelAttentionBean cancelAttentionBean) {
        Toast.makeText(this, cancelAttentionBean.getMessage(), Toast.LENGTH_SHORT).show();
        detailPresenter.getDetailData(movieId);
    }

    @Override
    public void cancelfollowerror() {

    }
}
