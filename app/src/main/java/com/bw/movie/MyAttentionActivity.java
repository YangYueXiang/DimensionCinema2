package com.bw.movie;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.view.adapter.MyAttentionAdapter;
import com.bw.movie.view.innerfragment.MyAttentionCinemaFragment;
import com.bw.movie.view.innerfragment.MyAttentionMovieFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAttentionActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MyAttentionAdapter myAttentionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new MyAttentionMovieFragment());
        fragments.add(new MyAttentionCinemaFragment());
        myAttentionAdapter = new MyAttentionAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myAttentionAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }



}
