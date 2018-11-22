package com.bw.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bw.movie.model.bean.CinemaByMovieIdBean;
import com.bw.movie.presenter.CinemaByMovieIdPresenter;
import com.bw.movie.view.IView.CinemaByMovieView;
import com.bw.movie.view.adapter.CinemaByMovieIdAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CinemaByMovieActivity extends AppCompatActivity implements CinemaByMovieView {

    @BindView(R.id.recycler_cinemaByMovie)
    RecyclerView recyclerCinemaByMovie;
    private CinemaByMovieIdPresenter cinemaByMovieIdPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_by_movie);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movieId");
        cinemaByMovieIdPresenter = new CinemaByMovieIdPresenter();
        cinemaByMovieIdPresenter.attachView(this);
        cinemaByMovieIdPresenter.getCinemaByMovieId(movieId);
    }

    @Override
    public void cinemaByMovieSuccess(final CinemaByMovieIdBean cinemaByMovieIdBean) {
      //  Toast.makeText(this, "cinemaByMovieIdBean:" + cinemaByMovieIdBean, Toast.LENGTH_SHORT).show();
        recyclerCinemaByMovie.setLayoutManager(new LinearLayoutManager(CinemaByMovieActivity.this,LinearLayoutManager.VERTICAL,false));
        CinemaByMovieIdAdapter cinemaByMovieIdAdapter = new CinemaByMovieIdAdapter(CinemaByMovieActivity.this,cinemaByMovieIdBean.getResult());
        recyclerCinemaByMovie.setAdapter(cinemaByMovieIdAdapter);
        cinemaByMovieIdAdapter.setOnItemClickListener(new CinemaByMovieIdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String cinemaId = cinemaByMovieIdBean.getResult().get(position).getId();
                Intent intent=new Intent(CinemaByMovieActivity.this,CinemaDetailActivity.class);
                intent.putExtra("cinemasId",cinemaId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void cinemaByMovieError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cinemaByMovieIdPresenter.detachView();
    }
}
