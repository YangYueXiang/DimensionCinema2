package com.bw.movie;


import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.MovieFragment;
import com.bw.movie.view.fragment.UserFragment;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.show_frame_layout)
    FrameLayout showFrameLayout;
    @BindView(R.id.rb_movie)
    RadioButton rbMovie;
    @BindView(R.id.rb_cinema)
    RadioButton rbCinema;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    private FragmentManager manager;
    private MovieFragment movieFragment;
    private CinemaFragment cinemaFragment;
    private UserFragment userFragment;
    private RadioButton[] rb;
    private FrameLayout main_vp;
    private RadioGroup main_rg;
    private Drawable[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
      //  main_rg.getBackground().mutate().setAlpha(12);
        //定义RadioButton数组用来装RadioButton，改变drawableTop大小
        rb = new RadioButton[3];
        main_vp = (FrameLayout) findViewById(R.id.show_frame_layout);
        main_rg = (RadioGroup) findViewById(R.id.rg);
//将RadioButton装进数组中
        rb[0] = (RadioButton) findViewById(R.id.rb_movie);
        rb[1] = (RadioButton) findViewById(R.id.rb_cinema);
        rb[2] = (RadioButton) findViewById(R.id.rb_user);
//for循环对每一个RadioButton图片进行缩放
        for (int i = 0; i < rb.length; i++) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            drawables = rb[i].getCompoundDrawables();
            //获取drawables，2/5表示图片要缩小的比例
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 2 / 3, drawables[1].getMinimumHeight() * 2 / 3);
            //定义一个Rect边界
            drawables[1].setBounds(r);
            //给每一个RadioButton设置图片大小
            rb[i].setCompoundDrawables(null, drawables[1], null, null);
        }


        manager = getSupportFragmentManager();
        movieFragment = new MovieFragment();
        cinemaFragment = new CinemaFragment();
        userFragment = new UserFragment();
        manager.beginTransaction()
                .add(R.id.show_frame_layout,movieFragment)
                .add(R.id.show_frame_layout,cinemaFragment)
                .add(R.id.show_frame_layout,userFragment)
                .hide(cinemaFragment)
                .hide(userFragment)
                .commit();

    }

    @OnClick({R.id.rb_movie, R.id.rb_cinema, R.id.rb_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_movie:
                manager.beginTransaction()
                        .show(movieFragment)
                        .hide(cinemaFragment)
                        .hide(userFragment)
                        .commit();
                break;
            case R.id.rb_cinema:
                manager.beginTransaction()
                        .hide(movieFragment)
                        .show(cinemaFragment)
                        .hide(userFragment)
                        .commit();
                break;
            case R.id.rb_user:
                manager.beginTransaction()
                        .hide(movieFragment)
                        .hide(cinemaFragment)
                        .show(userFragment)
                        .commit();
                break;
        }
    }
}
