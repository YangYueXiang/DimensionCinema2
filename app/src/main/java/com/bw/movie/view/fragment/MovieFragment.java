package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bw.movie.DetailActivity;
import com.bw.movie.R;

import com.bw.movie.WebActivity;
import com.bw.movie.model.bean.ComingMovieBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.NowPlayingMovieBean;
import com.bw.movie.presenter.ComingMoviePresenter;
import com.bw.movie.presenter.HotMoviePresenter;
import com.bw.movie.presenter.NowPlayingMoviePresenter;
import com.bw.movie.view.IView.MovieView;
import com.bw.movie.view.adapter.HotMovieAdapter;
import com.bw.movie.view.adapter.MyCoverAdapter;
import com.bw.movie.view.adapter.MyFragmentPagerAdapter;
import com.bw.movie.view.adapter.NowPlayingAdapter;
import com.bw.movie.view.adapter.SooningAdapter;
import com.bw.movie.view.innerfragment.HotMovieFragment;
import com.bw.movie.view.innerfragment.NowPlayingMovieFragment;
import com.bw.movie.view.innerfragment.SooningMovieFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import recycler.coverflow.RecyclerCoverFlow;


/**
 * Created by YangYueXiang
 * on 2018/11/3
 */
public class MovieFragment extends Fragment implements MovieView {

    @BindView(R.id.recycler_hotmovie)
    RecyclerView recyclerHotmovie;
    @BindView(R.id.recycler_playingmovie)
    RecyclerView recyclerPlayingmovie;
    @BindView(R.id.recycler_sooning)
    RecyclerView recyclerSooning;
    Unbinder unbinder;
    @BindView(R.id.tv_hotmovie)
    TextView tvHotmovie;
    @BindView(R.id.tv_nowplaying)
    TextView tvNowplaying;
    @BindView(R.id.tv_sooning)
    TextView tvSooning;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.ll_all)
    ScrollView llAll;
    @BindView(R.id.tv_location2)
    TextView tvLocation2;
    @BindView(R.id.tv_search2)
    TextView tvSearch2;
    @BindView(R.id.list_tab_layout)
    TabLayout listTabLayout;
    @BindView(R.id.list_view_pager)
    ViewPager listViewPager;
    @BindView(R.id.ll_all2)
    LinearLayout llAll2;
    @BindView(R.id.list)
    RecyclerCoverFlow list;
    private HotMoviePresenter hotMoviePresenter;
    private NowPlayingMoviePresenter nowPlayingMoviePresenter;
    private ComingMoviePresenter comingMoviePresenter;

    private String[] titles = {"热门影片", "正在上映", "即将上映"};
    private ArrayList<Fragment> fragments;
    private TabLayout my_tablayout;
    private ViewPager viewpager_movie;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hotMoviePresenter = new HotMoviePresenter();
        nowPlayingMoviePresenter = new NowPlayingMoviePresenter();
        comingMoviePresenter = new ComingMoviePresenter();
        hotMoviePresenter.attachView(this);
        nowPlayingMoviePresenter.attachView(this);
        comingMoviePresenter.attachView(this);
        hotMoviePresenter.getHotMovieData();
        nowPlayingMoviePresenter.getNowPlayingMovieData();
        comingMoviePresenter.getComingMovieData();

        fragments = new ArrayList<>();
        fragments.add(new HotMovieFragment());
        fragments.add(new NowPlayingMovieFragment());
        fragments.add(new SooningMovieFragment());

        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听


    }


    @Override
    public void hotmoviesuccess(final HotMovieBean result) {
        List<HotMovieBean.ResultBean> result1 = result.getResult();
        // Toast.makeText(getActivity(), "result:" + result, Toast.LENGTH_SHORT).show();
        list.setAdapter(new MyCoverAdapter(getActivity(),result));
        list.scrollToPosition(2);
        recyclerHotmovie.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getActivity(), result1);
        recyclerHotmovie.setAdapter(hotMovieAdapter);
        hotMovieAdapter.setOnItemClickListener(new HotMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                String movieId = result.getResult().get(position).getId();
                intent.putExtra("movieId",movieId);
                startActivity(intent);
            }
        });



    }

    @Override
    public void hotmovieerror() {

    }

    @Override
    public void nowmoviesuccess(List<NowPlayingMovieBean.ResultBean> result) {
        //Toast.makeText(getActivity(), "result:" + result, Toast.LENGTH_SHORT).show();
        recyclerPlayingmovie.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        NowPlayingAdapter nowPlayingAdapter = new NowPlayingAdapter(getActivity(), result);
        recyclerPlayingmovie.setAdapter(nowPlayingAdapter);
    }

    @Override
    public void nowmovieerror() {

    }

    @Override
    public void comingmoviesuccess(List<ComingMovieBean.ResultBean> result) {
        //Toast.makeText(getActivity(), "result:" + result, Toast.LENGTH_SHORT).show();
        recyclerSooning.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        SooningAdapter sooningAdapter = new SooningAdapter(getActivity(), result);
        recyclerSooning.setAdapter(sooningAdapter);
    }

    @Override
    public void comingmovieerror() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hotMoviePresenter.detachView();
        nowPlayingMoviePresenter.detachView();
        comingMoviePresenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_hotmovie, R.id.tv_nowplaying, R.id.tv_sooning, R.id.tv_location, R.id.tv_search, R.id.tv_location2, R.id.tv_search2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hotmovie:

                llAll.setVisibility(View.GONE);
                llAll2.setVisibility(View.VISIBLE);
                myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getActivity().getSupportFragmentManager(), fragments);
                listViewPager.setAdapter(myFragmentPagerAdapter);
                listTabLayout.setupWithViewPager(listViewPager);
                listTabLayout.getTabAt(0).select();
                break;
            case R.id.tv_nowplaying:
                llAll.setVisibility(View.GONE);
                llAll2.setVisibility(View.VISIBLE);
                myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getActivity().getSupportFragmentManager(), fragments);
                listViewPager.setAdapter(myFragmentPagerAdapter);
                listTabLayout.setupWithViewPager(listViewPager);
                listTabLayout.getTabAt(1).select();
                break;
            case R.id.tv_sooning:
                llAll.setVisibility(View.GONE);
                llAll2.setVisibility(View.VISIBLE);
                myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getActivity().getSupportFragmentManager(), fragments);
                listViewPager.setAdapter(myFragmentPagerAdapter);
                listTabLayout.setupWithViewPager(listViewPager);
                listTabLayout.getTabAt(1).select();
                break;
            case R.id.tv_location:
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_search:
                break;
            case R.id.tv_location2:
                Intent intent2 = new Intent(getActivity(), WebActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_search2:
                break;
        }
    }

}
