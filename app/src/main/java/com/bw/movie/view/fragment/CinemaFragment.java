package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bw.movie.R;
import com.bw.movie.view.adapter.MyCinemaAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YangYueXiang
 * on 2018/11/3
 */
public class CinemaFragment extends Fragment {
    @BindView(R.id.cinema_tablayout)
    TabLayout cinemaTablayout;
    @BindView(R.id.cinema_viewpager)
    ViewPager cinemaViewpager;
    Unbinder unbinder;
    private TabLayout cinema_tablayout;
    private ViewPager cinema_viewpager;
    private ArrayList<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        unbinder = ButterKnife.bind(this, view);
        cinema_tablayout = view.findViewById(R.id.cinema_tablayout);
        cinema_viewpager = view.findViewById(R.id.cinema_viewpager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragments = new ArrayList<>();
        fragments.add(new FirstCinemaFragment());
        fragments.add(new RecommendCinemaFragment());
        MyCinemaAdapter myCinemaAdapter = new MyCinemaAdapter(getActivity().getSupportFragmentManager(),fragments);
        cinema_viewpager.setAdapter(myCinemaAdapter);
        cinema_tablayout.setupWithViewPager(cinema_viewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
